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


        for (int i = 1; i<1000; i++)
          {



            animeAPI.getTopAnimesRating(i).enqueue(new Callback<AnimesList>() {
                @Override
                public void onResponse(Call<AnimesList> call, Response<AnimesList> response) {

                    if(response.body()!=null) {
                        List<Anime> newList = new ArrayList<>();
                        newList.addAll(lista.getValue());
                        newList.addAll(response.body().top);

//                        for (int j = 0; j < newList.size(); j++) {
//                            mAnimeDao.insert(newList.get(j));
//                        }

                        lista.postValue(newList);

                    }else{
                        respuesta = false;
                    }

//                    lista.getValue().addAll(response.body().top);
                }

                @Override
                public void onFailure(Call<AnimesList> call, Throwable t) {
                }
            });

            if (!respuesta){
                break;
            }
        }

        return lista;
    }
}
