package com.example.anilistproject.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Manga  implements Serializable {
    @PrimaryKey()
    @NonNull
    public int mal_id;

    public String title;
    public String image_url;
    public float score;

    public int rank;
    public String type;
    public String synopsis;

}