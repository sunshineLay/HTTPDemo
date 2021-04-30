package com.example.httpdemo.java;

import retrofit2.Retrofit;

/**
 * @TODO:
 * @Date: 2021/4/30 11:17
 * @User: lay
 */
public class RetrofitUtils {
    private static RetrofitUtils instance = new RetrofitUtils();

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gank.io/api/v2/")
                .build();
        api = retrofit.create(Api.class);
    }

    public static RetrofitUtils getInstance(){
        return instance;
    }

    private Api api;

    private Retrofit retrofit;

    public Api getApi(){
        return api;
    }

}
