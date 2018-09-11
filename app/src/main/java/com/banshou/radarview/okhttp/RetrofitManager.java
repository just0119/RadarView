package com.banshou.radarview.okhttp;

import com.banshou.radarview.base.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjq on 2017/11/22.
 * e-mail : qq@163.cn
 * function : retrofitmanager
 */

public class RetrofitManager {
    private static Map<String, RetrofitClient> map = new HashMap<>();

    private RetrofitManager() {
    }

    public static RetrofitClient getClient() {
        return getClient(Config.BASEURL);
    }

    public static RetrofitClient getClient(String url) {
        RetrofitClient appClient;
        appClient = map.get(url);

        if (appClient == null) {
            try {
                appClient = new RetrofitClient(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(url, appClient);
        }

        return appClient;
    }
}
