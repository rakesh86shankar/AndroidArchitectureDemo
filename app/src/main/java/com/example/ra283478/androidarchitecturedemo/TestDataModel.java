package com.example.ra283478.androidarchitecturedemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import junit.framework.Test;

/**
 * Created by RA283478 on 1/19/2018.
 */

public class TestDataModel extends ViewModel {

    private LiveData<Coupon> coupon;
    private CouponRepository couponRepository = new CouponRepository();

    public LiveData<Coupon> getCoupon() {
        if(coupon == null){
            coupon = couponRepository.getTopCoupon();
        }
        return coupon;
    }
}
