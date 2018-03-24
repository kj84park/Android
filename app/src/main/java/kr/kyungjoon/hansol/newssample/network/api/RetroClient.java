package kr.kyungjoon.hansol.newssample.network.api;

import android.util.Log;

import dagger.Module;
import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import kr.kyungjoon.hansol.newssample.network.listener.newsApiCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HANSOL on 2018-03-18.
 */
@Module
public class RetroClient {
    private final String TAG = RetroClient.class.getName();

    private RetroBaseApiService apiService;
    private Retrofit retrofit;

    private static String baseUrl = RetroBaseApiService.BaseUrl;

    private <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public RetroClient() {
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

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = create(RetroBaseApiService.class);
    }

    public void getResponse(String country,String category,String apikey, final newsApiCallback callback) {
        apiService.getResponse(country,category,apikey).enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.code(),response.body());
                } else {
                    Log.d(TAG, "#### error :"+call.request().url().toString());
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                Log.d(TAG,"#### onFailure : "+t.getMessage());
            }
        });
    }

}
