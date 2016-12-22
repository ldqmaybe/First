package com.cn.baselib.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Admin
 * @time 2016/11/24 0024.14:23
 */
public class RetrofitHttp {
    private static final int DEFAULT_TIMEOUT = 5;
    private final Retrofit retrofit;

    private static class SingleHolder {
        private static final RetrofitHttp INSTANCE = new RetrofitHttp();
    }

    public static RetrofitHttp getInstance() {
        return SingleHolder.INSTANCE;
    }

    private RetrofitHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new LoggerInterceptor())//添加拦截器
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://yuecaninfo.com/services/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
