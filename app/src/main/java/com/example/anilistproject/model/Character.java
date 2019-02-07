package com.example.anilistproject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Character {
    @PrimaryKey()
    @NonNull
    public int mal_id;

    public String title;
    public String image_url;
    //public float score;

    public int rank;


}