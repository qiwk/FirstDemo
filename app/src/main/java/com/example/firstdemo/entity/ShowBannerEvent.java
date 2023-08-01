package com.example.firstdemo.entity;

public class ShowBannerEvent {
    private boolean show;

    public ShowBannerEvent(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }
}

