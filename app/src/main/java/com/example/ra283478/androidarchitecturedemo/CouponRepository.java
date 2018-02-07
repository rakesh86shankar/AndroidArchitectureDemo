package com.example.ra283478.androidarchitecturedemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RA283478 on 1/23/2018.
 */

public class CouponRepository {

    public static final String BASE_URL = "http://www.zoftino.com/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public LiveData<Coupon> getTopCoupon() {
        final MutableLiveData<Coupon> coupon = new MutableLiveData<Coupon>();
        getRetrofitClient().create(CouponApi.class).getTopCoupon().enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                Coupon cpn = response.body();
                coupon.setValue(cpn);
//                coupon.setStore(cpn.getStore());
//                coupon.setCoupon(cpn.getCoupon());
//                coupon.setCouponCode(cpn.getCouponCode());
            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Log.e("", "Error Getting TOP COUPON Data Retrofit");
            }
        });

        return coupon;
    }
}