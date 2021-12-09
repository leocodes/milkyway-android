package com.leolerasse.milkyway.network;

import com.leolerasse.milkyway.network.models.APIResponse;
import com.leolerasse.milkyway.network.models.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPIService {

    @GET("search")
    Call<APIResponse> getImages(
            @Query("q") String query,
            @Query("media_type") String mediaType,
            @Query("year_start") int yearStart,
            @Query("year_end") int yearEnd
    );
}
