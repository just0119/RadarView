package com.banshou.radarview.login;

import com.banshou.radarview.bean.ResponseLogin;
import com.banshou.radarview.mvp.ActivityView;
import com.banshou.radarview.mvp.Moudle;

import io.reactivex.Observable;

/**
 * Created by cjq on 2018/9/11.
 * Email: stoic_yb@139.com
 * features:
 */
public interface LoginContract {
    interface LoginView extends ActivityView {
        void showResult();
    }

    interface LoginPresenter {
        void login(String tel);
    }

    interface LoginMoudle extends Moudle {
        Observable<ResponseLogin> getResLogin(String tel);
    }
}
