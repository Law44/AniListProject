package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeRepository {

    private AnimeDAO mAnimeDao;
    public AnimeApi animeAPI;
    Application application;
    public AnimeRepository(Application application){
        animeAPI = AnimeModule.getAPI();
        mAnimeDao = AnimeRoomDatabase.getDatabase(application).animeDAO();
    }

    public LiveData<List<Anime>> getTopAnimesRating(){

        final MutableLiveData<List<Anime>> lista = new MutableLiveData<>();


        lista.postValue(new ArrayList<Anime>());

        for (int i = 1;i<5; i++) {



            animeAPI.getTopAnimesRating(i).enqueue(new Callback<AnimesList>() {
                @Override
                public void onResponse(Call<AnimesList> call, Response<AnimesList> response) {
                    List<Anime> newList = new ArrayList<>();
                    newList.addAll(lista.getValue());
                    newList.addAll(response.body().top);

                    lista.postValue(newList);

//                    lista.getValue().addAll(response.body().top);
                }

                @Override
                public void onFailure(Call<AnimesList> call, Throwable t) {
                }
            });
        }
        return lista;
    }
}
