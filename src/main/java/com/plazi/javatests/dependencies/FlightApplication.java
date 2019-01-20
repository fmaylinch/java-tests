package com.plazi.javatests.dependencies;

import com.plazi.javatests.dependencies.components.CheapFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This is running Spring, and Spring will create the beans
 * (objects annotated with {@link Component} annotation, for example)
 * and will inject the {@link Autowired} dependencies.
 *
 * The {@link #showExample(CheapFlightService)} method is executed
 * after the {@link CheapFlightService} is created,
 * so that component can be used there.
 */
@SpringBootApplication
public class FlightApplication {

	public static void main(String... args) {
		SpringApplication.run(FlightApplication.class, args);
	}

	@Bean
	public CommandLineRunner showExample(CheapFlightService cheapFlightService) {

		return args -> {

			final String destination = cheapFlightService.findCheapestDestination(
					Arrays.asList("Madrid", "Buenos Aires", "Tokyo"));

			System.out.println("Cheapest destination to: " + destination);
		};
	}
}