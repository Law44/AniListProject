package com.example.anilistproject.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

@Entity
public class Animeography {

    public int mal_id;

    public String name;
    public String type;
    public String url;
}
