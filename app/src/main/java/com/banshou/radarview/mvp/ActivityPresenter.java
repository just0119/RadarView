package com.banshou.radarview.mvp;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cjq on 2018/6/8.
 * Email: stoic_yb@139.com
 * features:
 */
public abstract class ActivityPresenter<V extends ActivityView,M extends Moudle> extends BasePresenter<V,M> implements LifecycleProvider<ActivityEvent>{
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return getMvpView().lifecycle();
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent( ActivityEvent event) {
        return getMvpView().bindUntilEvent(event);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return getMvpView().bindToLifecycle();
    }

    public <T> ObservableTransformer<T,T> callbackOnIOThread(){
        return tObservable -> tObservable
                .filter(t -> isViewAttached())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle());
    }


}
