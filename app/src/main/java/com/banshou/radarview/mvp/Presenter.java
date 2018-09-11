package com.banshou.radarview.mvp;

/**
 * Created by cjq on 2018/6/8.
 * Email: stoic_yb@139.com
 * features:
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
