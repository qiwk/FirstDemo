package com.example.firstdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUtils {

    public static  String getSavedUsername(Context context) {
        // 在这里从SharedPreferences或全局变量中获取保存的用户名信息
        SharedPreferences sp = context.getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
        return sp.getString("username", "");
    }

    public static  String getCookie(Context context) {
        // 在这里从SharedPreferences或全局变量中获取保存的用户名信息
        SharedPreferences sp = context.getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
        return sp.getString("cookie", "");
    }

    public static boolean isLoggedIn(Context context) {
        // 在这里从SharedPreferences或全局变量中获取保存的用户名信息
        SharedPreferences sp = context.getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
        return sp.contains("cookie");
    }


}
