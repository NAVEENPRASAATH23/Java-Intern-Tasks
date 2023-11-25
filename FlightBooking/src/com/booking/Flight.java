package com.booking;

import java.util.HashMap;
import java.util.Map;

public class Flight {
    private Map<String, Seat> seats;

    public Flight() {
        seats = new HashMap<>();
        initializeSeats();
    }

    private void initializeSeats() {
        seats.put("Economy", new Seat("Economy", 50));
        seats.put("Business", new Seat("Business", 20));
        seats.put("First Class", new Seat("First Class", 10));
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }
}
