package com.example.anilistproject.view;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.anilistproject.GlideApp;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Anime;

import java.util.List;

public class AnimeListAdapter extends PagedListAdapter<Anime, AnimeListAdapter.AnimeListViewHolder> {

    public List<Anime> animeList ;

    MainActivity activity;

    public  AnimeListAdapter (MainActivity activity){
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public AnimeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
        return new AnimeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeListViewHolder holder, int position) {
        Anime anime = animeList.get(position);


        holder.title.setText(anime.title);
        GlideApp.with(holder.itemView.getContext()).load( anime.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return (animeList != null ? animeList.size() : 0);
    }

    class AnimeListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        public AnimeListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.animeTitle);
            poster = itemView.findViewById(R.id.animeImage);
        }
    }

    private static DiffUtil.ItemCallback<Anime> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Anime>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Anime oldAnime, Anime newAnime) {
                    return oldAnime.mal_id == newAnime.mal_id;
                }

                @Override
                public boolean areContentsTheSame(Anime oldAnime,
                                                  Anime newAnime) {
                    return oldAnime.equals(newAnime);
                }
            };
}
