package com.plazi.javatests.moviesapp.data;

import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Movie findById(long id) {
        final Object[] args = { id };
        return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieRowMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieRowMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {

        if (movie.getId() == null) {
            jdbcTemplate.update("insert into movies (name, minutes, genre) values (?, ?, ?)",
                    movie.getName(), movie.getMinutes(), movie.getGenre().toString());
        } else {
            jdbcTemplate.update("update movies set name = ?, minutes = ?, genre = ? where id = ?",
                    movie.getName(), movie.getMinutes(), movie.getGenre().toString(), movie.getId());
        }
    }

    private static final RowMapper<Movie> movieRowMapper = (rs, i) ->
            new Movie(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("minutes"),
                    Genre.valueOf(rs.getString("genre")));
}
