package com.example.cookie.mywatchlist;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MoviesClient {
    private final String API_BASE_URL = "http://d3biamo577v4eu.cloudfront.net/api/private/v1.0/";

    private AsyncHttpClient client;

    public MoviesClient() {
        this.client = new AsyncHttpClient();
    }

    public void getAllMovies(JsonHttpResponseHandler handler) {
        String url = getApiUrl("m/list/find");
        RequestParams params = new RequestParams();
        params.put("page", "1");
        params.put("limit", "100");
        params.put("type","in-theaters");
        params.put("sortBy", "popularity");
        client.get(url, params, handler);

    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
}

