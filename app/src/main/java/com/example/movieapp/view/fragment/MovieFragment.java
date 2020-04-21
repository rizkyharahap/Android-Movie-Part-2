package com.example.movieapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.model.Movie;
import com.example.movieapp.R;
import com.example.movieapp.adapter.CardMovieAdapter;
import com.example.movieapp.view.activity.MovieDetailActivity;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private CardMovieAdapter cardMovieAdapter;
    private RecyclerView recyclerView;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.movie_recycler);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(final List<Movie> movies) {
                cardMovieAdapter = new CardMovieAdapter(movies, getActivity());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerView.setAdapter(cardMovieAdapter);

                cardMovieAdapter.setListener(new CardMovieAdapter.Listener() {
                    @Override
                    public void onClick(int position) {
                        Movie movie = movies.get(position);
                        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movie);
                        getActivity().startActivity(intent);
                    }
                });
            }
        });
    }
}
