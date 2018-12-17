package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.anilistproject.animeapi.AnimeApi;
import com.example.anilistproject.animeapi.AnimeModule;
import com.example.anilistproject.animedb.AnimeDAO;
import com.example.anilistproject.animedb.AnimeRoomDatabase;
import com.example.anilistproject.model.Anime;
import com.example.anilistproject.model.AnimesList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeRepository {
    boolean respuesta;
    boolean romper = false;

    private AnimeDAO mAnimeDao;
    public AnimeApi animeAPI;
    Application application;
    public AnimeRepository(Application application){
        animeAPI = AnimeModule.getAPI();
        mAnimeDao = AnimeRoomDatabase.getDatabase(application).animeDAO();
    }

    public LiveData<List<Anime>> getTopAnimesRating(){
        respuesta = true;

        final MutableLiveData<List<Anime>> lista = new MutableLiveData<>();

        lista.postValue(new ArrayList<Anime>());
        int cont = 999;
        while (respuesta) {
            cont++;

            animeAPI.getTopAnimesRating(cont).enqueue(new Callback<AnimesList>() {
                @Override
                public void onResponse(Call<AnimesList> call, Response<AnimesList> response) {

                    if (response.body() != null && response.body().error == null) {
                        List<Anime> newList = new ArrayList<>();
                        newList.addAll(lista.getValue());
                        newList.addAll(response.body().top);
                        Log.e("ERROR", "ggg");
                        lista.postValue(newList);

                    } else {
                        respuesta = false;
                    }

                }

                @Override
                public void onFailure(Call<AnimesList> call, Throwable t) {
                }
            });
        }
//            if (romper){
//                break;
//            }
//        }

        return lista;
    }
}
