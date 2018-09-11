package com.banshou.radarview.okhttp.entity;

/**
 * Created by cjq on 2018/5/4.
 * Email: stoic_yb@139.com
 * features:
 */
public class ErrResponse {
    private static final String TAG = "ErrResponse";
    private int resultCode;
    private String resultMessage;
    private String data;

    public static String getTAG() {
        return TAG;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
