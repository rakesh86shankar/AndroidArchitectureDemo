package com.example.ra283478.androidarchitecturedemo;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by RA283478 on 1/23/2018.
 */


public interface StoreApi {
    @GET("storeOffers/")
    Call<StoreInfo> getStoreInfo();
}
