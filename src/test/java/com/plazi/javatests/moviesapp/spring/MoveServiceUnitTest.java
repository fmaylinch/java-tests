package com.plazi.javatests.moviesapp.spring;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieService.class) // <-- We can reduce the number of objects created
public class MoveServiceUnitTest {

    @Autowired
    MovieService movieService; // <-- the real service will be injected

    @MockBean
    MovieRepository movieRepository; // <-- a mock will be injected

    @Before
    public void setUp() {

        given(movieRepository.findAll()).willReturn(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                new Movie(4, "Super 8", 112, Genre.THRILLER),
                new Movie(5, "Scream", 111, Genre.HORROR),
                new Movie(6, "Home Alone", 103, Genre.COMEDY),
                new Movie(7, "Matrix", 136, Genre.ACTION)
        ));
    }

    @Test
    public void return_movies_by_genre() {

        final Collection<Movie> comedies = movieService.findByGenre(Genre.COMEDY);
        assertThat(idsOf(comedies), is(Arrays.asList(3, 6)));
    }

    @Test
    public void return_movies_shorter_or_equal_to_max_minutes() {

        final Collection<Movie> shortMovies = movieService.findByMaxMinutes(112);
        assertThat(idsOf(shortMovies), is(Arrays.asList(4, 5, 6)));
    }

    @Test
    public void return_no_movies_when_max_minutes_is_too_low() {

        final Collection<Movie> shortMovies = movieService.findByMaxMinutes(102);
        assertThat(idsOf(shortMovies), is(Collections.emptyList()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fail_when_max_minutes_is_not_positive() {

        movieService.findByMaxMinutes(0);
    }

    private List<Integer> idsOf(Collection<Movie> shortMovies) {
        return shortMovies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}
