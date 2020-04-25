package com.fastcon.producttoexcelscanner.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.fastcon.producttoexcelscanner.commons.Utils.BaseUrl;


public class ApiClient {
    private static final int REQUEST_TIMEOUT = 60;

    public Retrofit getClient(Context context) {
        Gson gSon = new GsonBuilder().create();

        return new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(initOkHttp(context))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gSon))
                .build();
    }

    private OkHttpClient initOkHttp(Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        httpClient.dispatcher(dispatcher);


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        httpClient.addInterceptor(interceptor);
        return httpClient.build();
    }

}