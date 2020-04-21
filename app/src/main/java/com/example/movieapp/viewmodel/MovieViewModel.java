package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.Service.ApiService;
import com.example.movieapp.Service.Client;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> listMutableLiveData = new MutableLiveData<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Movie>> getListMutableLiveData() {
        ApiService apiService = Client.getService();
        Call<MovieResult> call = apiService.getDataMovies();
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult movieResult = response.body();

                 movies= (ArrayList<Movie>) movieResult.getResults();
                 listMutableLiveData.setValue(movies);
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });

        return listMutableLiveData;
    }

    public LiveData<List<Movie>> getAllMovie() {
        return getListMutableLiveData();
    }
}
