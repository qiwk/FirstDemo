package com.example.firstdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstdemo.R;
import com.example.firstdemo.activity.AboutAuthorActivity;
import com.example.firstdemo.activity.HistoryActivity;
import com.example.firstdemo.activity.LaterReadActivity;
import com.example.firstdemo.activity.LoginActivity;
import com.example.firstdemo.activity.MyCollectActivity;
import com.example.firstdemo.activity.MyScoreActivity;
import com.example.firstdemo.activity.MyShareActivity;
import com.example.firstdemo.activity.OpenProjectActivity;
import com.example.firstdemo.activity.SystemSettingActivity;
import com.example.firstdemo.api.Api;
import com.example.firstdemo.api.ApiConfig;
import com.example.firstdemo.api.TtitCallback;
import com.example.firstdemo.entity.LoginResponse;
import com.example.firstdemo.util.CommonUtils;
import com.example.firstdemo.util.StringUtils;
import com.example.firstdemo.util.ToastUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private Button loginButton;

    private ImageView headImage;
    private TextView myScoreView;
    private TextView myShareView;
    private TextView myCollectView;
    private TextView laterReadView;
    private TextView readHistoryView;
    private TextView openProjectView;
    private TextView aboutAuthorView;
    private TextView systemSettingView;
    private TextView logoutView;

    private AppBarLayout appBarLayout;
    private View upperLayout;

    private static final int REQUEST_LOGIN = 1;

    private Handler handler; // 声明一个Handler，用于在主线程中更新UI


    public MyFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        handler = new Handler(Looper.getMainLooper()); // 初始化Handler

        loginButton = (Button) view.findViewById(R.id.loginButton);
        headImage = view.findViewById(R.id.headImage);
        myScoreView = view.findViewById(R.id.myScore);
        myShareView = view.findViewById(R.id.myShare);
        myCollectView = view.findViewById(R.id.myCollect);
        laterReadView = view.findViewById(R.id.laterRead);
        readHistoryView = view.findViewById(R.id.readHistory);
        openProjectView = view.findViewById(R.id.openProject);
        aboutAuthorView = view.findViewById(R.id.aboutAuthor);
        systemSettingView = view.findViewById(R.id.systemSetting);
        logoutView = view.findViewById(R.id.tv_logout);


        //设置监听事件跳转到不同的页面
        setListeners();
        //设置上拉折叠逻辑
        appBarLayout = view.findViewById(R.id.app_bar_layout);
        upperLayout = view.findViewById(R.id.upper_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                // 获取AppBarLayout的滚动距离和总高度
                int scrollRange = appBarLayout.getTotalScrollRange();
                float alpha = 1 - (float) Math.abs(verticalOffset) / scrollRange;

                // 设置上半部分的透明度
                upperLayout.setAlpha(alpha);
            }
        });

        //设置退出登录按钮的监听器
        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 判断登录状态，如果已登录，访问远端退出登录接口，清除本地缓存，未登录则给出提示
                if (isLoggedIn()) {
                    logout("", "");
                } else {
                    ToastUtil.showMsg(getContext(), "已经是退出登录状态！");
                }
            }
        });

        // 判断登录状态，如果已登录，则显示用户头像和用户名，隐藏登录按钮
        if (isLoggedIn()) {
            showLoggedInState();
        } else {
            showLoggedOutState();
        }
        return view;
    }


    private void setListeners(){
        OnClick onclick = new OnClick();
        loginButton.setOnClickListener(onclick);
        myScoreView.setOnClickListener(onclick);
        myShareView.setOnClickListener(onclick);
        myCollectView.setOnClickListener(onclick);
        laterReadView.setOnClickListener(onclick);
        readHistoryView.setOnClickListener(onclick);
        openProjectView.setOnClickListener(onclick);
        aboutAuthorView.setOnClickListener(onclick);
        systemSettingView.setOnClickListener(onclick);
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            int id = view.getId();
            if (id == R.id.loginButton){
                intent = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
                return;
            }else if(id == R.id.myScore){
                intent = new Intent(getContext(), MyScoreActivity.class);
                startActivity(intent);
            }else if(id == R.id.myShare){
                intent = new Intent(getContext(), MyShareActivity.class);
                startActivity(intent);
            }else if (id == R.id.myCollect){
                if(!isLoggedIn()){
                    ToastUtil.showMsg(getContext(), "请先登录");
                }else{
                    intent = new Intent(getContext(), MyCollectActivity.class);
                    startActivity(intent);
                }

            }else if(id == R.id.laterRead){
                intent = new Intent(getContext(), LaterReadActivity.class);
                startActivity(intent);
            }else if(id == R.id.readHistory){
                intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
            }else if(id == R.id.openProject){
                intent = new Intent(getContext(), OpenProjectActivity.class);
                startActivity(intent);
            }else if(id == R.id.aboutAuthor){
                intent = new Intent(getContext(), AboutAuthorActivity.class);
                startActivity(intent);
            }else if(id == R.id.systemSetting){
                intent = new Intent(getContext(), SystemSettingActivity.class);
                startActivity(intent);
            }
        }

    }


    // 显示登录后的界面
    private void showLoggedInState() {

        int avatarResId = R.mipmap.image_boy; // 根据实际情况获取头像资源ID
        headImage.setImageResource(avatarResId);
        loginButton.setText(getSavedUsername());
        loginButton.setOnClickListener(null);//解除登录监听器
    }

    // 显示登录前的界面
    private void showLoggedOutState() {
        headImage.setImageResource(R.drawable.shape_gray_circle); // 显示默认头像
        loginButton.setText("登录或注册");
        //重新加上登录监听器
        loginButton.setOnClickListener(null);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
            }
        });

    }

    // 在登录界面登录成功后回调此方法，用于刷新界面显示
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == Activity.RESULT_OK) {
            // 登录成功，刷新界面显示
            showLoggedInState();
        }
    }


    private String getSavedUsername() {
        // 在这里从SharedPreferences或全局变量中获取保存的用户名信息
        SharedPreferences sp = getActivity().getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
        return sp.getString("username", "");
    }

    private boolean isLoggedIn() {
        // 在这里从SharedPreferences或全局变量中获取保存的用户名信息
        SharedPreferences sp = getActivity().getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
        return sp.contains("cookie");
    }

    private void logout(String account, String pwd) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", account);
        params.put("password", pwd);
        Api.config(ApiConfig.LOGOUT, params).getRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String resBody, final List<String> resCookie) {
                Log.d("logoutSuccess", resBody);
                Log.d("logoutCookie", resCookie.toString());
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(resBody, LoginResponse.class);
                if (loginResponse.getErrorCode() == 0) {
                    //退出成功，清除本地缓存
                    SharedPreferences sp = getActivity().getSharedPreferences("sp_qiwk", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear();
                    editor.commit();
                    // 使用Handler在主线程中更新UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 在这里更新UI控件，例如显示用户名和头像
                            ToastUtil.showMsg(getContext(), "成功退出登录！");
                            showLoggedOutState();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showMsg(getContext(), "退出登录失败！");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}

