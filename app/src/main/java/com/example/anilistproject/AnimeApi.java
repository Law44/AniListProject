package com.example.anilistproject;

import retrofit2.Call;
import retrofit2.http.POST;


public interface AnimeApi {
    @POST("/3/discover/movie")
    Call<AnimesList> getMovies();


}
