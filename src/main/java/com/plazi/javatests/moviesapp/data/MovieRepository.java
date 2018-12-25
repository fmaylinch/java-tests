package com.plazi.javatests.moviesapp.data;

import com.plazi.javatests.moviesapp.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findById(long id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);
}
