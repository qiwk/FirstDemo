package com.example.firstdemo.api;

public interface TtitCallback {

    void onSuccess(String resBody, String resCookie);

    void onFailure(Exception e);
}
