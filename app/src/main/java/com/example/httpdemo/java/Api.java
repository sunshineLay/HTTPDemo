package com.example.httpdemo.java;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @TODO:
 * @Date: 2021/4/30 10:48
 * @User: lay
 */
public interface Api {
    //GET 请求
    //没有参数的get请求
    //例子：https://gank.io/api/v2/banners
    @GET("banners")
    Call<ResponseBody> getData();

    //确定参数的get请求
    //appKey: 1572e8b33ff76dccff6799c8d901d02e
    //例子1：https://way.jd.com/jisuapi/get?channel=头条&num=10&start=0&appkey=您申请的APPKEY（获取新闻）
    @GET("get")
    Call<ResponseBody> getFixedData(@Query("channel")String channel,@Query("num")int num,
                                    @Query("start")int start,@Query("appkey")String appkey);

    //例子二：http://suggest.taobao.com/sug?code=utf-8&q=商品关键字&callback=cb
    //HTTP 协议的
    @GET("sug")
    Call<ResponseBody> getTaobaoData(@Query("code")String code,@Query("q")String q,
                                     @Query("callback")String callback);

    //不确定参数的get请求
    //这种参数类型只做了解，通常情况下不使用这种传参方式。
    @GET("user")
    Call<ResponseBody> getUnFixedData(@QueryMap Map<String,Object> map);

    //POST 请求
    //没有参数的请求
    @POST("user")
    Call<ResponseBody> getPostData();


}
