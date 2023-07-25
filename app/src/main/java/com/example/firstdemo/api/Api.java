package com.example.firstdemo.api;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.content.Intent;


import com.example.firstdemo.activity.LoginActivity;
import com.example.firstdemo.util.StringUtils;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    private static OkHttpClient client;
    private static String requestUrl;
    private static HashMap<String, String> mParams;
    public static Api api = new Api();

    public Api() {

    }

    public static Api config(String url, HashMap<String, String> params) {
        client = new OkHttpClient.Builder()
                .build();
        requestUrl = ApiConfig.BASE_URl + url;
        mParams = params;
        return api;
    }

    public void postRequest(Context context, final TtitCallback callback) {

        // 第二步构建POST请求的RequestBody
        RequestBody requestBody = new FormBody.Builder()
                .add("username", mParams.get("username"))
                .add("password", mParams.get("password"))
                .build();

        //第三步创建Rquest
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(requestBody)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                final String resCookie = "" + response.header("Set-Cookie").split(";")[0].split("=")[1];
                callback.onSuccess(result, resCookie);
            }
        });
    }


}
