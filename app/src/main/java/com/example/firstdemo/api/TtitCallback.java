package com.example.firstdemo.api;

import java.util.List;

public interface TtitCallback {

    void onSuccess(String resBody, List<String> resCookie);

    void onFailure(Exception e);
}
