package com.example.cookie.mywatchlist.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cookie.mywatchlist.DBModels.LoggedUser;
import com.example.cookie.mywatchlist.DBModels.User;
import com.example.cookie.mywatchlist.Movie;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class PracticeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyWatchList.db";
    private static final int DATABASE_VERSION = 2;

    public PracticeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(User.class);
        cupboard().register(LoggedUser.class);
        cupboard().register(Movie.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
