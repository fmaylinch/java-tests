package com.plazi.javatests.moviesapp.data;

import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Collection;

@Repository
public class MovieRepositoryJdbc implements MovieRepository {

    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Movie findById(long id) {
        final Object[] args = { id };
        try {
            return jdbcTemplate.queryForObject("select * from movies where id = ?", args, movieRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("ID doesn't exist: " + id);
        }
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("select * from movies", movieRowMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {

        if (movie.getId() == null) {

            final KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                final PreparedStatement ps = connection.prepareStatement(
                        "insert into movies (name, minutes, genre) values (?, ?, ?)", new String[]{"id"});
                ps.setString(1, movie.getName());
                ps.setInt(2, movie.getMinutes());
                ps.setString(3, movie.getGenre().toString());
                return ps;
            }, keyHolder);

            movie.setId(keyHolder.getKey().intValue());

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
