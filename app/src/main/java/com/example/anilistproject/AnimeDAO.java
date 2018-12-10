package com.example.anilistproject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public  interface AnimeDAO {

    @Insert
    void insert(Anime anime);

    @Query("SELECT * FROM anime")
     LiveData<List<Anime>> getAllAnimes();
}
