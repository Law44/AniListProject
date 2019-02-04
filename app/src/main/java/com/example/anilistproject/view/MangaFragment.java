package com.example.anilistproject.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anilistproject.AnimeViewModel;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Manga;

public class MangaFragment extends Fragment {

    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    public  MangaListAdapter mangaListAdapter;

    PrincipalActivity application;




    public void setApplication(PrincipalActivity application) {
        this.application = application;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View mView = inflater.inflate(R.layout.fragment_manga, container, false);
        mRecyclerView = mView.findViewById(R.id.mangaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(application));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        mangaListAdapter = new MangaListAdapter(application);

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        mViewModel.getTopMangaRating().observe(this, new Observer<PagedList<Manga>>() {
            @Override
            public void onChanged(@Nullable PagedList<Manga> pagedList) {
                mangaListAdapter.mangaList = pagedList;

            }
        });



        mViewModel.timerManga.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mangaListAdapter.notifyDataSetChanged();

            }
        });

        mRecyclerView.setAdapter(mangaListAdapter);





        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mangaListAdapter.notifyDataSetChanged();
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_rank);
//
//
//
//        mRecyclerView = findViewById(R.id.characterList);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
//
//
//        charac = new AnimeListAdapter(RankFragment.this);
//
//        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
//        mViewModel.getTopAnimesRating().observe(this, new Observer<PagedList<Anime>>() {
//            @Override
//            public void onChanged(@Nullable PagedList<Anime> pagedList) {
//                charac.characterList = pagedList;
//
//            }
//        });
//
//        mViewModel.timer.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                charac.notifyDataSetChanged();
//            }
//        });
//
//        mRecyclerView.setAdapter(charac);
//
//    }
}