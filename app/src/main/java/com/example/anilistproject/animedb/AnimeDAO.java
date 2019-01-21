package com.example.anilistproject.animedb;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.anilistproject.model.Anime;

import java.util.List;

@Dao
public  interface AnimeDAO {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void insert(Anime anime);

    @Query("SELECT * FROM anime ORDER BY score DESC")
    DataSource.Factory<Integer, Anime> getAllAnimes();

    @Query("SELECT * FROM anime WHERE mal_id=:mal_id")
    Anime getAnime(int mal_id);
}
