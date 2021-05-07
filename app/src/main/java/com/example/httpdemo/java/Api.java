package com.example.httpdemo.java;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    //例子二：http://suggest.taobao.com/sug?code=utf-8&q=男士卫衣&callback=cb
    //HTTP 协议的
    @GET("sug")
    Call<ResponseBody> getTaobaoData(@Query("code")String code,@Query("q")String q,
                                     @Query("callback")String callback);

    //不确定参数的get请求
    //这种参数类型只做了解，通常情况下不使用这种传参方式。
    @GET("user")
    Call<ResponseBody> getUnFixedData(@QueryMap Map<String,Object> map);

    //POST 请求
    //【常规】
    //没有参数的请求
    @POST("user")
    Call<ResponseBody> getPostData();

    //有固定参数的表单请求
    @FormUrlEncoded
    @POST("user")
    Call<ResponseBody> getFixedPostData(@Field("id")int id,@Field("name")String name);

    //没有固定参数的表单请求
    //这种参数类型只做了解，通常情况下不使用这种传参方式。
    @FormUrlEncoded
    @POST("user")
    Call<ResponseBody> getUnFixedPostData(@FieldMap Map<String,Object> map);

    //非表单请求，包括但不限于xml、JSON
    //参数是自定义类型,当参数是自定义的类型的时候，例如：TestCmd。
    //通常使用@Headers来固定这次传输的内容数据类型。
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("user")
    Call<ResponseBody> getBodyPostData(@Body TestCmd testCmd);

    //参数是RequestBody
    @POST("user")
    Call<ResponseBody> getBodyPostData2 (@Body RequestBody body);

    //注意：@Body 不能和@MultiPart和@FormUrlEncoded 这两个注解同时使用。

    //@HTTP 请求
    //这个注解可以替代其他七种注解，甚至可以自定义注解的功能。比如，给@GET添加body部分，创造出一个新的GET。
    //method 必须是大写
    //通常情况下不使用
    @HTTP(method = "GET",path = "user",hasBody = true)
    Call<ResponseBody> getHttpData(@Field("name")String name);

    //@Path
    //适合在网址上面添加参数的GET请求使用，因为通常情况下，POST请求不会在网址部分添加参数。
    @GET("user/id/{id}/name/{name}")
    Call<ResponseBody> getPathData(@Path("id")int id,@Path("name")String name,@Query("sex")int sex);

    //实例：https://gank.io/api/v2/categories/<category_type>
    // https://gank.io/api/v2/categories/GanHuo
    // Https 请求之 Chain validation failed 异常 - OKHTTP默认支持CA机构颁发的SSL证书。
    // 如果是自定义证书，或者是其他机构的证书，或者证书过期的话，都会出现这个错误。
    // 解决方式：1、更换SSL正式CA机构的证书；2、使用自定义SSL证书
    @GET("categories/{category_type}")
    Call<ResponseBody> getCategoryType(@Path("category_type")String category_type_con);

    //@Url
    // 实际上，@Url替代的是@GET("url_content")的"url_content"部分。
    // 例子：http://suggest.taobao.com/sug?code=utf-8&q=男士卫衣&callback=cb
    @GET
    Call<ResponseBody> getUrlData(@Url String url,@Query("code")String codeStr,
                                  @Query("q")String qStr,@Query("callback")String cbStr);

    //@Headers、@Header
    //【强调：请求头是可以自定义的，但是要避开已经定义好的请求头】
    //Content-Type: text/html; charset=utf-8
    //Content-Type: multipart/form-data; boundary=something [注：对于多部分实体，boundary 是必需的]
    //Content-type:application/json;charset=UTF-8 [常见的三种使用]
    //更多使用可以参考【补充：网络基础】
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @GET("user")
    Call<ResponseBody> getHeadersData(@Header("X-version")String versionStr,@Query("name")String nameStr);//X- 代表自定义请求头的Key.

    //@Streaming
    //下载大文件时候特别有用。
    @Streaming
    @POST("user")
    Call<ResponseBody> getStreamBigData();









}
