package com.example.movieapp.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.model.Movie;
import com.example.movieapp.R;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "";

    private Movie movie;
    private TextView tvTitle, tvDescription;
    private ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.info_detail_text);
        tvDescription = findViewById(R.id.info_detail_description);
        ivPoster = findViewById(R.id.info_detail_image);

        movie = (Movie) getIntent().getExtras().get(EXTRA_MOVIE_ID);

        tvTitle.setText(movie.getTitle());
        tvDescription.setText(movie.getOverview());
        Glide.with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/w342" + movie.getPosterPath())
                .into(ivPoster);
    }
}
