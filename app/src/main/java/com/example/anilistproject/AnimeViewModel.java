package com.example.anilistproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class AnimeViewModel extends AndroidViewModel {
    private AnimeRepository animeRepository;

    public AnimeViewModel(@NonNull Application application) {
        super(application);
        animeRepository = new AnimeRepository();
    }

    public LiveData<List<Anime>> getTopAnimesRating(){ return animeRepository.getTopAnimesRating(); }
}
