package com.booking;

public class Seat {
    private String seatClass;
    private int availability;

    public Seat(String seatClass, int availability) {
        this.seatClass = seatClass;
        this.availability = availability;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public int getAvailability() {
        return availability;
    }

    public void reserveSeats(int numSeats) {
        availability -= numSeats;
    }

    @Override
    public String toString() {
        return seatClass;
    }
}
