package com.example.anilistproject.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anilistproject.AnimeViewModel;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Anime;


public class RankFragment extends Fragment {

    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    public  AnimeListAdapter animeListAdapter;





    PrincipalActivity application;

    public void setApplication(PrincipalActivity application) {
        this.application = application;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.e("CICLO","OnCreateView");


        View mView = inflater.inflate(R.layout.fragment_rank, container, false);
        mRecyclerView = mView.findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(application));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        animeListAdapter = new AnimeListAdapter();


        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        mViewModel.getTopAnimesRating().observe(this, new Observer<PagedList<Anime>>() {
            @Override
            public void onChanged(@Nullable PagedList<Anime> pagedList) {
                animeListAdapter.submitList(pagedList);
            }
        });




        mRecyclerView.setAdapter(animeListAdapter);





        return mView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.e("CICLO","OnDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("CICLO","OnDestroy");
    }



}