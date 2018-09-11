package com.banshou.radarview.okhttp.modify;

import android.support.annotation.NonNull;

import com.banshou.radarview.okhttp.entity.ErrResponse;
import com.banshou.radarview.okhttp.entity.ResultResponse;
import com.banshou.radarview.okhttp.ex.ResultException;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by jhhuang on 2016/10/20.
 * QQ:781913268
 * Description：自定义gson转换器
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        try {
            //ResultResponse 只解析code字段进行约定异常处理
            ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
            if (-1 == resultResponse.getResultcode()) {
                return gson.fromJson(response, type);
            } else if (0 == resultResponse.getResultcode()) {
                return gson.fromJson(response, type);
            } else if (200 == resultResponse.getResultcode()) {
                return gson.fromJson(response, type);
            } else if(6 == resultResponse.getResultcode() || 16 == resultResponse.getResultcode() || 7 == resultResponse.getResultcode()){
//                throw new ResultException(resultResponse.getResultcode(), "");
                return gson.fromJson(response, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = gson.fromJson(response, ErrResponse.class);
                throw new ResultException(resultResponse.getResultcode(), errResponse.getResultMessage());
            }
        } finally {
            value.close();
        }
    }
}
