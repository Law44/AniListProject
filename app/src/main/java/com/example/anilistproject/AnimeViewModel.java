package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.anilistproject.model.Anime;
import com.example.anilistproject.model.Character;
import com.example.anilistproject.model.Manga;

import java.util.List;

public class AnimeViewModel extends AndroidViewModel {
    private AnimeRepository animeRepository;

//    public  MutableLiveData<Boolean> timerAnime = new MutableLiveData<>();
//    public  MutableLiveData<Boolean> timerManga = new MutableLiveData<>();
//    public  MutableLiveData<Boolean> timerX = new MutableLiveData<>();


    public AnimeViewModel(@NonNull Application application) {
        super(application);
        animeRepository = new AnimeRepository(application);
//        timerAnime.postValue(true);
//        timerManga.postValue(true);
//        timerX.postValue(true);
//        startTimer();
    }

//    void startTimer(){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                timerAnime.postValue(!timerAnime.getValue());
//                timerManga.postValue(!timerManga.getValue());
//                timerX.postValue(!timerX.getValue());
//            }
//        }, 50);
//    }

    public LiveData<PagedList<Anime>> getTopAnimesRating(String query){ return animeRepository.getTopAnimesRating(query); }

    public LiveData<PagedList<Manga>> getTopMangaRating(String query){ return animeRepository.getTopMangaRating(query); }

    public LiveData<PagedList<Character>> getTopCharacterRating(String query){ return animeRepository.getTopCharacterRating(query); }

}
