package com.plazi.javatests.moviesapp.service;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveOrUpdate(Movie movie) {
        movieRepository.saveOrUpdate(movie);
    }

    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(int id) {
        return movieRepository.findById(id);
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
