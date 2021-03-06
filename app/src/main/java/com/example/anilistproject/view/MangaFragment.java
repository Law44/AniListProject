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

public class MangaFragment extends Fragment implements PrincipalActivity.QueryChangeListener {

    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    public  MangaListAdapter mangaListAdapter;
    Observer observer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View mView = inflater.inflate(R.layout.fragment_manga, container, false);
        mRecyclerView = mView.findViewById(R.id.mangaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        mangaListAdapter = new MangaListAdapter(getActivity());

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        observer = new Observer<PagedList<Manga>>() {
            @Override
            public void onChanged(@Nullable PagedList<Manga> pagedList) {
                mangaListAdapter.submitList(pagedList);
            }
        };
        mViewModel.getTopMangaRating("").observe(this, observer);

        mRecyclerView.setAdapter(mangaListAdapter);

        return mView;
    }

    public void onQueryChange(String query){
        mViewModel.getTopMangaRating("").removeObserver(observer);

        mViewModel.getTopMangaRating("%" + query + "%").observe(this, observer);
        if (query.equals("")){
            mRecyclerView.scrollToPosition(mangaListAdapter.getItemCount()-1);
            mRecyclerView.scrollToPosition(0);
        }
    }
}