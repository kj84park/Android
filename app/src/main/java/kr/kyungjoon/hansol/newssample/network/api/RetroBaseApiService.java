package kr.kyungjoon.hansol.newssample.network.api;

import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HANSOL on 2018-03-18.
 */

public interface RetroBaseApiService {

    public final String BaseUrl = "https://newsapi.org/";

   @GET("/v2/top-headlines")
    Call<GetResponse> getResponse(@Query("country") String country,@Query("apiKey") String apiKey);
}
