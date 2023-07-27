package com.example.firstdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.firstdemo.R;
import com.example.firstdemo.util.ToastUtil;

public abstract class BaseActivity extends AppCompatActivity {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
        //设置toolbar为actionbar
//        Toolbar tool_bar = findViewById(R.id.toolbar);
//        setSupportActionBar(tool_bar);
    }


    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void showToast(String msg){
        ToastUtil.showMsg(mContext, msg);
    }

    //异步弹提示消息
    public void showToastSync(String msg) {
        Looper.prepare();
        ToastUtil.showMsg(mContext, msg);
        Looper.loop();
    }

    protected void insertVal(String key, String val) {
        SharedPreferences sp = getSharedPreferences("sp_qiwk", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", key);
        editor.putString("cookie", val);
        editor.commit();
    }

    public void navigateTo(Class cls) {
        Intent in = new Intent(mContext, cls);
        startActivity(in);
    }

    public void navigateToWithFlag(Class cls, int flags) {
        Intent in = new Intent(mContext, cls);
        in.setFlags(flags);
        startActivity(in);
    }

}
