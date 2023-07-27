package com.example.firstdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstdemo.R;

public class ArticleDetailActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        // 获取传递过来的文章链接
        String articleUrl = getIntent().getStringExtra("article_url");

        // 设置WebView并加载文章链接
        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用JavaScript
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 启用缓存，优先从缓存加载
        webSettings.setDomStorageEnabled(true); // 启用DOM存储
        webSettings.setLoadWithOverviewMode(true); // 使用宽视图
        webSettings.setUseWideViewPort(true); // 支持双击缩放

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // 当用户点击网页链接时，仍然在WebView中加载而不是打开默认浏览器
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(articleUrl);

        // 添加浮动返回按钮
        findViewById(R.id.btn_goback_in_detail).setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放WebView资源，避免内存泄漏
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
        }
    }
}
