package com.example.xinyuxin.Config;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import okhttp3.OkHttpClient;

public class Config {
    public static String ServerUrl="http://120.79.69.218/androidapi/" ;
    public static String localUrl="http://192.168.0.59:808/androidapi/" ;
    public static void cookieConfig(Context context){
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(context));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
