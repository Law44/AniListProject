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
import com.example.anilistproject.model.Character;
import com.example.anilistproject.model.CharacterList;
import com.example.anilistproject.model.Manga;
import com.example.anilistproject.model.MangaList;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeRepository {

    private AnimeDAO mAnimeDao;
    public AnimeApi animeAPI;

    private final Executor executor = Executors.newFixedThreadPool(2);
    public LiveData<PagedList<Anime>> animeList;
    public LiveData<PagedList<Manga>> mangaList;
    public LiveData<PagedList<Character>> characterList;


    public AnimeRepository(Application application){
        animeAPI = AnimeModule.getAPI();
        mAnimeDao = AnimeRoomDatabase.getDatabase(application).animeDAO();
    }

    public LiveData<PagedList<Anime>> getTopAnimesRating(String query){
        refreshAnimeList();
        PagedList.Config conf = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(100).setPageSize(10).build();
        animeList = new LivePagedListBuilder<>(
                mAnimeDao.getAllAnimes(query), /* page size */ conf).build();
        return animeList;
    }

    public void refreshAnimeList(){

        for (int i = 1; i<3; i++) {
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


    /*------------------------------Manga-----------------------*/
    public LiveData<PagedList<Manga>> getTopMangaRating(String query){
        refreshMangaList();
        PagedList.Config conf = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(100).setPageSize(10).build();
        mangaList = new LivePagedListBuilder<>(
                mAnimeDao.getAllManga(query), /* page size */ conf).build();
        return mangaList;
    }

    private void refreshMangaList() {

        for (int i = 1; i<3; i++) {
            animeAPI.getTopMangasRating(i).enqueue(new Callback<MangaList>() {
                @Override
                public void onResponse(Call<MangaList> call, final Response<MangaList> response) {
                    if(response.body()!=null) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {

                                for(Manga manga : response.body().top){
                                    updateManga(manga);
                                }
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<MangaList> call, Throwable t) {
                }
            });

        }
    }

    public void updateManga (final Manga manga){
        if (manga!=null) {

            Manga mangaFromDb = mAnimeDao.getManga(manga.mal_id);

            if (mangaFromDb == null) {

                animeAPI.getManga(manga.mal_id).enqueue(new Callback<Manga>() {
                    @Override
                    public void onResponse(Call<Manga> call, final Response<Manga> response) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if(response.body()!=null) {
                                    mAnimeDao.insertManga(response.body());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Manga> call, Throwable t) {
                    }
                });
            }
        }
    }

    /*------------------------------Character-----------------------*/
    public LiveData<PagedList<Character>> getTopCharacterRating(String query){
        refreshCharacterList();
        PagedList.Config conf = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(100).setPageSize(10).build();
        characterList = new LivePagedListBuilder<>(
                mAnimeDao.getAllCharacter(query), /* page size */ conf).build();
        return characterList;
    }

    private void refreshCharacterList() {

        for (int i = 1; i<3; i++) {
            animeAPI.getTopCharacterRating(i).enqueue(new Callback<CharacterList>() {
                @Override
                public void onResponse(Call<CharacterList> call, final Response<CharacterList> response) {
                    if(response.body()!=null) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                for(Character character : response.body().top){
                                    updateCharacter(character);
                                }
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Call<CharacterList> call, Throwable t) {
                }
            });

        }


    }

    public void updateCharacter (final Character character){
        if (character!=null) {

            Character daoCharacter = mAnimeDao.getCharacter(character.mal_id);

            if (daoCharacter == null) {

                animeAPI.getCharacter(character.mal_id).enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, final Response<Character> response) {
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if(response.body()!=null) {
                                    mAnimeDao.insertCharacter(response.body());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {
                    }
                });
            }
        }
    }
}
