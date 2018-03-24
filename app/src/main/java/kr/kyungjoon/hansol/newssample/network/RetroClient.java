package kr.kyungjoon.hansol.newssample.network;

import dagger.Module;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.kyungjoon.hansol.newssample.network.api.RetroBaseApiService;
import kr.kyungjoon.hansol.newssample.network.listener.newsApiCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .build();

                    okhttp3.Response response = chain.proceed(request);
                    response.cacheResponse();
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
        apiService.getResponse(country, category, apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }
}
