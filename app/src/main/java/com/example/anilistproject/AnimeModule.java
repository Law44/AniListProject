package com.example.anilistproject;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeModule {
    static AnimeApi animeApi;

    public static AnimeApi getAPI(){
        if(animeApi == null){
            final OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/v3/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            animeApi = retrofit.create(AnimeApi.class);
        }
        return animeApi;
    }
}
