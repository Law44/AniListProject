package com.example.anilistproject;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeRepository {

    AnimeApi animeAPI;

    public AnimeRepository(){
        animeAPI = AnimeModule.getAPI();
    }

    public LiveData<List<Anime>> getTopAnimesRating(){
        final MutableLiveData<List<Anime>> lista = new MutableLiveData<>();

        animeAPI.getTopAnimesRating().enqueue(new Callback<AnimesList>() {

            @Override
            public void onResponse(Call<AnimesList> call, Response<AnimesList> response) {
                lista.setValue(response.body().top);
            }

            @Override
            public void onFailure(Call<AnimesList> call, Throwable t) {
            }
        });

        return lista;
    }
}
