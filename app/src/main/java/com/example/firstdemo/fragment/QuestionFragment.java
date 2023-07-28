package com.example.firstdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.firstdemo.MainActivity;
import com.example.firstdemo.R;
import com.example.firstdemo.activity.ArticleDetailActivity;
import com.example.firstdemo.activity.MyCollectActivity;
import com.example.firstdemo.adapter.ArticleAdapter;
import com.example.firstdemo.api.Api;
import com.example.firstdemo.api.ApiConfig;
import com.example.firstdemo.api.TtitCallback;
import com.example.firstdemo.entity.Article;
import com.example.firstdemo.entity.ArticleResponse;
import com.example.firstdemo.entity.TopArticleResponse;
import com.example.firstdemo.util.CommonUtils;
import com.example.firstdemo.util.ToastUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {


    private List<Article> articles;

    private RecyclerView articleRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private int currentPage;
    private boolean isLoadingMore;

    private Handler handler; // 声明一个Handler，用于在主线程中更新UI

    private SharedPreferences sp;



    public QuestionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        sp = getContext().getSharedPreferences("system_preferences", Context.MODE_PRIVATE);
        // 初始化Handler
        handler = new Handler(Looper.getMainLooper());

        // 初始化文章列表
        articles = new ArrayList<>();

        //设置RecyclerView
        articleRecyclerView = view.findViewById(R.id.articleRecyclerView);
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

        // 初始化下拉刷新布局
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 执行下拉刷新操作，重新获取第一页数据
                currentPage = 0;
                articles.clear();
                getArticlesData(currentPage);
            }
        });
        return view;
    }

    private void getArticlesData(int page) {


        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", "account");
        params.put("password", "pwd");


        String restUrl = ApiConfig.QUESTION + "/" +  page + "/json";

        String cookieStr = CommonUtils.getCookie(getContext());
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

                            // 停止下拉刷新动画
                            swipeRefreshLayout.setRefreshing(false);
                            //设置加载更多标志为false，表示加载完成
                            isLoadingMore = false;
                        }
                    });
                } else {
                    // 使用Handler在主线程中更新UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 在这里更新UI控件，例如显示用户名和头像
                            ToastUtil.showMsg(getContext(), "获取文章列表失败！");
                            //设置加载更多标志为false，表示加载完成
                            isLoadingMore = false;
                            // 停止下拉刷新动画
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {
                // 使用Handler在主线程中更新UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里更新UI控件，例如显示用户名和头像
                        ToastUtil.showMsg(getContext(), "网络请求列表失败！");
                        //设置加载更多标志为false，表示加载完成
                        isLoadingMore = false;
                        // 停止下拉刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
        // 设置加载更多标志为true，表示正在加载中
        isLoadingMore = true;
    }



    //跳转到详情页
    private void openDetailPage(String url){
        Intent in = new Intent(getContext(), ArticleDetailActivity.class);
        in.putExtra("article_url", url);
        startActivity(in);
    }

    private void setupRecyclerView() {
        // 初始化文章列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        articleRecyclerView.setLayoutManager(layoutManager);
        // 创建文章列表适配器并设置给RecyclerView
        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), articles, false);
        articleRecyclerView.setAdapter(articleAdapter);
    }

}