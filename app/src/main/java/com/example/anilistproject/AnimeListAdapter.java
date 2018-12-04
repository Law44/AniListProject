package com.example.anilistproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.MovieListViewHolder>{

    public List<Anime> animeList = new ArrayList<>();

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Anime anime = animeList.get(position);

        holder.title.setText(anime.title);
        GlideApp.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + anime.poster_path).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        public MovieListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.animeTitle);
            poster = itemView.findViewById(R.id.animeImage);
        }
    }
}
