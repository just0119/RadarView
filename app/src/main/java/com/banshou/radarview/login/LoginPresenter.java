package com.banshou.radarview.login;

import android.annotation.SuppressLint;

import com.banshou.radarview.bean.ResponseLogin;
import com.banshou.radarview.mvp.ActivityPresenter;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by cjq on 2018/9/11.
 * Email: stoic_yb@139.com
 * features:
 */
@SuppressLint("CheckResult")
public class LoginPresenter extends ActivityPresenter<LoginActivity, LoginContract.LoginMoudle> implements LoginContract.LoginPresenter {
    @Override
    public void login(String tel) {
        mMoudle.getResLogin(tel)
                .compose(callbackOnIOThread())
                .subscribe(responseLogin -> {
                    if (isViewAttached()) getMvpView().showResult();
                });
    }

    @Override
    protected LoginContract.LoginMoudle createMoudle() {
        return new loginModule();
    }
}
