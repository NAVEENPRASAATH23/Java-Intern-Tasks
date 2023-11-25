
package com.booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FlightReservationSystem extends JFrame {
    private JTextField flightNumberField;
    private JComboBox<Seat> seatClassDropdown;
    private JTextField numSeatsField;
    private JTextArea outputArea;
    private Map<String, Flight> flights;

    private FlightReservationSystem() {
        setTitle("Flight Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        flights = new HashMap<>();
        initializeFlights();
        initializeComponents();

        setLayout(new GridLayout(3, 1));
        add(createInputPanel());
        add(createOutputPanel());
        add(createButtonPanel());
    }

    private void initializeFlights() {
        flights.put("Airindia", new Flight());
        flights.put("Emirtes", new Flight());
        flights.put("Indigo", new Flight());

        // Add more flights if needed
    }

    private void initializeComponents() {
        flightNumberField = new JTextField();
        seatClassDropdown = new JComboBox<>();
        numSeatsField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Populate seatClassDropdown with Seat objects
        Flight sampleFlight = new Flight();
        for (Seat seat : sampleFlight.getSeats().values()) {
            seatClassDropdown.addItem(seat);
        }
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Seat Class:"));
        inputPanel.add(seatClassDropdown);
        inputPanel.add(new JLabel("Number of Seats:"));
        inputPanel.add(numSeatsField);
        return inputPanel;
    }

    private JPanel createOutputPanel() {
        JScrollPane scrollPane = new JScrollPane(outputArea);
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JLabel("Output:"), BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        return outputPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton displayButton = new JButton("Display Seat Availability");
        JButton reserveButton = new JButton("Reserve Seats");
        JButton exitButton = new JButton("Exit");

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAvailableSeats();
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveSeats();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(displayButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }

    private void displayAvailableSeats() {
        String flightNumber = flightNumberField.getText();
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            outputArea.setText("Displaying seat availability for Flight " + flightNumber + ":\n");
            for (Seat seat : flight.getSeats().values()) {
                outputArea.append(seat.getSeatClass() + ": " + seat.getAvailability() + " seats available\n");
            }
        } else {
            outputArea.setText("Flight not found");
        }
    }

    private void reserveSeats() {
        String flightNumber = flightNumberField.getText();
        Seat selectedSeat = (Seat) seatClassDropdown.getSelectedItem();
        int numSeats = Integer.parseInt(numSeatsField.getText());

        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            if (selectedSeat != null) {
                if (selectedSeat.getAvailability() >= numSeats) {
                    selectedSeat.reserveSeats(numSeats);
                    outputArea.setText(numSeats + " seats reserved in " + selectedSeat.getSeatClass() +
                            " for Flight " + flightNumber);
                } else {
                    outputArea.setText("Not enough seats available in " + selectedSeat.getSeatClass() +
                            " for Flight " + flightNumber);
                }
            } else {
                outputArea.setText("Invalid seat class");
            }
        } else {
            outputArea.setText("Flight not found");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlightReservationSystem().setVisible(true);
            }
        });
    }
}
