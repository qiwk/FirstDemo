package com.example.firstdemo.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtil {
    public static Toast mToast;
    public static void showMsg(Context context, String msg){
        if(mToast == null){
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(msg);
        }
        mToast.show();

    }


    public static void showToastSync(Context mContext, String msg) {
        Looper.prepare();
        ToastUtil.showMsg(mContext, msg);
        Looper.loop();
    }
}
