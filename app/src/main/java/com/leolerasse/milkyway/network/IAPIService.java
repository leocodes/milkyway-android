package com.leolerasse.milkyway.network;

import com.leolerasse.milkyway.network.models.Collection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIService {

    @GET("search")
    Call<List<Collection>> getImages(
            @Path("q") String query,
            @Path("media_type") String mediaType,
            @Path("year_start") int yearStart,
            @Path("year_end") int yearEnd
    );
}
