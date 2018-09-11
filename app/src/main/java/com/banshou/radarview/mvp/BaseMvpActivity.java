package com.banshou.radarview.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.banshou.radarview.login.BaseActivity;
import com.banshou.radarview.utils.WeiboDialogUtils;

/**
 * Created by cjq on 2018/6/8.
 * Email: stoic_yb@139.com
 * features:
 */
public abstract class BaseMvpActivity<P extends ActivityPresenter> extends BaseActivity implements ActivityView{
    protected P mPresenter;
    public Dialog dialogAni;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected void showLoading(){
        if(dialogAni == null){
            dialogAni = new WeiboDialogUtils().createLoadingDialog(mContext);
        }
    }

    protected void hideLoading(){
        if(dialogAni!=null){
            new WeiboDialogUtils().closeDialog(dialogAni);
        }
    }

    protected abstract P createPresenter();
}
