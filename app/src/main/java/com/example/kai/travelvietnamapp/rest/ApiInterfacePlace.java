package com.example.kai.travelvietnamapp.rest;

import com.example.kai.travelvietnamapp.entity.EnPlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kai on 01/03/2017.
 */

public interface ApiInterfacePlace {
    @GET("place")
    Call<EnPlaceResponse> getAll();
}
