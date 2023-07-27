package com.example.firstdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.firstdemo.R;
import com.example.firstdemo.activity.BaseActivity;
import com.example.firstdemo.api.Api;
import com.example.firstdemo.api.ApiConfig;
import com.example.firstdemo.api.TtitCallback;
import com.example.firstdemo.entity.LoginResponse;
import com.example.firstdemo.util.AppConfig;
import com.example.firstdemo.util.StringUtils;
import com.example.firstdemo.util.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.*;



public class LoginActivity extends BaseActivity {

    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;
    private TextView tvGoRegister;





    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        tvGoRegister = findViewById(R.id.tv_go_register);
    }

    @Override
    protected void initData() {




        //监听跳转注册页面
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(RegisterActivity.class);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }
        });


    }

    private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }

        // 如果登录成功，创建一个Intent将数据返回给之前的Activity
        Intent intent = new Intent();
        intent.putExtra("username", account);
        intent.putExtra("password", pwd);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", account);
        params.put("password", pwd);


        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String resBody, final List<String> resCookie) {
                Log.d("onSuccess", resBody);
                Log.d("Cookie", resCookie.toString());
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(resBody, LoginResponse.class);
                if (loginResponse.getErrorCode() == 0) {
                    //登录成功，保存token到本地并跳转
                    String cookieStr = String.join("----", resCookie);
                    insertVal(loginResponse.getLoginData().getNickname(), cookieStr);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    showToastSync("登录成功！");
                } else {
                    showToastSync("登录失败！");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



}