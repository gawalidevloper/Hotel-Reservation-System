import java.util.Scanner;

public class HotelReservation {
    static boolean[] rooms = new boolean[5]; 
    static String[] customers = new String[5]; 
    static double[] roomPrices = {1000, 1500, 2000, 2500, 3000}; 
    static String[] roomCategories = {"Standard", "Deluxe", "Suite", "Executive Suite", "Presidential Suite"}; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nWelcome to HOTEL RESERVATION SYSTEM!");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    cancelBooking(scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using our Hotel Reservation system!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i]) {
                System.out.println("Room " + (i + 1) + " - " + roomCategories[i] + " - $" + roomPrices[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    static void bookRoom(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room number (1-5) to book: ");
        int roomNumber = scanner.nextInt();

        if (roomNumber < 1 || roomNumber > 5) {
            System.out.println("Invalid room number! Choose between 1-5.");
        } else if (rooms[roomNumber - 1]) {
            System.out.println("Room already booked!");
        } else {
            double price = roomPrices[roomNumber - 1];
            System.out.println("Room Category: " + roomCategories[roomNumber - 1]);
            System.out.println("Room Price: $" + price);
            System.out.print("Enter payment amount: $");
            double payment = scanner.nextDouble();

            if (payment >= price) {
                rooms[roomNumber - 1] = true;
                customers[roomNumber - 1] = name;
                System.out.println("Room " + roomNumber + " (" + roomCategories[roomNumber - 1] + ") booked successfully for " + name + "!");
                System.out.println("Change returned: $" + (payment - price));
            } else {
                System.out.println("Insufficient payment! Booking failed.");
            }
        }
    }

    static void viewBookings() {
        System.out.println("\nBooked Rooms:");
        boolean found = false;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i]) {
                System.out.println("Room " + (i + 1) + " (" + roomCategories[i] + ") - Booked by " + customers[i] + ".");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are booked.");
        }
    }

    static void cancelBooking(Scanner scanner) {
        System.out.print("\nEnter room number (1-5) to cancel booking: ");
        int roomNumber = scanner.nextInt();

        if (roomNumber < 1 || roomNumber > 5) {
            System.out.println("Invalid room number! Choose between 1-5.");
        } else if (!rooms[roomNumber - 1]) {
            System.out.println("Room is already available (not booked).");
        } else {
            System.out.println("Booking for Room " + roomNumber + " (" + roomCategories[roomNumber - 1] + ") by " + customers[roomNumber - 1] + " has been cancelled.");
            rooms[roomNumber - 1] = false;
            customers[roomNumber - 1] = null;
        }
    }
}