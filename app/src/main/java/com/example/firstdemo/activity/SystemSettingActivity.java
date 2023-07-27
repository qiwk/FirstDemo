package com.example.firstdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.firstdemo.R;

public class SystemSettingActivity extends AppCompatActivity {

    private ImageButton bt_dark_mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        //绑定主题改变模式
        bt_dark_mode = findViewById(R.id.bt_dark_mode);
        bt_dark_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取当前暗色模式状态
                int currentNightMode = AppCompatDelegate.getDefaultNightMode();

                // 根据当前暗色模式状态切换
                if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    bt_dark_mode.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    bt_dark_mode.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }


                // 将新的主题应用到Activity
                getDelegate().applyDayNight();

                // 重新创建Activity
//                recreate();

            }
        });


        //设置返回键
        findViewById(R.id.btn_goback).setOnClickListener(v -> onBackPressed());
    }
}