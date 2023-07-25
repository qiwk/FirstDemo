package com.example.firstdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.firstdemo.util.CommonUtils;
import com.google.android.material.appbar.AppBarLayout;

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

    private AppBarLayout appBarLayout;
    private View upperLayout;

    private static final int REQUEST_LOGIN = 1;





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
            }else if(id == R.id.myShare){
                intent = new Intent(getContext(), MyShareActivity.class);
            }else if (id == R.id.myCollect){
                intent = new Intent(getContext(), MyCollectActivity.class);
            }else if(id == R.id.laterRead){
                intent = new Intent(getContext(), LaterReadActivity.class);
            }else if(id == R.id.readHistory){
                intent = new Intent(getContext(), HistoryActivity.class);
            }else if(id == R.id.openProject){
                intent = new Intent(getContext(), OpenProjectActivity.class);
            }else if(id == R.id.aboutAuthor){
                intent = new Intent(getContext(), AboutAuthorActivity.class);
            }else {
                intent = new Intent(getContext(), SystemSettingActivity.class);
            }
            startActivity(intent);
        }

    }


    // 显示登录后的界面
    private void showLoggedInState() {

        int avatarResId = R.drawable.home_selected; // 根据实际情况获取头像资源ID
        headImage.setImageResource(avatarResId);
        loginButton.setText(getSavedUsername());
        loginButton.setOnClickListener(null);//解除监听器
    }

    // 显示登录前的界面
    private void showLoggedOutState() {
        headImage.setImageResource(R.drawable.shape_gray_circle); // 显示默认头像
        loginButton.setText("登录或注册");
        //重亲加上监听器
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
        return sp.contains("password");
    }

}

