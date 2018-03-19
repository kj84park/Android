package kr.kyungjoon.hansol.newssample.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HANSOL on 2018-03-14.
 */

@Module
public class MainModule {

    public String BaseUrl = "https://newsapi.org/";

    @Provides
    @Singleton
    public Retrofit getRetrofit(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor( chain -> {
                    Request original = chain.request();
                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .build();

                    okhttp3.Response response = chain.proceed(request);
                    response.cacheResponse();
                    // Customize or return the response
                    return response;
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
