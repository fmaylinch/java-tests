package com.plazi.javatests.dependencies.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheapFlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public CheapFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public String findCheapestDestination(List<String> destinations) {

        String result = null;
        double minPrice = 0;

        for (String destination : destinations) {
            double price = flightRepository.findPriceByDestination(destination);
            if (result == null || price < minPrice) {
                result = destination;
                minPrice = price;
            }
        }

        return result;
    }
}
