package com.capstone.designpatterntutorial.views.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.presenters.HomePresenter;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.adapters.NavigationMenuAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by gubbave on 5/19/2017.
 */

public class NavigationMenuFragment extends BaseFragment implements NavigationMenuAdapter.OnItemClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static String TAG = NavigationMenuFragment.class.getSimpleName();

    public static final int DEFAULT_SELECTED_INDEX = 0;

    @BindView(R.id.navigation_menu_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.curentLocation)
    TextView mCurrentLocation;

    @BindView(R.id.title)
    TextView title;

    @Inject
    HomePresenter homePresenter;

    private NavigationMenuAdapter mAdapter;
    private String[] menuList;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private final int PERMISSION_ACCESS_FINE_LOCATION = 1;


    public static NavigationMenuFragment newInstance() {
        Bundle args = new Bundle();

        NavigationMenuFragment fragment = new NavigationMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectFragment() {
        MyApplication.getAppComponent(MyApplication.getAppContext()).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_menu_fragment;
    }

    @Override
    protected void initFragmentCreation(Bundle savedInstanceState) {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void initFragment(View rootView) {
        super.initFragment(rootView);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        menuList = getResources().getStringArray(R.array.navigation_menu_list);

        mAdapter = new NavigationMenuAdapter(menuList);
        mAdapter.setSelectedItem(DEFAULT_SELECTED_INDEX);
        mAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        int prevSelectedIndex = mAdapter.getSelectedItem();

        if (prevSelectedIndex != position) {
            mAdapter.setSelectedItem(position);
            mAdapter.notifyItemChanged(prevSelectedIndex);
            mAdapter.notifyItemChanged(position);
        }

        ((HomeActivity) getActivity()).closeDrawerLayout();

        if (getResources().getString(R.string.design_pattern).equalsIgnoreCase(menuList[position])) {
            homePresenter.getPattern(getActivity().getLoaderManager());
        } else if (getResources().getString(R.string.favorites).equalsIgnoreCase(menuList[position])) {
            ((HomeActivity) getActivity()).launchFavoriteFragment();
        } else if (getResources().getString(R.string.recents).equalsIgnoreCase(menuList[position])) {
            //Todo: Implement Recent visited pattern screen.
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.tag(TAG).d(" onConnected ");
        //Check about the Last Known location
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Timber.tag(TAG).d("Permission Not Granted");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_ACCESS_FINE_LOCATION);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
            if (addresses.size() > 0) {
                Address addr = addresses.get(0);
                Timber.tag(TAG).d("Locality :: " + addr.getLocality() + " Country Name :: " + addr.getCountryName());
                String location = addr.getLocality() + ", " + addr.getCountryName();
                mCurrentLocation.setText(location);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mGoogleApiClient.disconnect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_ACCESS_FINE_LOCATION:
                Timber.tag(TAG).d("Permission granted");
                mGoogleApiClient.reconnect();
                break;
            default:
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Timber.tag(TAG).d(" onConnectionSuspended ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.tag(TAG).d(" onConnectionFailed ");
    }
}