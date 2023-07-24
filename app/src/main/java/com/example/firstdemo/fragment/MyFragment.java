package com.example.firstdemo.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private Button loginButton;
    private TextView myScoreView;
    private TextView myShareView;
    private TextView myCollectView;
    private TextView laterReadView;
    private TextView readHistoryView;
    private TextView openProjectView;
    private TextView aboutAuthorView;
    private TextView systemSettingView;





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
}

