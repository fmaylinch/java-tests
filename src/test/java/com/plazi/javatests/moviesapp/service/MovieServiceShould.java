package com.plazi.javatests.moviesapp.service;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

public class MovieServiceShould {

    private MovieService movieService;

    @Before
    public void setUp() {

        final MovieRepository movieRepository = mock(MovieRepository.class);

        given(movieRepository.findAll()).willReturn(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                new Movie(4, "Super 8", 112, Genre.THRILLER),
                new Movie(5, "Scream", 111, Genre.HORROR),
                new Movie(6, "Home Alone", 103, Genre.COMEDY),
                new Movie(7, "Matrix", 136, Genre.ACTION)
        ));

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {

        final Collection<Movie> comedies = movieService.findByGenre(Genre.COMEDY);
        assertThat(idsOf(comedies), is(Arrays.asList(3, 6)));
    }

    @Test
    public void return_movies_by_max_minutes() {

        final Collection<Movie> shortMovies = movieService.findByMaxMinutes(120);
        assertThat(idsOf(shortMovies), is(Arrays.asList(2, 3, 4, 5, 6)));
    }

    private List<Integer> idsOf(Collection<Movie> shortMovies) {
        return shortMovies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}