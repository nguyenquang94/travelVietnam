package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnPivot {
    @SerializedName("place_id")
    private int placeId;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public EnPivot() {}
}
