package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnPlaceResponse {
    @SerializedName("data")
    private List<EnPlace> data;
    @SerializedName("images")
    private List<EnImage> images;

    public List<EnPlace> getData() {
        return data;
    }

    public void setData(List<EnPlace> data) {
        this.data = data;
    }

    public List<EnImage> getImages() {
        return images;
    }

    public void setImages(List<EnImage> images) {
        this.images = images;
    }
}
