package com.example.anilistproject.animedb;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.anilistproject.model.Manga;
import com.example.anilistproject.model.Anime;

@Dao
public  interface AnimeDAO {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void insert(Anime anime);

    @Query("SELECT * FROM anime WHERE rank != 0 ORDER BY rank ASC LIMIT 50")
    DataSource.Factory<Integer, Anime> getAllAnimes();

    @Query("SELECT * FROM anime WHERE mal_id=:mal_id")
    Anime getAnime(int mal_id);


/*----------------------Manga------------------------------*/

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    void insertManga(Manga manga);

    @Query("SELECT * FROM manga WHERE rank != 0 ORDER BY rank ASC LIMIT 50")
    DataSource.Factory<Integer, Manga> getAllManga();

    @Query("SELECT * FROM manga WHERE mal_id=:mal_id")
    Manga getManga(int mal_id);

}
