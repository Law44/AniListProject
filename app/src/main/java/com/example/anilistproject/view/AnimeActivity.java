package com.example.anilistproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.anilistproject.GlideApp;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Anime;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        TextView title = findViewById(R.id.animeInfoTitle);
        TextView score = findViewById(R.id.Infoscore);
        TextView rank = findViewById(R.id.Inforank);
        TextView episodes = findViewById(R.id.Infoepisodes);
        ImageView image = findViewById(R.id.animeInfoImage);
        TextView syinopsis = findViewById(R.id.InfoSypnosis);

        Bundle bundle = getIntent().getExtras();
        Anime anime = (Anime) bundle.get("anime");

        title.setText(anime.title);
        score.setText(String.valueOf(anime.score));
        rank.setText("Rank: "+String.valueOf(anime.rank));
        episodes.setText(bundle.get("episodes").toString());
        GlideApp.with(image.getContext()).load( anime.image_url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.placeholder).into(image);
        syinopsis.setText(anime.synopsis);
    }
}
