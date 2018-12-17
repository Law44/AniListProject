package com.example.anilistproject.animedb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anilistproject.model.Anime;

import java.util.List;

@Dao
public  interface AnimeDAO {

    @Insert
    void insert(Anime anime);

    @Query("SELECT * FROM anime")
     LiveData<List<Anime>> getAllAnimes();
}
