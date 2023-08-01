package com.example.firstdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.firstdemo.R;
import com.example.firstdemo.entity.ShowBannerEvent;

import org.greenrobot.eventbus.EventBus;


public class SystemSettingActivity extends AppCompatActivity {

    private ImageButton bt_follow_system;
    private ImageButton bt_dark_mode;
    private ImageButton bt_later_read;
    private ImageButton bt_read_history;
    private ImageButton bt_show_top;
    private ImageButton bt_show_banner;
    private ImageButton bt_hide_author;
    private ImageButton bt_hide_project;
    private ImageButton bt_slide_back;

    private SharedPreferences sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        sp = getSharedPreferences("system_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();




        bt_follow_system = findViewById(R.id.bt_follow_system);
        bt_dark_mode = findViewById(R.id.bt_dark_mode);
        bt_later_read = findViewById(R.id.bt_later_read);
        bt_read_history = findViewById(R.id.bt_read_history);
        bt_show_top = findViewById(R.id.bt_show_top);
        bt_show_banner = findViewById(R.id.bt_show_banner);
        bt_hide_author = findViewById(R.id.bt_hide_author);
        bt_hide_project = findViewById(R.id.bt_hide_project);
        bt_slide_back = findViewById(R.id.bt_slide_back);

        //判断图标初始的显示状态
        bt_follow_system.setImageResource(sp.getBoolean("follow_system", true)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_dark_mode.setImageResource(sp.getBoolean("dark_mode", false)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_later_read.setImageResource(sp.getBoolean("later_read", true)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_read_history.setImageResource(sp.getBoolean("read_history", true)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_show_top.setImageResource(sp.getBoolean("show_top", true)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_show_banner.setImageResource(sp.getBoolean("show_banner", true)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_hide_author.setImageResource(sp.getBoolean("hide_author", false)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_hide_project.setImageResource(sp.getBoolean("hide_project", false)?R.mipmap.icon_on:R.mipmap.icon_off);
        bt_slide_back.setImageResource(sp.getBoolean("slide_back", true)?R.mipmap.icon_on:R.mipmap.icon_off);

        //绑定跟随系统
        bt_follow_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("follow_system", true)){
                    editor.putBoolean("follow_system", false);
                    bt_follow_system.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("follow_system", true);
                    bt_follow_system.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
            }
        });

        //绑定主题改变模式
        bt_dark_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("dark_mode", false)){
                    editor.putBoolean("dark_mode", false);
                    bt_dark_mode.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else{
                    editor.putBoolean("dark_mode", true);
                    bt_dark_mode.setImageResource(R.mipmap.icon_on); // 设置开启图标
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                editor.commit();
                recreate(); // 重启当前 Activity 以应用新的主题
            }
        });

        //绑定稍后阅读
        bt_later_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("later_read", true)){
                    editor.putBoolean("later_read", false);
                    bt_later_read.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("later_read", true);
                    bt_later_read.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
            }
        });

        //绑定阅读历史
        bt_read_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("read_history", true)){
                    editor.putBoolean("read_history", false);
                    bt_read_history.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("read_history", true);
                    bt_read_history.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
            }
        });

        //绑定显示置顶
        bt_show_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("show_top", true)){
                    editor.putBoolean("show_top", false);
                    bt_show_top.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("show_top", true);
                    bt_show_top.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
            }
        });


        //绑定显示轮播
        bt_show_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("show_banner", true)){
                    editor.putBoolean("show_banner", false);
                    bt_show_banner.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                    sendShowBannerEvent(false);

                }else{
                    editor.putBoolean("show_banner", true);
                    bt_show_banner.setImageResource(R.mipmap.icon_on); // 设置开启图标
                    sendShowBannerEvent(true);
                }
                editor.commit();
            }
        });


        //绑定隐藏作者
        bt_hide_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("hide_author", true)){
                    editor.putBoolean("hide_author", false);
                    bt_hide_author.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("hide_author", true);
                    bt_hide_author.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
                setResult(RESULT_OK);
            }
        });

        //绑定隐藏项目
        bt_hide_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("hide_project", true)){
                    editor.putBoolean("hide_project", false);
                    bt_hide_project.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("hide_project", true);
                    bt_hide_project.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
                setResult(RESULT_OK);
            }
        });


        //绑定滑动返回
        bt_slide_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sp.getBoolean("slide_back", true)){
                    editor.putBoolean("slide_back", false);
                    bt_slide_back.setImageResource(R.mipmap.icon_off); // 设置关闭图标
                }else{
                    editor.putBoolean("slide_back", true);
                    bt_slide_back.setImageResource(R.mipmap.icon_on); // 设置开启图标
                }
                editor.commit();
            }
        });



        //设置返回键
        findViewById(R.id.btn_goback).setOnClickListener(v -> onBackPressed());
    }



    // 点击按钮后调用该方法发送隐藏 Banner 的事件
    private void sendShowBannerEvent(boolean show) {
        EventBus.getDefault().post(new ShowBannerEvent(show));
    }

}