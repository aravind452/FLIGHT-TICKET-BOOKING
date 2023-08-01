
class Seat {
    private String seatNumber;
    private String seatType;
    private int price;

    public Seat(String seatNumber, String seatType, int basePrice) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = basePrice;
    }

    // Getters and setters

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getPrice() {
        return price;
    }
}
