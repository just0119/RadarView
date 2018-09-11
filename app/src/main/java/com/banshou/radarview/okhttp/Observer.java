package com.banshou.radarview.okhttp;


import com.banshou.radarview.base.Config;
import com.banshou.radarview.okhttp.ex.ResultException;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by cjq on 2018/7/27.
 * Email: stoic_yb@139.com
 * features:
 */
public abstract class Observer<T> implements io.reactivex.Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onNextStep(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String message = Config.NONET;
        if (e instanceof HttpException) {
//            HttpException h = (HttpException) e;
//            try {
//                ResponseBody exception = h.response().errorBody();
//                if (exception != null && ((HttpException) e).code() == 500) {
//                    String string = exception.string();
//                    MyException exception1 = new Gson().fromJson(string, MyException.class);
//                    message = exception1.get__exception().getMessage();
//                } else if (exception != null && (((HttpException) e).code()) == 401) {
//                    Intent intent = new Intent(BaseApplication.getAppContext(), LoginActivity.class);
//                    BaseApplication.getAppContext().startActivity(intent);
//                    SharePreUtils.clear(BaseApplication.getAppContext());
//                }
//            } catch (IOException e1) {
//                e1.printStackTrace();
//                ToastUtils.showShort(message);
//            }
        } else if (e instanceof ResultException) {
//            ResultResponse resultResponse = new Gson().fromJson(((ErrorResponseException) e).getString(), ResultResponse.class);
//            if (resultResponse.getResultCode() == 43) {
//                message = "该手机号已绑定微信";
//            } else if (resultResponse.getResultCode() == 49) {
//                message = "该地址您已经被添加为亲友，请先解除亲友关系";
//            }else if(resultResponse.getResultCode() == 35){
//                message = "余额不足";
//            }
        }
        ToastUtils.showShort(message);
        onErrorResult();
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onNextStep(T t);

    protected abstract void onErrorResult();
}
