package com.example.httpdemo.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.httpdemo.databinding.BasicActivityBinding;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @TODO: Retrofit 的基本常规使用
 * @Date: 2021/4/29 16:14
 * @User: lay
 */
public class BasicActivity extends AppCompatActivity {

    private BasicActivityBinding binding;

    private Api myApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.basic_activity);
        binding = BasicActivityBinding.inflate(getLayoutInflater());
        LinearLayout root = binding.getRoot();
        setContentView(root);
        myApi = RetrofitUtils.getInstance().getApi();

        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData();
                getChildThreadData();
//                getAsynData();
            }
        });
    }

    //异步非阻塞请求
    private void getAsynData() {
        myApi.getCategoryType("Girl").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Object body = response.body();
                if(body == null)return;
                String result = body.toString().trim();
                binding.tvContent.setText(result);
                Log.e("getAsynData()", "onResponse: " + result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("getAsynData()", "onFailure: "+ t.getMessage());
            }
        });
    }


    //同步阻塞请求
    //开启子线程，在子线程中使用同步请求。- 这是在android 端同步的写法。
    //不过由于UI线程是子线程，所以，在Android 场景下必然是异步请求的场景。所以，同步请求只是了解一下即可。
    private void getChildThreadData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Response<ResponseBody> response = null;
                String result = "结果没有";
                try {
                    //没有参数
                    response = myApi.getData().execute();
                    //有参数
//                    response = myApi.getFixedData("娱乐",1,0,"1572e8b33ff76dccff6799c8d901d02e").execute();
                    //有参数 - HTTP
//                    response = myApi.getTaobaoData("utf-8","男士卫衣","cb").execute();
                    //@Path
//                    response = myApi.getCategoryType("Girl").execute();
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                    result = "同步请求失败";
                }
                Log.e("getChildThreadData()", "run: "+result );
            }
        }.start();
    }

    //同步阻塞请求
    //直接在主线程使用出现异常 - 在主线程请求网络错误【NetworkOnMainThreadException】
    private void getData() {
        Response<ResponseBody> response = null;
        String result = "结果没有";
        try {
           response = myApi.getData().execute();
           result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            result = "同步请求失败";
        }
        binding.tvContent.setText(result);

    }






}
