package com.example.anilistproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface AnimeApi {


    @GET("top/anime/{animeid}/favorite")
    Call<AnimesList> getTopAnimesRating(@Path("animeid") int animeid);
}
