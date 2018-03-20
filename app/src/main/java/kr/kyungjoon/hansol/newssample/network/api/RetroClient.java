package kr.kyungjoon.hansol.newssample.network.api;

import android.content.Context;
import android.util.Log;
import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HANSOL on 2018-03-18.
 */

public class RetroClient {
    public final String TAG = RetroClient.class.getName();

    static Context mContext;
    static RetroBaseApiService apiService;
    static Retrofit retrofit;

    public static String baseUrl = RetroBaseApiService.BaseUrl;

    private static class SingletonHolder {
        private static RetroClient INSTANCE = new RetroClient(mContext);
    }

    public static RetroClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    public  <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    private RetroClient(Context context) {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build();
    }

    public RetroClient createBaseApi() {
        apiService = create(RetroBaseApiService.class);
        return this;
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
                //callback.onError(t);
            }


        });
    }

}
