package com.plazi.javatests.moviesapp;

import com.plazi.javatests.moviesapp.data.MovieRepository;
import com.plazi.javatests.moviesapp.model.Genre;
import com.plazi.javatests.moviesapp.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String... args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() throws SQLException {

		// H2 will create files ~/moviesapp.*.db
		final DataSource dataSource = new DriverManagerDataSource("jdbc:h2:~/moviesapp;MODE=MYSQL", "sa", "sa");

		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/init.sql"));

		return dataSource;
	}

	@Bean
	public CommandLineRunner insertSampleData(MovieRepository movieRepository) {

		return args -> {

			if (movieRepository.findAll().isEmpty()) {
				System.out.println("Inserting sample data");
				movieRepository.saveOrUpdate(new Movie("Dark Knight", 152, Genre.ACTION));
				movieRepository.saveOrUpdate(new Movie("Memento", 113, Genre.THRILLER));
				movieRepository.saveOrUpdate(new Movie("There's Something About Mary", 119, Genre.COMEDY));
			} else {
				System.out.println("There are already movies in the database");
			}
		};
	}
}