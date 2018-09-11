package com.banshou.radarview.okhttp;

import com.banshou.radarview.bean.ResponseLogin;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by cjq on 2018/9/11.
 * Email: stoic_yb@139.com
 * features:
 */
public interface ApiService {

    @POST("app/login")
    Observable<ResponseLogin> loginTel(@QueryMap Map<String, String> map);
}
