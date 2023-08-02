import java.io.*;
import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        FlightBookingSystem flightBookingSystem = new FlightBookingSystem();

        // Read flight details from folder and add to the ticket booking system
        File folder = new File("D:\\FLIGHT TICKET BOOKING\\FLIGHTS");
        File[] files = folder.listFiles();
        for (File file : files) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String flightNumber = file.getName().replaceFirst("[.][^.]+$", "");

                int[] businessSeats = null;
                int totalBusinessSeats = 0;
                int[] economySeats = null;
                int totalEconomySeats = 0;

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Business")) {
                        String businessSeatsInfo = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                        String[] businessSeatsArray = businessSeatsInfo.split(",\\s+");
                        businessSeats = new int[businessSeatsArray.length];
                        for (int i = 0; i < businessSeatsArray.length; i++) {
                            businessSeats[i] = Integer.parseInt(businessSeatsArray[i]);
                        }

                        totalBusinessSeats = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1).trim());

                    }

                    else if (line.startsWith("Economy")) {
                        String economySeatsInfo = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                        String[] economySeatsArray = economySeatsInfo.split(",\\s+");
                        economySeats = new int[economySeatsArray.length];
                        for (int i = 0; i < economySeatsArray.length; i++) {
                            economySeats[i] = Integer.parseInt(economySeatsArray[i]);
                        }

                        System.out.println();
                        totalEconomySeats = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1).trim());

                    }
                }

                Flight flight = new Flight(flightNumber, businessSeats, economySeats, totalBusinessSeats,
                        totalEconomySeats);
                flightBookingSystem.addFlight(flight);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Test the functions
        System.out.println("List of flights:");
        System.out.println();
        List<String> flightDetails = flightBookingSystem.listFlightDetails();
        for (String flight : flightDetails) {
            System.out.println(flight);
        }

        System.out.println();

        System.out.println("1. Book Tickets");
        System.out.println("2. Available Tickets");
        System.out.println("3. Filter Flights With Business Class Alone");
        System.out.println("4. Cancellation");
        System.out.println("5. Seats Numbers For Which Meal Is Ordered");
        System.out.println("6. Full Summary");
        System.out.println();
        System.out.println("Enter Any Number From The Above List");

        Scanner scanner = new Scanner(System.in);
        int enterValue = scanner.nextInt();

        switch (enterValue) {
            case 1: // Book Seats

                System.out.println("Enter the source and destination");
                String source = null;
                String destination = null;
                source = scanner.next();
                destination = scanner.next();
                System.out.println("\nFlights from " + source + " to " + destination + ":");
                System.out.println();
                List<Flight> filteredFlights = flightBookingSystem.searchFlights(source, destination);
                for (Flight flight : filteredFlights) {
                    if (filteredFlights != null)
                        System.out.println(flight.getFlightNumber());
                    else {
                        System.out.println("No Flights Found!");
                    }
                }
                int fromIndex = 0;
                int toIndex = 3;
                List<Seat> seatsToBook = filteredFlights.get(0).getBusinessSeats().subList(fromIndex, toIndex);
                boolean mealOption = true;
                flightBookingSystem.bookSeats(filteredFlights.get(0).getFlightNumber(), seatsToBook, mealOption);
                break;

            case 2: // Print Available Seats

                System.out.println("Enter the source and destination");

                source = scanner.next();
                destination = scanner.next();
                List<Flight> filtered_Flights = flightBookingSystem.searchFlights(source, destination);

                flightBookingSystem.printAvailableSeats(filtered_Flights.get(0).getFlightNumber());

                break;

            case 3: // Business Class Alone

                System.out.println("\nFlights with business class alone:");
                List<Flight> businessOnlyFlights = flightBookingSystem.filterBusinessClassFlights();
                if (businessOnlyFlights == null) {
                    System.out.println("Sorry! No business class tickets!");
                } else {
                    for (Flight flight : businessOnlyFlights) {
                        System.out.println(flight.getFlightNumber());
                    }
                }
                break;

            case 4: // Cancellation

                System.out.println("Enter your booking ID");
                int bookingIdForCancellation = scanner.nextInt();
                flightBookingSystem.cancelBooking(bookingIdForCancellation);
                break;

            case 5: // Seats Numbers For Which Meal Is Ordered

                System.out.println("Enter Flight Number and Name");

                String seatsForWhichMealHasBeenOrdered = scanner.next();
                flightBookingSystem.printMealSeats(seatsForWhichMealHasBeenOrdered);
                break;

            case 6: // Individual And Flight Summary

                System.out.println("Enter Your BookingId");
                int bookingIdForSummary = scanner.nextInt();
                flightBookingSystem.printIndividualAndFlightSummary(bookingIdForSummary);
                break;

        }
        scanner.close();

    }
}


 
