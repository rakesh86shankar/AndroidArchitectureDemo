package com.example.ra283478.androidarchitecturedemo;

/**
 * Created by RA283478 on 1/23/2018.
 */
import retrofit2.Call;
import retrofit2.http.GET;

public interface CouponApi {

    @GET("topCoupon/")
    Call<Coupon>  getTopCoupon();
}
