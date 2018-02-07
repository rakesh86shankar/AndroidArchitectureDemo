package com.example.ra283478.androidarchitecturedemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity implements LifecycleOwner{
    private LifecycleRegistry mLifecycleRegistry;
    ImageView imageView;
    Runnable r;
    int i = 0;
    public static Integer[] mThumbIds = {
            R.drawable.pic0,R.drawable.pic1,R.drawable.pic2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_view);
         r = new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(mThumbIds[i]);
                i++;
                if(i >= mThumbIds.length){
                    i = 0;
                }
                imageView.postDelayed(r, 1000); //set to go off again in 3 seconds.
            }
        };
        imageView.postDelayed(r,1000);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        Button button = findViewById(R.id.checkLiveData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitRepository.getStoreInfo();
                try{
                    TestDataModel couponViewModel = ViewModelProviders.of(MainActivity.this).get(TestDataModel.class);
                    couponViewModel.getCoupon().observe(MainActivity.this, new Observer<Coupon>() {
                        @Override
                        public void onChanged(@Nullable Coupon coupon) {
                            Coupon coupon1 = coupon;
                            Log.v("Coupon value",coupon1.getCoupon());
                            Log.v("Coupon value Data",coupon1.getCouponCode());
                            Log.v("Coupon valuec11",coupon1.getStore());
                        }
                    });
                }catch (Exception e){
                    Log.v("Exception in downloading from model",e.getMessage());
                }
            }
        });
        RetrofitRepository.getIntData().observe(this, new Observer<StoreInfo>() {
            @Override
            public void onChanged(@Nullable StoreInfo storeInfo) {
                String storeInfo1 = storeInfo.getStore();
                Log.v("Store value",storeInfo1);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
