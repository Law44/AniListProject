package com.example.anilistproject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Anime {
    @PrimaryKey()
    @NonNull
    public int mal_id;

    public String title;
    public String image_url = "drawable/placeholder.jpg";
    public String score;

}
