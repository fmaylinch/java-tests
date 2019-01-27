package com.plazi.javatests.moviesapp.api;

import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Movie> findMovies(@RequestParam(required = false) Genre genre) {

        if (genre == null) {
            return movieService.findAll();
        } else {
            return movieService.findByGenre(genre);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Movie movieById(@PathVariable int id) {

        return movieService.findById(id);
    }
}
