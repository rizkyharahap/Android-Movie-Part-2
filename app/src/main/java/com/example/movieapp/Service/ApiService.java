package com.example.movieapp.Service;

import com.example.movieapp.BuildConfig;
import com.example.movieapp.model.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String API_KEY = "237893d501833425b891960da4b1da9c";

    @GET("discover/movie?api_key=" + API_KEY)
    Call<MovieResult> getDataMovies();
}
