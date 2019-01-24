package com.example.anilistproject.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anilistproject.AnimeViewModel;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Anime;

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        animeListAdapter = new AnimeListAdapter(MainActivity.this);

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        mViewModel.getTopAnimesRating().observe(this, new Observer<PagedList<Anime>>() {
            @Override
            public void onChanged(@Nullable PagedList<Anime> pagedList) {
                animeListAdapter.animeList = pagedList;
            }
        });
        mRecyclerView.setAdapter(animeListAdapter);

    }
}