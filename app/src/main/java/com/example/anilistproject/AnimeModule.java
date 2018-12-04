package com.example.anilistproject;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeModule {
    static AnimeApi moviedbAPI;

    public static AnimeApi getAPI(){
        if(moviedbAPI == null){
            final OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/v3")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            moviedbAPI = retrofit.create(AnimeApi.class);
        }
        return moviedbAPI;
    }
}
