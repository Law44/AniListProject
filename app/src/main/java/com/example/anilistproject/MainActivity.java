package com.example.anilistproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private AnimeListAdapter animeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        animeListAdapter = new AnimeListAdapter();
        mRecyclerView.setAdapter(animeListAdapter);

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        mViewModel.getTopAnimesRating().observe(this, new Observer<List<Anime>>() {
            @Override
            public void onChanged(@Nullable List<Anime> animes) {
                animeListAdapter.animeList = animes;
                animeListAdapter.notifyDataSetChanged();
            }
        });
    }
}