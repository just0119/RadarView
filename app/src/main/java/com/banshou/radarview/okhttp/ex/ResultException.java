package com.banshou.radarview.okhttp.ex;

/**
 * Created by cjq on 2018/5/4.
 * Email: stoic_yb@139.com
 * features:
 */
public class ResultException extends RuntimeException{
    private int errCode;

    public ResultException(int errCode,String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
