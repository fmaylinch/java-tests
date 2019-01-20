package com.plazi.javatests.dependencies;

import com.plazi.javatests.dependencies.components.CheapFlightService;
import com.plazi.javatests.dependencies.components.FakeFlightRepository;
import com.plazi.javatests.dependencies.components.FlightRepository;

import java.util.Arrays;

/**
 * In this application example we inject dependencies
 * manually (programmatically).
 *
 * Note some classes have Spring annotations but since we're
 * not using Spring here, they're not relevant (you could remove
 * those annotations and this application example would still work).
 */
public class FlightApplicationNoSpring {

    public static void main(String[] args) {

        final FlightRepository flightRepository = new FakeFlightRepository();

        final CheapFlightService cheapFlightService = new CheapFlightService(flightRepository);

        final String destination = cheapFlightService.findCheapestDestination(
                Arrays.asList("Madrid", "Buenos Aires", "Tokyo"));

        System.out.println("Cheapest destination to: " + destination);
    }
}
