package com.banshou.radarview.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by cjq on 2018/6/8.
 * Email: stoic_yb@139.com
 * features:
 */
public abstract class BasePresenter<V extends ActivityView, M extends Moudle> implements Presenter<V> {

    private Reference<V> mView;
    protected M mMoudle;

    public BasePresenter() {
        mMoudle = createMoudle();
    }

    @Override
    public void attachView(V mvpView) {
        this.mView = new WeakReference<>(mvpView);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }

    public V getMvpView() {
        return mView.get();
    }

    protected abstract M createMoudle();
}
