package com.lbg.lbgtest.NetworkInterface;



import com.lbg.lbgtest.common.AppSettings;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Name - RetrofitHelper
 * Purpose - Retorfit Helper to create service for requested class
 */
public class RetrofitHelper {

    private static Retrofit sInstance;

    public <S > S createService(Class<S> serviceClass){
        return getInstance().create(serviceClass);
    }



    /**
     * Singleton retrofit instance
     * @return - retrofit
     */
    private Retrofit getInstance(){
        if(sInstance == null){
            sInstance = getRetrofit();
        }
        return sInstance;
    }

    /**
     * Get retrofit instance
     * @return - retrofit object
     */
    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(AppSettings.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
