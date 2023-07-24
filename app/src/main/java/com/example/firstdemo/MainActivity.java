package com.example.firstdemo;

import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstdemo.activity.BaseActivity;
import com.example.firstdemo.activity.HomeActivity;
import com.example.firstdemo.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //起始页面动画。。
        //SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

    }

    @Override
    protected void initData() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(HomeActivity.class);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(RegisterActivity.class);
            }
        });

    }
}