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
import com.example.anilistproject.model.Anime;


public class AnimeFragment extends Fragment
implements PrincipalActivity.QueryChangeListener {

    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    public  AnimeListAdapter animeListAdapter;
    Observer observer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View mView = inflater.inflate(R.layout.fragment_rank, container, false);

        mRecyclerView = mView.findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        animeListAdapter = new AnimeListAdapter(getActivity());

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        observer = new Observer<PagedList<Anime>>() {
            @Override
            public void onChanged(@Nullable PagedList<Anime> pagedList) {
                animeListAdapter.submitList(pagedList);
            }
        };

        mViewModel.getTopAnimesRating("").observe(this, observer);

        mRecyclerView.setAdapter(animeListAdapter);

        return mView;
    }

    public void onQueryChange(String query){
        mViewModel.getTopAnimesRating("").removeObserver(observer);

        mViewModel.getTopAnimesRating("%" + query + "%").observe(this, observer);

        if (query.equals("")){
            mRecyclerView.scrollToPosition(animeListAdapter.getItemCount()-1);
            mRecyclerView.scrollToPosition(0);
        }
    }
}