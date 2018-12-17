package com.example.anilistproject.animeapi;

import com.example.anilistproject.model.AnimesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface AnimeApi {


    @GET("top/anime/{animeid}/favorite")
    Call<AnimesList> getTopAnimesRating(@Path("animeid") int animeid);
}
