package com.example.httpdemo.java;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @TODO:
 * @Date: 2021/4/30 10:48
 * @User: lay
 */
public interface Api {
    //Get 请求
    //没有参数的get请求
    //例子：https://gank.io/api/v2/banners
    @GET("banners")
    Call<ResponseBody> getData();

    //确定参数的get请求
    @GET("user")
    Call<ResponseBody> getFixedData(@Query("id")long idLong,@Query("name")String nameStr);

    //不确定参数的get请求
    @GET("user")
    Call<ResponseBody> getUnFixedData(@QueryMap Map<String,Object> map);
}
