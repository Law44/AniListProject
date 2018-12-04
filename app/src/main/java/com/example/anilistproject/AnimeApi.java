package com.example.anilistproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface AnimeApi {

    @GET("/top/anime/1/favorite")
    Call<AnimesList> getTopAnimesRating();
    }
