package com.example.firstdemo.api;

public class ApiConfig {
    public static final int PAGE_SIZE = 5;
    public static final String BASE_URl = "https://www.wanandroid.com";
    public static final String LOGIN = "/user/login"; //登录
    public static final String HOME_ARTICLE = "/article/list"; //首页文章列表

    public static final String LOGOUT = "/user/logout/json"; //退出登录

    public static final String MY_COLLECT = "/lg/collect/list"; //收藏文章列表

    public static final String DO_COLLECT = "/lg/collect"; //收藏文章
    public static final String UN_COLLECT_FROM_HOME = "/lg/uncollect_originId";
    public static final String UN_COLLECT_FROM_MY = "/lg/uncollect";
    public static final String TOP_ARTICLE = "/article/top/json";

    public static final String QUESTION = "/wenda/list";




    public static final String REGISTER = "/user/register";//注册
    public static final String VIDEO_LIST_ALL = "/app/videolist/listAll";//所有类型视频列表
    public static final String VIDEO_LIST_BY_CATEGORY = "/app/videolist/getListByCategoryId";//各类型视频列表
    public static final String VIDEO_CATEGORY_LIST = "/app/videocategory/list";//视频类型列表
    public static final String NEWS_LIST = "/app/news/api/list";//资讯列表
    public static final String VIDEO_UPDATE_COUNT = "/app/videolist/updateCount";//更新点赞,收藏,评论
    public static final String VIDEO_MYCOLLECT = "/app/videolist/mycollect";//我的收藏

}
