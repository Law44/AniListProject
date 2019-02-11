package com.example.anilistproject.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.anilistproject.animedb.GithubTypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Character implements Serializable {
    @PrimaryKey()
    @NonNull
    public int mal_id;

    public String title;
    public String image_url;
    public int rank;
    public String name;
    public String animeName;
    public String about;

    @TypeConverters(GithubTypeConverters.class)
    public List<Animeography> animeography;


}