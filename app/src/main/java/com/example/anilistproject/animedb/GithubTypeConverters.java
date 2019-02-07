package com.example.anilistproject.animedb;

import android.arch.persistence.room.TypeConverter;

import com.example.anilistproject.model.Animeography;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class GithubTypeConverters {

    static Gson gson = new Gson();

    @TypeConverter
    public static List<Animeography> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Animeography>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Animeography> someObjects) {
        return gson.toJson(someObjects);
    }
}
