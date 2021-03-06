package com.example.anilistproject.view;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
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
import com.example.anilistproject.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaListAdapter extends PagedListAdapter<Manga, MangaListAdapter.MangaListViewHolder> {
    Context context;
    public MangaListAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MangaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manga, parent, false);
        return new MangaListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MangaListViewHolder holder, int position) {
        final Manga manga = getItem(position);

        holder.title.setText(manga.title);
        holder.score.setText(String.valueOf("Score: " + manga.score));
        GlideApp.with(holder.itemView.getContext()).load( manga.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.placeholder).into(holder.poster);
        holder.rank.setText(String.valueOf("Rank: " + manga.rank));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {

                Intent intent = new Intent(context, MangaActivity.class);
                intent.putExtra("manga" ,manga);

                context.startActivity(intent);

            }
        });
    }

    class MangaListViewHolder extends RecyclerView.ViewHolder {
        TextView title,  score, rank;
        ImageView poster;
        public MangaListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mangaTitle);
            score = itemView.findViewById(R.id.mangascore);
            poster = itemView.findViewById(R.id.mangaImage);
            rank = itemView.findViewById(R.id.mangarank);

        }
    }

    private static DiffUtil.ItemCallback<Manga> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Manga>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Manga oldAnime, Manga newAnime) {
                    return oldAnime.mal_id == newAnime.mal_id;
                }

                @Override
                public boolean areContentsTheSame(Manga oldAnime,
                                                  Manga newAnime) {
                    return oldAnime.equals(newAnime);
                }
            };
}
