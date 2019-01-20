package com.plazi.javatests.dependencies.components;

import org.springframework.stereotype.Repository;

@Repository
public class FakeFlightRepository implements FlightRepository {

    @Override
    public double findPriceByDestination(String destination) {

        switch (destination) {
            case "Buenos Aires": return 300;
            case "Madrid": return 700;
            case "Tokyo": return 1200;
            default: throw new IllegalArgumentException("Unkwown destination " + destination);
        }
    }
}
