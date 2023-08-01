import java.util.*;

class Flight {
    private String flightNumber;
    private List<Seat> businessSeats;
    private List<Seat> economySeats;
    private int businessPrice;
    private int economyPrice;
    private int businessPriceIncrease;
    private int economyPriceIncrease;

    public Flight(String flightNumber, int[] businessSeating, int[] economySeating, int totalBusinessRows,
            int totalEconomyRows) {
        this.flightNumber = flightNumber;
        this.businessSeats = new ArrayList<>();
        this.economySeats = new ArrayList<>();
        this.businessPrice = 2000;
        this.economyPrice = 1000;
        this.businessPriceIncrease = 200;
        this.economyPriceIncrease = 100;

        calculateSeatingArrangement(businessSeating, economySeating, totalBusinessRows, totalEconomyRows);
    }

    // Getters

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Seat> getBusinessSeats() {
        return businessSeats;
    }

    public List<Seat> getEconomySeats() {
        return economySeats;
    }

    public int getBusinessPrice() {
        return businessPrice;
    }

    public int getEconomyPrice() {
        return economyPrice;
    }

    public int getBusinessPriceIncrease() {
        return businessPriceIncrease;
    }

    public int getEconomyPriceIncrease() {
        return economyPriceIncrease;
    }

    private void calculateSeatingArrangement(int[] businessSeating, int[] economySeating, int totalBusinessRows,
            int totalEconomyRows) {
        // Calculate business class seating arrangement

        for (int j = 0; j < totalBusinessRows; j++) {

            for (int i = 1; i <= businessSeating[0]; i++) {
                businessSeats.add(new Seat(i + "_W", "Business", businessPrice));

                businessSeats.add(new Seat(i + "_A", "Business", businessPrice));
            }

            for (int i = 1; i <= businessSeating[1]; i++) {

                businessSeats.add(new Seat(i + "_A", "Business", businessPrice));
                businessSeats.add(new Seat(i + "_M", "Business", businessPrice));
                businessSeats.add(new Seat(i + "_A", "Business", businessPrice));
            }

            for (int i = 1; i <= businessSeating[2]; i++) {
                businessSeats.add(new Seat(i + "_W", "Business", businessPrice));
                businessSeats.add(new Seat(i + "_A", "Business", businessPrice));

            }

        }

        // Calculate economy class seating arrangement

        for (int j = 0; j < totalEconomyRows; j++) {

            for (int i = 1; i <= economySeating[0]; i++) {
                economySeats.add(new Seat(i + "_A", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_M", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_M", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_W", "Economy", economyPrice));
            }
            for (int i = 1; i <= economySeating[1]; i++) {
                economySeats.add(new Seat(i + "_A", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_M", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_M", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_A", "Economy", economyPrice));
            }

            for (int i = 1; i <= economySeating[2]; i++) {
                economySeats.add(new Seat(i + "_W", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_M", "Economy", economyPrice));
                economySeats.add(new Seat(i + "_A", "Economy", economyPrice));
            }

        }
    }
}
