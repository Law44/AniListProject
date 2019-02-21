package com.example.anilistproject.animedb;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.anilistproject.model.Character;
import com.example.anilistproject.model.Manga;
import com.example.anilistproject.model.Anime;

@Dao
public  abstract class AnimeDAO {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    public abstract void insert(Anime anime);

    @Query("SELECT * FROM anime WHERE rank != 0 ORDER BY rank ASC LIMIT 50")
    public abstract DataSource.Factory<Integer, Anime> getAllAnimes();

    @Query("SELECT * FROM anime WHERE rank != 0 AND title LIKE :query ORDER BY rank ASC LIMIT 50")
    public abstract DataSource.Factory<Integer, Anime> getAllAnimesQuery(String query);

    public DataSource.Factory<Integer, Anime> getAllAnimes(String query){
        if(query.isEmpty()){
            return getAllAnimes();
        }
        else {
            return getAllAnimesQuery(query);
        }
    }

    @Query("SELECT * FROM anime WHERE mal_id=:mal_id")
    public  abstract Anime getAnime(int mal_id);


/*----------------------Manga------------------------------*/

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    public abstract void insertManga(Manga manga);

    @Query("SELECT * FROM manga WHERE rank != 0 ORDER BY rank ASC LIMIT 50")
    public abstract DataSource.Factory<Integer, Manga> getAllManga();

    @Query("SELECT * FROM manga WHERE rank != 0 AND title LIKE :query ORDER BY rank ASC LIMIT 50")
    public abstract DataSource.Factory<Integer, Manga> getAllMangaQuery(String query);

    public DataSource.Factory<Integer, Manga> getAllManga(String query){
        if(query.isEmpty()){
            return getAllManga();
        }
        else {
            return getAllMangaQuery(query);
        }
    }

    @Query("SELECT * FROM manga WHERE mal_id=:mal_id")
    public abstract Manga getManga(int mal_id);


    /*----------------------Character------------------------------*/

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    public abstract void insertCharacter(Character character);

    @Query("SELECT * FROM character WHERE name NOT like 'Sasuke Uchiha' and name NOT like 'Ichigo Kurosaki'  LIMIT 50  ")
    public abstract DataSource.Factory<Integer, Character> getAllCharacter();

    @Query("SELECT * FROM character WHERE name LIKE :query ORDER BY rank ASC LIMIT 50")
    public abstract DataSource.Factory<Integer, Character> getAllCharacterQuery(String query);

    public DataSource.Factory<Integer, Character> getAllCharacter(String query){
        if(query.isEmpty()){
            return getAllCharacter();
        }
        else {
            return getAllCharacterQuery(query);
        }
    }

    @Query("SELECT * FROM character WHERE mal_id=:mal_id")
    public abstract Character getCharacter(int mal_id);


}
