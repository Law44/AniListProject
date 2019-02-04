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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anilistproject.AnimeViewModel;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Character;


public class CharacterFragment extends Fragment {
    private AnimeViewModel mViewModel;
    private RecyclerView mRecyclerView;
    public  CharacterListAdapter charac;

    PrincipalActivity application;




    public void setApplication(PrincipalActivity application) {
        this.application = application;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View mView = inflater.inflate(R.layout.fragment_character, container, false);
        mRecyclerView = mView.findViewById(R.id.characterList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(application));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        charac = new CharacterListAdapter(application);

        mViewModel = ViewModelProviders.of(this).get(AnimeViewModel.class);
        mViewModel.getTopCharacterRating().observe(this, new Observer<PagedList<Character>>() {
            @Override
            public void onChanged(@Nullable PagedList<Character> pagedList) {
                charac.characterList = pagedList;

            }
        });


        mViewModel.timerX.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                charac.notifyDataSetChanged();

            }
        });

        mRecyclerView.setAdapter(charac);


        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ABC", "onSTART");
        charac.notifyDataSetChanged();
    }


}
