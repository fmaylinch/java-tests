package com.plazi.javatests.moviesapp.spring;

import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerNotOptimizedUnitTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private MovieService movieService;

    @Test
    public void getMovies() {

        List<Movie> sampleMovies = Arrays.asList(
                new Movie(1, "Memento", 113, Genre.THRILLER),
                new Movie(2, "Super 8", 112, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        );

        Mockito.when(movieService.findAll()).thenReturn(sampleMovies);

        // Needs no-arg constructor in Movie
        ResponseEntity<List<Movie>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Movie>>(){});

        List<Movie> movies = response.getBody();

        Assert.assertThat(movies, is(sampleMovies));
    }

    @Test
    public void getMovies_json() throws Exception {

        List<Movie> sampleMovies = Arrays.asList(
                new Movie(1, "Memento", 113, Genre.THRILLER),
                new Movie(2, "Super 8", 112, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        );

        Mockito.when(movieService.findAll()).thenReturn(sampleMovies);

        String responseJson = restTemplate.getForObject(
                "http://localhost:" + port + "/api/movies", String.class);

        final String expectedMoviesJson =
                IOUtils.resourceToString("/json/expected-movies.json", StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expectedMoviesJson, responseJson, true);
    }
}
