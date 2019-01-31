package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.anilistproject.model.Anime;
import com.example.anilistproject.model.Manga;

import java.util.List;

public class AnimeViewModel extends AndroidViewModel {
    private AnimeRepository animeRepository;

    public  MutableLiveData<Boolean> timer = new MutableLiveData<>();

    public AnimeViewModel(@NonNull Application application) {
        super(application);
        animeRepository = new AnimeRepository(application);
        timer.postValue(true);
        startTimer();
    }

    void startTimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.postValue(!timer.getValue());
            }
        }, 1000);
    }

    public LiveData<PagedList<Anime>> getTopAnimesRating(){ return animeRepository.getTopAnimesRating(); }
    public LiveData<PagedList<Manga>> getTopMangaRating(){ return animeRepository.getTopMangaRating(); }
}
