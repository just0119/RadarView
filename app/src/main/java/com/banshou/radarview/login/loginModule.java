package com.banshou.radarview.login;

import com.banshou.radarview.bean.RequestLogin;
import com.banshou.radarview.bean.ResponseLogin;
import com.banshou.radarview.okhttp.ApiService;
import com.banshou.radarview.okhttp.ParamUtils;
import com.banshou.radarview.okhttp.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by cjq on 2018/9/11.
 * Email: stoic_yb@139.com
 * features:
 */
public class loginModule implements LoginContract.LoginMoudle{
    @Override
    public Observable<ResponseLogin> getResLogin(String tel) {
        return RetrofitManager.getClient()
                .create(ApiService.class)
                .loginTel(ParamUtils.objectToMap(new RequestLogin(tel)));
    }
}
