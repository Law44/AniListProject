package com.example.anilistproject.animeapi;

import com.example.anilistproject.model.Anime;
import com.example.anilistproject.model.AnimesList;
import com.example.anilistproject.model.Manga;
import com.example.anilistproject.model.MangaList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface AnimeApi {


    @GET("top/anime/{mal_id}/favorite")
    Call<AnimesList> getTopAnimesRating(@Path("mal_id") int animeid);

    @GET("anime/{mal_id}")
    Call<Anime> getAnime(@Path("mal_id") int mal_id);

    @GET("top/manga/{mal_id}/favorite")
    Call<MangaList> getTopMangasRating(@Path("mal_id") int mangaid);

    @GET("manga/{mal_id}")
    Call<Manga> getManga(@Path("mal_id") int mal_id);



}
