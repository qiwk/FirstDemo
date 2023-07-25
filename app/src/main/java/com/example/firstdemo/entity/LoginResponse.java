package com.example.firstdemo.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {


    /**
     * data : {"admin":false,"chapterTops":[],"coinCount":10,"collectIds":[],"email":"","icon":"","id":151394,"nickname":"qiwk","password":"","publicName":"qiwk","token":"","type":0,"username":"qiwk"}
     * errorCode : 0
     * errorMsg :
     */

    @SerializedName("data")
    private LoginBean loginData;
    private int errorCode;
    private String errorMsg;

    public LoginBean getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginBean loginData) {
        this.loginData = loginData;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
