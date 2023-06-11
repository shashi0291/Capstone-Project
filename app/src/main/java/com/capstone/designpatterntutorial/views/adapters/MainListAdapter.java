package com.capstone.designpatterntutorial.views.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.databinding.ItemDesignPatternBinding;
import com.capstone.designpatterntutorial.model.mainscreen.Pattern;

import java.util.List;


/**
 * Created by gubbave on 5/8/2017.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.DetailsViewHolder> {

    private List<Pattern> mPatternList;
    private OnItemClickListener mListener;

    public MainListAdapter(List<Pattern> patternList) {
        this.mPatternList = patternList;
    }

    public void setmPatternList(List<Pattern> mPatternList) {
        this.mPatternList = mPatternList;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDesignPatternBinding binding = ItemDesignPatternBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        holder.bindData(getItemData(position), position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private Pattern getItemData(int position) {
        return mPatternList.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_design_pattern;
    }

    @Override
    public int getItemCount() {
        return mPatternList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        ItemDesignPatternBinding binding;

        public DetailsViewHolder(ItemDesignPatternBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Pattern pattern, final int position) {
            binding.itemDesignPatternTitle.setText(pattern.getName());
            binding.itemDesignPatternDescription.setText(pattern.getIntent());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {
                        mListener.onItemClick(v, position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
