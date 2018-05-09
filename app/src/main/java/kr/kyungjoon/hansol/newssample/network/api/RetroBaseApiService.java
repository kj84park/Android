package kr.kyungjoon.hansol.newssample.network.api;

import io.reactivex.Observable;
import kr.kyungjoon.hansol.newssample.network.dto.GetResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HANSOL on 2018-03-18.
 */

public interface RetroBaseApiService {

    public final String baseUrl = "https://newsapi.org/";

   @GET("/v2/top-headlines")
   Observable<GetResponse> getResponse(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);
}
