package com.nandodev1997.androidtestfernandomorales.Api;

import com.nandodev1997.androidtestfernandomorales.Models.Beer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiBeer {
    @GET("beers")
    Call<List<Beer>> getListBeers(@Query("page")String page);
}
