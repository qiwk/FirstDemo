package com.example.firstdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.firstdemo.R;
import com.example.firstdemo.adapter.ArticleAdapter;
import com.example.firstdemo.api.Api;
import com.example.firstdemo.api.ApiConfig;
import com.example.firstdemo.api.TtitCallback;
import com.example.firstdemo.entity.Article;
import com.example.firstdemo.entity.ArticleResponse;
import com.example.firstdemo.entity.LoginResponse;
import com.example.firstdemo.util.CommonUtils;
import com.example.firstdemo.util.ToastUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCollectActivity extends AppCompatActivity {

    private List<Article> articles;

    private RecyclerView articleRecyclerView;

    private ArticleAdapter articleAdapter;
    private int currentPage;
    private boolean isLoadingMore;

    private Handler handler; // 声明一个Handler，用于在主线程中更新UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);

        // 初始化Handler
        handler = new Handler(Looper.getMainLooper());
        // 初始化文章列表和
        articles = new ArrayList<>();
        //设置RecyclerView
        articleRecyclerView = this.findViewById(R.id.articleCollectRecyclerView);
        setupRecyclerView();


        // 获取文章数据
        currentPage = 0;
        getArticlesData(currentPage); // 请求第一页数据，页码从0开始

        // 设置RecyclerView的滚动监听器，检测是否到达列表底部
        articleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoadingMore && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // 到达列表底部，加载更多数据
                        currentPage++;
                        getArticlesData(currentPage);
                    }
                }
            }
        });

        //设置返回键
        findViewById(R.id.btn_goback).setOnClickListener(v -> onBackPressed());
    }

    private void getArticlesData(int page) {


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", "account");
        params.put("password", "pwd");

        String restUrl = ApiConfig.MY_COLLECT + "/" +  page + "/json";
        String cookieStr = CommonUtils.getCookie(MyCollectActivity.this);
        Api.config(restUrl, params).getRequestWithCookie(cookieStr, new TtitCallback() {
            @Override
            public void onSuccess(final String resBody, final List<String> resCookie) {
                Log.d("onSuccess", resBody);
                Log.d("Cookie", resCookie.toString());
                Gson gson = new Gson();
                ArticleResponse articleResponse = gson.fromJson(resBody, ArticleResponse.class);
                if (articleResponse.getErrorCode() == 0) {
                    // 使用Handler在主线程中更新UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 在这里更新UI控件，例如显示用户名和头像
                            articles.addAll(articleResponse.getPageBean().getArticleList());
                            // 更新RecyclerView的数据
                            ArticleAdapter articleAdapter = (ArticleAdapter) articleRecyclerView.getAdapter();
                            articleAdapter.setArticleList(articles);
                            articleAdapter.notifyDataSetChanged();
                            //设置加载更多标志为false，表示加载完成
                            isLoadingMore = false;
                        }
                    });
                } else {
                    // 使用Handler在主线程中更新UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //设置加载更多标志为false，表示加载完成
                            isLoadingMore = false;
                            // 在这里更新UI控件，例如显示用户名和头像
                            ToastUtil.showMsg(MyCollectActivity.this, "获取收藏文章列表失败！");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        // 设置加载更多标志为true，表示正在加载中
        isLoadingMore = true;
    }



    private void setupRecyclerView() {
        // 初始化文章列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyCollectActivity.this);
        articleRecyclerView.setLayoutManager(layoutManager);
        // 创建文章列表适配器并设置给RecyclerView
        ArticleAdapter articleAdapter = new ArticleAdapter(MyCollectActivity.this, articles, true);
        articleRecyclerView.setAdapter(articleAdapter);
    }


}



