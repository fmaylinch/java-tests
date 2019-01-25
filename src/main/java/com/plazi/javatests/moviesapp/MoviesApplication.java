package com.plazi.javatests.moviesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String... args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	/*
	 * Spring can also configure the DataSource
	 * from the values in application.properties
	 *
	@Bean
	public DataSource getDataSource() {
		return DataSourceUtil.getDataSource();
	}
	*/
}