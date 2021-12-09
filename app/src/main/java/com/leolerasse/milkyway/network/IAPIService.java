package com.leolerasse.milkyway.network;

import com.leolerasse.milkyway.network.models.APIResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAPIService {

    @GET("search")
    Single<APIResponse> getImages(
            @Query("q") String query,
            @Query("media_type") String mediaType,
            @Query("year_start") int yearStart,
            @Query("year_end") int yearEnd
    );
}
