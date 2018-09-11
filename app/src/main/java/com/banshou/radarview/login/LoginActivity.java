package com.banshou.radarview.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.banshou.radarview.R;
import com.banshou.radarview.mvp.BaseMvpActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by cjq on 2018/9/11.
 * Email: stoic_yb@139.com
 * features:
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {

    public void onCLick(View c){
        mPresenter.login("15779421123");
    }

    @Override
    public void showResult() {
        Logger.d("haha");
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected View loadContentView() {
        return LayoutInflater.from(mContext).inflate(R.layout.login, null);
    }
}
