package com.example.anilistproject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Anime {
    @PrimaryKey(autoGenerate = true)
    public int animeid;

    public String title;
    public String image_url;

}
