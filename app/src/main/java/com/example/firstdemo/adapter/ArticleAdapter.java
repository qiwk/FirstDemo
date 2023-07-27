package com.example.firstdemo.adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdemo.R;
import com.example.firstdemo.activity.ArticleDetailActivity;
import com.example.firstdemo.activity.LoginActivity;
import com.example.firstdemo.activity.MyCollectActivity;
import com.example.firstdemo.api.Api;
import com.example.firstdemo.api.ApiConfig;
import com.example.firstdemo.api.TtitCallback;
import com.example.firstdemo.entity.Article;
import com.example.firstdemo.entity.ArticleResponse;
import com.example.firstdemo.entity.LoginResponse;
import com.example.firstdemo.util.CommonUtils;
import com.example.firstdemo.util.TimeUtils;
import com.example.firstdemo.util.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private List<Article> articleList;

    private boolean isCollected;
    private Handler handler; // 声明一个Handler，用于在主线程中更新UI

    public ArticleAdapter(Context context, List<Article> articleList, boolean isCollected) {
        this.context = context;
        this.articleList = articleList;
        this.isCollected = isCollected;
        // 初始化Handler
        handler = new Handler(Looper.getMainLooper());
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.bindArticleData(article);

        //点击列表项跳转到详情页面
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: 打开并渲染详情页
//                Intent in = new Intent(context, LoginActivity.class);
//                context.startActivity(in);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticleList(List<Article> articleList){
        this.articleList = articleList;
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorTextView;
        private TextView publishTimeTextView;
        private TextView originTextView;

        private ImageButton collectButton;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            publishTimeTextView = itemView.findViewById(R.id.publishTimeTextView);
            originTextView = itemView.findViewById(R.id.originTextView);
            collectButton = itemView.findViewById(R.id.bt_collect);
        }

        public void bindArticleData(Article article) {
            titleTextView.setText(Html.fromHtml(article.getTitle()).toString());
            String Author = article.getAuthor().length()==0?article.getShareUser():article.getAuthor();
            authorTextView.setText(Author);
            publishTimeTextView.setText(TimeUtils.getTimeAgo(article.getPublishTime()));
            originTextView.setText(article.getSuperChapterName());

            //判断要显示的收藏图标的颜色
            if(isCollected || article.isCollect()){
                collectButton.setImageResource(R.mipmap.like_selected);
            }else {
                collectButton.setImageResource(R.mipmap.like_unselected);
            }


            //点击列表题目项跳转到详情页面
            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: 打开并渲染详情页
                    Intent in = new Intent(context, ArticleDetailActivity.class);
                    //传递链接
                    in.putExtra("article_url", article.getLink());
                    context.startActivity(in);
                }
            });


            // 点击收藏按钮进行收藏操作或者取消收藏操作
            collectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doCollectOrUnCollect(article);
                }
            });
        }

        public void doCollectOrUnCollect(Article article) {

            //先判断是否登录，如果未登录，提示登录并跳转到登录页面
            if(!CommonUtils.isLoggedIn(context)){
                ToastUtil.showMsg(context, "请先登录！");
                Intent in = new Intent(context, LoginActivity.class);
                context.startActivity(in);
            }
            //获取cookie
            String cookieStr = CommonUtils.getCookie(context);
            String restUrl;
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("username", "account");
            params.put("password", "pwd");

            //判断是收藏还是取消收藏
            if(isCollected || article.isCollect()){
                //取消收藏，看是在首页文章列表取消收藏还是在收藏列表取消收藏
                if (isCollected){
                    restUrl = ApiConfig.UN_COLLECT_FROM_MY + "/" +  article.getId() + "/json";
                    int originId = (article.getOriginId()>0)?article.getOriginId():-1;
                    params.put("originId", ""+originId);
                    Api.config(restUrl, params).postRequestWithCookie(cookieStr, new TtitCallback() {
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
                                        // 在这里更新UI控件，刷新图标
                                        collectButton.setImageResource(R.mipmap.like_unselected);
                                    }
                                });
                            } else {
                                // 使用Handler在主线程中更新UI
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 在这里更新UI控件
                                        ToastUtil.showMsg(context, "取消收藏失败！");
                                    }
                                });
                            }
                        }
                        @Override
                        public void onFailure(Exception e) {

                        }
                    });
                    return;
                }else {
                    restUrl = ApiConfig.UN_COLLECT_FROM_HOME + "/" +  article.getId() + "/json";
                }
            }else {
                //收藏
                restUrl = ApiConfig.DO_COLLECT + "/" +  article.getId() + "/json";
            }
            Api.config(restUrl, params).postRequestWithCookie(cookieStr, new TtitCallback() {
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
                                if(isCollected || article.isCollect()){
                                    // 在这里更新UI控件，刷新图标
                                    collectButton.setImageResource(R.mipmap.like_unselected);
                                }else {
                                    collectButton.setImageResource(R.mipmap.like_selected);
                                }
                            }
                        });
                    } else {
                        // 使用Handler在主线程中更新UI
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(isCollected || article.isCollect()){
                                    // 在这里更新UI控件
                                    ToastUtil.showMsg(context, "取消收藏失败！");
                                }else {
                                    // 在这里更新UI控件
                                    ToastUtil.showMsg(context, "收藏文章失败！");
                                }
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
}