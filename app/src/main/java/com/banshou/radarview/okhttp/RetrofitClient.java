package com.banshou.radarview.okhttp;

import com.banshou.radarview.okhttp.modify.GsonConverterFactory;
import com.blankj.utilcode.util.AppUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by cjq on 2018/6/13.
 * Email: stoic_yb@139.com
 * features:
 */
public class RetrofitClient {
    private Retrofit retrofit;

    RetrofitClient(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS);
        if (AppUtils.isAppDebug()) {
            builder.addInterceptor(new LoggerInterceptor("http", true));
        }
        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(url)
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String path = request.url().encodedPath();
            Headers headers = request.headers();
            Request.Builder builder = chain.request().newBuilder().headers(headers);
            builder.addHeader("platForm", "Android");
            Request interRequest = builder.build();
            return chain.proceed(interRequest);
        }
    }
}
