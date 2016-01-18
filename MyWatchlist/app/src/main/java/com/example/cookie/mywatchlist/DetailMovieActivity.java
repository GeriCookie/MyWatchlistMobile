package com.example.cookie.mywatchlist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookie.mywatchlist.DBModels.LoggedUser;
import com.example.cookie.mywatchlist.DBModels.User;
import com.example.cookie.mywatchlist.Helpers.CurrentUser;
import com.example.cookie.mywatchlist.Helpers.PracticeDatabaseHelper;
import com.squareup.picasso.Picasso;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DetailMovieActivity extends AppCompatActivity {
    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvSynopsis;
    private TextView tvCast;
    private TextView tvAudienceScore;
    private TextView tvCriticsScore;
    private TextView tvCriticsConsensus;
    public static final String MOVIE_DETAIL_KEY = "movie";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivPosterImage = (ImageView) findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvCast = (TextView) findViewById(R.id.tvCast);
        tvCriticsConsensus = (TextView) findViewById(R.id.tvCriticsConsensus);
        tvAudienceScore =  (TextView) findViewById(R.id.tvAudienceScore);
        tvCriticsScore = (TextView) findViewById(R.id.tvCriticsScore);
        // Load movie data
        movie = (Movie) getIntent().getSerializableExtra(AllMoviesActivity.MOVIE_DETAIL_KEY);
        loadMovie(movie);
    }

    public void loadMovie(Movie movie) {
//        if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.HONEYCOMB) {
//            getActionBar().setTitle(movie.getTitle());
//        }
        // Populate data
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText(Html.fromHtml("<b>Critics Score:</b> " + movie.getCriticsScore() + "%"));
        tvAudienceScore.setText(Html.fromHtml("<b>Audience Score:</b> " + movie.getAudienceScore() + "%"));
      //  tvCast.setText(movie.getCastList());
        tvSynopsis.setText(Html.fromHtml("<b>Synopsis:</b> " + movie.getSynopsis()));
        // R.drawable.large_movie_poster from
        // http://content8.flixster.com/movie/11/15/86/11158674_pro.jpg -->
        Picasso.with(this).load(movie.getLargePosterUrl()).
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);
    }

    public void addMovie(View view) {
        PracticeDatabaseHelper dbHelper = new PracticeDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (CurrentUser.getId() == null) {
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra(MOVIE_DETAIL_KEY, movie);
            startActivity(i);
           return;
        }
        else {
            try {
                cupboard().withDatabase(db).put(movie);
            }   catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG);
                toast.show();
            }
            Intent i = new Intent(this, MyWatchListActivity.class);
            startActivity(i);
        }

        Toast toast = Toast.makeText(getApplicationContext(), "At END!", Toast.LENGTH_LONG);
        toast.show();
    }
}
