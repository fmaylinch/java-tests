package com.plazi.javatests.moviesapp.service;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findByGenre(Genre genre) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getGenre() == genre)
                .collect(Collectors.toList());
    }

    public Collection<Movie> findByMaxMinutes(int maxMinutes) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getMinutes() <= maxMinutes)
                .collect(Collectors.toList());
    }
}
