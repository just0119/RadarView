package com.banshou.radarview.mvp;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by cjq on 2018/6/8.
 * Email: stoic_yb@139.com
 * features:
 */
public interface ActivityView extends MvpView,LifecycleProvider<ActivityEvent>{
}
