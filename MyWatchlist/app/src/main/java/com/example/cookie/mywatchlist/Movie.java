package com.example.cookie.mywatchlist;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cookie on 1/17/16.
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = -8959832007991513854L;
    private String title;
    private int year;
    private String synopsis;
    private String posterUrl;
    private String largePosterUrl;
    private String criticsConsensus;
    private int audienceScore;

    private int criticsScore;
    private ArrayList<String> castList;


    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }

    public String getLargePosterUrl() {
        return largePosterUrl;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public int getAudienceScore() {
        return audienceScore;
    }


    public static Movie fromJson(JSONObject jsonObject){
        Movie movie = new Movie();

        try {
            movie.title = jsonObject.getString("title");
            movie.year = jsonObject.getInt("year");
            movie.synopsis = jsonObject.getString("synopsis");
            movie.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            movie.largePosterUrl = jsonObject.getJSONObject("posters").getString("detailed");
            movie.criticsConsensus = jsonObject.getString("critics_consensus");
            movie.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            movie.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");
            // Construct simple array of cast names
            movie.castList = new ArrayList<String>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                movie.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movie;
    }

    public static ArrayList<Movie> fromJson(JSONArray jsonArray) {
        ArrayList<Movie> movies = new ArrayList<Movie>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieJson = null;
            try {
                movieJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Movie movie = Movie.fromJson(movieJson);
            if (movie != null) {
                movies.add(movie);
            }
        }
        return movies;
    }

}
