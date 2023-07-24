package com.example.firstdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static String getTimeAgo(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - timestamp;

        // Convert time difference to minutes
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifference);

        if (minutes < 1) {
            return "刚刚";
        } else if (minutes < 60) {
            return minutes + "分钟前";
        } else if (minutes < 24 * 60) {
            long hours = TimeUnit.MILLISECONDS.toHours(timeDifference);
            return hours + "小时前";
        } else if (minutes < 48 * 60) {
            return "昨天";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.format(new Date(timestamp));
        }
    }
}