import java.io.*;
import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();

        // Read flight details from folder and add to the ticket booking system
        File folder = new File("D:\\FLIGHT BOOKING SYSTEM\\FLIGHTS");
        File[] files = folder.listFiles();
        for (File file : files) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String flightNumber = file.getName().replaceFirst("[.][^.]+$", "");
                //String source = flightNumber.split("-")[1];
                //String destination = flightNumber.split("-")[2];

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
                ticketBookingSystem.addFlight(flight);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Test the functions
        System.out.println("List of flights:");
        List<String> flightDetails = ticketBookingSystem.listFlightDetails();
        for (String flight : flightDetails) {
            System.out.println(flight);
        }

        System.out.println("1. Book Tickets");
        System.out.println("2. Available Tickets");
        System.out.println("3. Filter Flights With Business Class Alone");
        System.out.println("4. Cancellation");
        System.out.println("5. Ticket Summary");
        System.out.println("6. Full Summary");
        System.out.println("Enter The Option");

        Scanner scanner = new Scanner(System.in);
        int enterValue = scanner.nextInt();

        while (enterValue < 7) {

            switch (enterValue) {
                case 1:
                    // Book seats for a flight

                    System.out.println("Enter the source and destination");
                    String source, destination = null;
                    source = scanner.nextLine();
                    destination = scanner.nextLine();
                    System.out.println("\nFiltered flights from " + source + " to " + destination + ":");
                    List<Flight> filteredFlights = ticketBookingSystem.searchFlights(source, destination);
                    for (Flight flight : filteredFlights) {
                        System.out.println(flight.getFlightNumber());
                    }

                    List<Seat> seatsToBook = filteredFlights.get(0).getBusinessSeats().subList(0, 3);
                    boolean mealOption = true;
                    ticketBookingSystem.bookSeats(filteredFlights.get(0).getFlightNumber(), seatsToBook, mealOption);
                    break;

                case 2:
                    // Print available seats for a flight
                    System.out.println("Enter the source and destination");

                    source = scanner.nextLine();
                    destination = scanner.nextLine();
                    System.out.println("\nFiltered flights from " + source + " to " + destination + ":");
                    List<Flight> filtered_Flights = ticketBookingSystem.searchFlights(source, destination);
                    System.out.println("\nAvailable seats for " + filtered_Flights.get(0).getFlightNumber() + ":");
                    ticketBookingSystem.printAvailableSeats(filtered_Flights.get(0).getFlightNumber());

                    break;

                case 3:

                    System.out.println("\nFlights with business class alone:");
                    List<Flight> businessOnlyFlights = ticketBookingSystem.filterBusinessClassFlights();
                    for (Flight flight : businessOnlyFlights) {
                        System.out.println(flight.getFlightNumber());
                    }

                    break;

            }

        }
        scanner.close();
        

    }
}
