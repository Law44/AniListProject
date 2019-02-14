package com.example.anilistproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.anilistproject.GlideApp;
import com.example.anilistproject.R;
import com.example.anilistproject.model.Character;
import com.example.anilistproject.model.Manga;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        TextView title = findViewById(R.id.animeInfoTitle);
        TextView score = findViewById(R.id.Infoscore);
        TextView rank = findViewById(R.id.Inforank);

        ImageView image = findViewById(R.id.animeInfoImage);
        TextView syinopsis = findViewById(R.id.InfoSypnosis);

        Bundle bundle = getIntent().getExtras();


        title.setText(bundle.get("name").toString());
        score.setText(bundle.get("anime").toString());
        rank.setText(bundle.get("rank").toString());


        GlideApp.with(image.getContext()).load( bundle.get("image")).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.placeholder).into(image);
        syinopsis.setText(bundle.get("synopsis").toString());
    }
}
