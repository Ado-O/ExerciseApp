package com.tech387.wokroutapp.data.storage.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String sBaseUrl = "http://staging.api.mobile.diamondappgroup.com/";

    private static final boolean mLoggingEnabled = true;

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(sBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit = sBuilder.build();

    private static OkHttpClient.Builder sHttpClientBuilder =
            new OkHttpClient
                    .Builder()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .writeTimeout(45, TimeUnit.SECONDS);

    /**
     * Logging Interceptor
     */
    private static HttpLoggingInterceptor sLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    public static <S> S createService(Class<S> serviceClass) {
        setupLogging();

        return sRetrofit.create(serviceClass);
    }

    private static void setupLogging() {
        if (mLoggingEnabled && !sHttpClientBuilder.interceptors().contains(sLoggingInterceptor)) {
            sHttpClientBuilder.addInterceptor(sLoggingInterceptor);
            sBuilder.client(sHttpClientBuilder.build());
            sRetrofit = sBuilder.build();
        } else if (sHttpClientBuilder.interceptors().contains(sLoggingInterceptor)) {
            sHttpClientBuilder.interceptors().remove(sLoggingInterceptor);
            sRetrofit = sBuilder.build();
        }
    }
}
