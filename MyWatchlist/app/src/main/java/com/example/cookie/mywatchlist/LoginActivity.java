package com.example.cookie.mywatchlist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cookie.mywatchlist.DBModels.LoggedUser;
import com.example.cookie.mywatchlist.DBModels.User;
import com.example.cookie.mywatchlist.Helpers.CurrentUser;
import com.example.cookie.mywatchlist.Helpers.PracticeDatabaseHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        PracticeDatabaseHelper dbHelper = new PracticeDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, AllMoviesActivity.class);

        EditText loginText = (EditText) findViewById(R.id.login_name_text);
        EditText passwordText = (EditText) findViewById(R.id.login_password_text);

        String username = loginText.getText().toString();
        String password = passwordText.getText().toString();

        User user = null;

        try {
            user = cupboard().withDatabase(db).query(User.class).withSelection("name = ? AND password = ?", username, password).get();
        } catch (Exception e) {

        }

        if (user == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        cacheUserId(user._id);
        startActivity(intent);
    }

    public void registerUser(View view) {
        Intent intent = new Intent(this, AllMoviesActivity.class);

        EditText loginText = (EditText) findViewById(R.id.login_name_text);
        EditText passwordText = (EditText) findViewById(R.id.login_password_text);

        String username = loginText.getText().toString();
        String password = passwordText.getText().toString();

        User user = null;

        try {
            user = cupboard().withDatabase(db).query(User.class).withSelection("name = ?", username).get();
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "User doesnt exist :)", Toast.LENGTH_LONG);
            toast.show();
        }

        if (user != null) {
            Toast toast = Toast.makeText(getApplicationContext(), "User with that name already exists!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        Long id = cupboard().withDatabase(db).put(new User(username, password));
        cacheUserId(id);

        startActivity(intent);
    }

    public void cacheUserId(Long id) {
        cupboard().withDatabase(db).put(new LoggedUser(id));
        CurrentUser.setId(id);
    }
}
