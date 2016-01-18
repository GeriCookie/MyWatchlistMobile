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
    private String year;
    private String synopsis;
    private String posterUrl;
    private String largePosterUrl;
    private int audienceScore;
    private Long _id;
    private int criticsScore;
//    private ArrayList<String> castList;

    public Movie(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public void setCriticsScore(int criticsScore) {
        this.criticsScore = criticsScore;
    }


//    public String getCastList() {
//        return TextUtils.join(", ", castList);
//    }


    public String getLargePosterUrl() {
        return largePosterUrl;
    }

    public void setLargePosterUrl(String largePosterUrl) {
        this.largePosterUrl = largePosterUrl;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(int audienceScore) {
        this.audienceScore = audienceScore;
    }


    public static Movie fromJson(JSONObject jsonObject){
        Movie movie = new Movie();

        try {
            movie.title = jsonObject.getString("title");
            movie.year = jsonObject.getString("theaterReleaseDate");
            movie.synopsis = jsonObject.getString("synopsis");
            movie.posterUrl = jsonObject.getJSONObject("posters").getString("primary");
            movie.largePosterUrl = jsonObject.getJSONObject("posters").getString("secondary");
            movie.criticsScore = jsonObject.getInt("tomatoScore");
            movie.audienceScore = jsonObject.getInt("popcornScore");

//            movie.castList = new ArrayList<String>();
//            JSONArray abridgedCast = jsonObject.getJSONArray("actors");
//            for (int i = 0; i < abridgedCast.length(); i++) {
//                movie.castList.add(abridgedCast.getString(i));
//            }
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
