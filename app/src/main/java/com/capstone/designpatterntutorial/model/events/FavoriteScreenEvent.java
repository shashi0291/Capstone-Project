package com.capstone.designpatterntutorial.model.events;

import com.capstone.designpatterntutorial.model.favorite.FavoriteScreenData;

/**
 * Created by gubbave on 7/12/2017.
 */

public class FavoriteScreenEvent {

    private FavoriteScreenData favoriteScreenData;

    public FavoriteScreenEvent(FavoriteScreenData data) {
        favoriteScreenData = data;
    }

    public FavoriteScreenData getFavoriteScreenData() {
        return favoriteScreenData;
    }

    public void setFavoriteScreenData(FavoriteScreenData favoriteScreenData) {
        this.favoriteScreenData = favoriteScreenData;
    }
}
