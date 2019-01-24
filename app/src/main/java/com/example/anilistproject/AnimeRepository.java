package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;


import com.example.anilistproject.animeapi.AnimeApi;
import com.example.anilistproject.animeapi.AnimeModule;
import com.example.anilistproject.animedb.AnimeDAO;
import com.example.anilistproject.animedb.AnimeRoomDatabase;
import com.example.anilistproject.model.Anime;
import com.example.anilistproject.model.AnimesList;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeRepository {
    boolean respuesta;

    private AnimeDAO mAnimeDao;
    public AnimeApi animeAPI;
    Application application;
    private final Executor executor = Executors.newFixedThreadPool(2);
    public LiveData<PagedList<Anime>> animeList;


    public AnimeRepository(Application application){
        animeAPI = AnimeModule.getAPI();
        mAnimeDao = AnimeRoomDatabase.getDatabase(application).animeDAO();

    }

    public LiveData<PagedList<Anime>> getTopAnimesRating(){
        refreshAnimeList();
        PagedList.Config conf = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(100).setPageSize(10).build();
        animeList = new LivePagedListBuilder<>(
                mAnimeDao.getAllAnimes(), /* page size */ conf).build();
        return animeList;
    }

    public void refreshAnimeList(){

        for (int i = 1; i<50; i++) {
            animeAPI.getTopAnimesRating(i).enqueue(new Callback<AnimesList>() {
                @Override
                public void onResponse(Call<AnimesList> call, final Response<AnimesList> response) {
                    if(response.body()!=null) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                for(Anime anime : response.body().top){
                                    updateAnime(anime);
                                }
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<AnimesList> call, Throwable t) {
                }
            });

        }


    }

    public void updateAnime (final Anime anime){
        if (anime!=null) {

            Anime animeFromDB = mAnimeDao.getAnime(anime.mal_id);

            if (animeFromDB == null) {

                animeAPI.getAnime(anime.mal_id).enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, final Response<Anime> response) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if(response.body()!=null) {
                                    mAnimeDao.insert(response.body());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {
                    }
                });
            }
        }
    }
}
