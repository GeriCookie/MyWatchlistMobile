package com.example.cookie.mywatchlist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.cookie.mywatchlist.Helpers.PracticeDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MyWatchListActivity extends AppCompatActivity {
    private ListView lvMovies;
    private MoviesAdapter adapterMovies;
    private MoviesClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_watch_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PracticeDatabaseHelper dbHelper = new PracticeDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        List<Movie> movies = cupboard().withDatabase(db).query(Movie.class).list();


        lvMovies = (ListView) findViewById(R.id.mylvMovies);
        ArrayList<Movie> aMovies = new ArrayList<Movie>(movies);
        adapterMovies = new MoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);


    }

}
