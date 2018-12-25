package com.plazi.javatests.moviesapp.service;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.data.MovieRepositoryJdbc;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
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

public class IntegrationTest {

    @Test
    public void retrieve_movies() throws Exception {

        final DataSource dataSource = new DriverManagerDataSource("jdbc:h2:~/test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/init.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        final MovieRepository movieRepository = new MovieRepositoryJdbc(dataSource);
        final MovieService movieService = new MovieService(movieRepository);

        final Collection<Movie> movies = movieService.findAll();
        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));

        // Remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}
