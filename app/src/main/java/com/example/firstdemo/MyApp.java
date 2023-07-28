package com.example.firstdemo;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApp extends Application {
    SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();

        sp = getSharedPreferences("system_preferences", MODE_PRIVATE);
        //生成系统的默认通用属性
        genDefaultPreferences();

        //改变主题
        AppCompatDelegate.setDefaultNightMode(sp.getBoolean("dark_mode", false)?AppCompatDelegate.MODE_NIGHT_YES:AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void genDefaultPreferences(){
        if (!sp.contains("exist")){
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("exist", true);
            editor.putBoolean("follow_system", true);
            editor.putBoolean("dark_mode", false);
            editor.putBoolean("later_read", true);
            editor.putBoolean("read_history", true);
            editor.putBoolean("show_top", true);
            editor.putBoolean("show_banner", true);
            editor.putBoolean("hide_author", false);
            editor.putBoolean("hide_project", false);
            editor.putBoolean("slide_back", true);
            editor.commit();
        }
    }
}
