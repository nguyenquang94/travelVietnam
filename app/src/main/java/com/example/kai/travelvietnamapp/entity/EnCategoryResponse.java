package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnCategoryResponse {

    @SerializedName("data")
    private List<EnCategory> data;

    public List<EnCategory> getData() {
        return data;
    }

    public void setData(List<EnCategory> data) {
        this.data = data;
    }

}
