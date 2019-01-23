package com.plazi.javatests.moviesapp;

import com.plazi.javatests.moviesapp.data.DataSourceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String... args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() {
		return DataSourceUtil.getDataSource();
	}
}