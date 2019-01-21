package com.plazi.javatests.moviesapp;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.data.MovieRepositoryJdbc;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MovieIntegrationTest {

    private DataSource dataSource;
    private MovieService movieService;

    @Before
    public void setUp() throws Exception {

        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/init.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        final MovieRepository movieRepository = new MovieRepositoryJdbc(dataSource);
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void retrieve_movies() {

        final Collection<Movie> movies = movieService.findAll();
        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void save_and_load_movies() {

        final int expectedId = 4;

        final Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieService.saveOrUpdate(movie);
        assertThat(movie.getId(), is(expectedId));

        final Movie movie2 = movieService.findById(expectedId);
        assertThat(movie2, is(movie));
    }

    @Test(expected = IllegalArgumentException.class)
    public void non_existing_movie() {

        final int nonExistingId = 4;
        movieService.findById(nonExistingId);
    }

    @After
    public void tearDown() throws Exception {

        // Remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files"); // "shutdown" is also enough for mem db
    }
}
