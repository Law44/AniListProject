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
import com.example.anilistproject.model.Character;
import com.example.anilistproject.model.Manga;

import java.util.ArrayList;
import java.util.List;

public class CharacterListAdapter extends PagedListAdapter<Character, CharacterListAdapter.CharacterListViewHolder> {
    Context context;

    public CharacterListAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterListAdapter.CharacterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterListAdapter.CharacterListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CharacterListAdapter.CharacterListViewHolder holder, int position) {
        final Character chara = getItem(position);

        holder.title.setText(chara.name);
        holder.anime.setText(chara.animeography.get(0).name);
        GlideApp.with(holder.itemView.getContext()).load( chara.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.placeholder).into(holder.poster);

        holder.rank.setText(String.valueOf("Rank: " + (position+1)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {

                Intent intent = new Intent(context, CharacterActivity.class);
                intent.putExtra("name" ,chara.name);
                intent.putExtra("anime" ,chara.animeography.get(0).name);
                intent.putExtra("image",chara.image_url);
                intent.putExtra("rank",holder.rank.getText().toString());
                intent.putExtra("synopsis",chara.about);

                context.startActivity(intent);

            }
        });
    }


    class CharacterListViewHolder extends RecyclerView.ViewHolder {
        TextView title,   rank, anime;
        ImageView poster;
        public CharacterListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.characterTitle);
            anime = itemView.findViewById(R.id.characterAnime);
            poster = itemView.findViewById(R.id.characterImage);
            rank = itemView.findViewById(R.id.characterRank);

        }
    }

    private static DiffUtil.ItemCallback<Character> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Character>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Character oldAnime, Character newAnime) {
                    return oldAnime.mal_id == newAnime.mal_id;
                }

                @Override
                public boolean areContentsTheSame(Character oldAnime,
                                                  Character newAnime) {
                    return oldAnime.equals(newAnime);
                }
            };
}
