import java.util.*;
class Booking {
    private static int bookingIdCounter = 1;
    private int bookingId;
    private String flightNumber;
    private List<Seat> bookedSeats;
    private boolean mealOption;

    public Booking(String flightNumber, List<Seat> bookedSeats, boolean mealOption) {
        this.bookingId = bookingIdCounter++;
        this.flightNumber = flightNumber;
        this.bookedSeats = bookedSeats;
        this.mealOption = mealOption;
    }

    // Getters

    public int getBookingId() {
        return bookingId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public boolean hasMealOption() {
        return mealOption;
    }



}