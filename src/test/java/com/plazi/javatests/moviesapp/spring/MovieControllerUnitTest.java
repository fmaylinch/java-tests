package com.plazi.javatests.moviesapp.spring;

import com.plazi.javatests.moviesapp.api.MovieController;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import com.plazi.javatests.moviesapp.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(MovieController.class) // <-- narrow down the tests to just this controller, to optimize
public class MovieControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void all_movies() throws Exception {

        given(movieService.findAll()).willReturn(Arrays.asList(
                new Movie(1, "Memento", 113, Genre.THRILLER),
                new Movie(2, "Super 8", 112, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        ));

        final String expectedMoviesJson =
                IOUtils.resourceToString("/json/expected-movies.json", StandardCharsets.UTF_8);

        mvc.perform(get("/api/movies")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Memento")))
                .andExpect(jsonPath("$..name", is(Arrays.asList("Memento", "Super 8", "Matrix"))))

                .andExpect(content().json(expectedMoviesJson));
    }

    @Test
    public void movies_by_genre() throws Exception {

        given(movieService.findByGenre(Genre.THRILLER)).willReturn(Arrays.asList(
                new Movie(1, "Memento", 113, Genre.THRILLER),
                new Movie(2, "Super 8", 112, Genre.THRILLER)
        ));

        final String expectedMoviesJson =
                IOUtils.resourceToString("/json/expected-movies-by-genre.json", StandardCharsets.UTF_8);

        mvc.perform(get("/api/movies?genre=THRILLER")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(content().json(expectedMoviesJson));
    }

    @Test
    public void movie_by_id() throws Exception {

        given(movieService.findById(2)).willReturn(
                new Movie(2, "Super 8", 112, Genre.THRILLER)
        );

        final String expectedMovieJson =
                IOUtils.resourceToString("/json/expected-movie.json", StandardCharsets.UTF_8);

        mvc.perform(get("/api/movies/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedMovieJson));
    }
}
