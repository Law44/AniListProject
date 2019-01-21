package com.example.anilistproject.animedb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anilistproject.model.Anime;

@Database(entities = {Anime.class}, version = 3)
public abstract class AnimeRoomDatabase extends RoomDatabase {

    public abstract AnimeDAO animeDAO();

    private static volatile AnimeRoomDatabase INSTANCE;

    public static AnimeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AnimeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AnimeRoomDatabase.class, "anime_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
