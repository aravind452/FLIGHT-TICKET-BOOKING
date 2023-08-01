import java.util.*;
public class TicketBookingSystem {
    private Map<String, Flight> flights;
    private Map<Integer, Booking> bookings;

    public TicketBookingSystem() {
        flights = new HashMap<>();
        bookings = new HashMap<>();
    }

    // Add a flight to the system
    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    // Get a flight by its number
    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }

    // Search flights from a given source and destination
    public List<Flight> searchFlights(String source, String destination) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (flight.getFlightNumber().toLowerCase().contains(source.toLowerCase()) &&
                    flight.getFlightNumber().toLowerCase().contains(destination.toLowerCase())) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    // Filter flights with business class only
    public List<Flight> filterBusinessClassFlights() {
        List<Flight> businessClassFlights = new ArrayList<>();
        for (Flight flight : flights.values()) {
            if (!flight.getBusinessSeats().isEmpty()) {
                businessClassFlights.add(flight);
            }
        }
        return businessClassFlights;
    }

    // List down the number of flights and their names
    public List<String> listFlightDetails() {
        List<String> flightDetails = new ArrayList<>();
        for (Flight flight : flights.values()) {
            flightDetails.add(flight.getFlightNumber());
        }
        return flightDetails;
    }

    // Book seats for a flight with an optional meal option
    public void bookSeats(String flightNumber, List<Seat> seats, boolean mealOption) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        int totalCost = 0;
        if (seats.stream().anyMatch(s -> s.getSeatType().equals("Business"))) {
            totalCost = seats.size() * (flight.getBusinessPrice() + (flight.getBusinessSeats().size() / 6) * flight.getBusinessPriceIncrease());
            flight.getBusinessSeats().removeAll(seats);
        } else if (seats.stream().anyMatch(s -> s.getSeatType().equals("Economy"))) {
            totalCost = seats.size() * (flight.getEconomyPrice() + (flight.getEconomySeats().size() / 6) * flight.getEconomyPriceIncrease());
            flight.getEconomySeats().removeAll(seats);
        } else {
            System.out.println("Invalid seat type.");
            return;
        }

        if (mealOption) {
            totalCost += 200 * seats.size();
        }

        bookings.put(bookings.size() + 1, new Booking(flightNumber, seats, mealOption));
        System.out.println("Booking successful! Total cost: INR " + totalCost);
    }

    // Cancel a booking by booking ID
    public void cancelBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        Flight flight = flights.get(booking.getFlightNumber());
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        List<Seat> seatsToCancel = booking.getBookedSeats();
        String seatType = seatsToCancel.get(0).getSeatType();

        if (seatType.equals("Business")) {
            flight.getBusinessSeats().addAll(seatsToCancel);
        } else if (seatType.equals("Economy")) {
            flight.getEconomySeats().addAll(seatsToCancel);
        }

        bookings.remove(bookingId);
        System.out.println("Booking canceled successfully.");
    }

    // Print available seats for a flight
    public void printAvailableSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        int availableBusinessSeats = flight.getBusinessSeats().size();
        int availableEconomySeats = flight.getEconomySeats().size();

        System.out.println("Available seats for Flight " + flightNumber + ":");
        System.out.println("Business Class: " + availableBusinessSeats);
        System.out.println("Economy Class: " + availableEconomySeats);
    }

    // Print seat numbers with meal option for a flight
    public void printMealSeats(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.println("Seats with meal option for Flight " + flightNumber + ":");
        for (Booking booking : bookings.values()) {
            if (booking.getFlightNumber().equals(flightNumber) && booking.hasMealOption()) {
                for (Seat seat : booking.getBookedSeats()) {
                    System.out.print(seat.getSeatNumber() + ", ");
                }
            }
        }
        System.out.println();
    }

    // Print individual and flight summary for a booking
    public void printIndividualAndFlightSummary(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        Flight flight = flights.get(booking.getFlightNumber());
        if (flight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Passenger Details (with seat and Meal preference):");
        for (Seat seat : booking.getBookedSeats()) {
            System.out.println("Seat: " + seat.getSeatNumber() + ", Type: " + seat.getSeatType() + ", Meal: " + (booking.hasMealOption() ? "Yes" : "No"));
        }

        System.out.println("Flight Details (Flight no): " + flight.getFlightNumber());
    }
}
