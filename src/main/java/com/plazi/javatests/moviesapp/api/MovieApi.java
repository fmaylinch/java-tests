package com.plazi.javatests.moviesapp.api;

import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api")
public class MovieApi {

    private final MovieService movieService;

    @Autowired
    public MovieApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/movies")
    public Collection<Movie> allMovies() {

        return movieService.findAll();
    }
}
