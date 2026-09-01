
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ================= ROOM CLASS =================

class Room {

    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {

        System.out.printf(
                "%-10d %-15s Rs. %-12.2f %-12s%n",
                roomNumber,
                category,
                price,
                available ? "Available" : "Booked"
        );
    }
}


// ================= BOOKING CLASS =================

class Booking {

    private String bookingId;
    private String customerName;
    private String phone;
    private int roomNumber;
    private String category;
    private String checkIn;
    private String checkOut;
    private int nights;
    private double amount;
    private String paymentStatus;

    public Booking(
            String bookingId,
            String customerName,
            String phone,
            int roomNumber,
            String category,
            String checkIn,
            String checkOut,
            int nights,
            double amount,
            String paymentStatus) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.category = category;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.nights = nights;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String toFileString() {

        return bookingId + "|" +
                customerName + "|" +
                phone + "|" +
                roomNumber + "|" +
                category + "|" +
                checkIn + "|" +
                checkOut + "|" +
                nights + "|" +
                amount + "|" +
                paymentStatus;
    }

    public void displayBooking() {

        System.out.println(
                "\n========== BOOKING DETAILS ==========");

        System.out.println(
                "Booking ID      : " + bookingId);

        System.out.println(
                "Customer Name   : " + customerName);

        System.out.println(
                "Phone Number    : " + phone);

        System.out.println(
                "Room Number     : " + roomNumber);

        System.out.println(
                "Room Category   : " + category);

        System.out.println(
                "Check-In        : " + checkIn);

        System.out.println(
                "Check-Out       : " + checkOut);

        System.out.println(
                "Number of Nights: " + nights);

        System.out.printf(
                "Total Amount    : Rs. %.2f%n",
                amount);

        System.out.println(
                "Payment Status  : " + paymentStatus);

        System.out.println(
                "=====================================");
    }
}


// ================= MAIN CLASS =================

public class HotelReservationSystem {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Room> rooms =
            new ArrayList<>();

    static ArrayList<Booking> bookings =
            new ArrayList<>();

    static final String FILE_NAME =
            "hotel_bookings.txt";

    static int bookingCounter = 1001;


    // ================= INITIALIZE ROOMS =================

    public static void initializeRooms() {

        // Standard Rooms

        rooms.add(
                new Room(101, "Standard", 2000)
        );

        rooms.add(
                new Room(102, "Standard", 2000)
        );

        rooms.add(
                new Room(103, "Standard", 2000)
        );


        // Deluxe Rooms

        rooms.add(
                new Room(201, "Deluxe", 3500)
        );

        rooms.add(
                new Room(202, "Deluxe", 3500)
        );

        rooms.add(
                new Room(203, "Deluxe", 3500)
        );


        // Suite Rooms

        rooms.add(
                new Room(301, "Suite", 6000)
        );

        rooms.add(
                new Room(302, "Suite", 6000)
        );

        rooms.add(
                new Room(303, "Suite", 6000
        ));
    }


    // ================= DISPLAY ROOMS =================

    public static void displayRooms() {

        System.out.println(
                "\n========== HOTEL ROOMS ==========");

        System.out.printf(
                "%-10s %-15s %-16s %-12s%n",
                "Room No.",
                "Category",
                "Price/Night",
                "Status"
        );

        System.out.println(
                "--------------------------------------------------");

        for (Room room : rooms) {

            room.displayRoom();
        }
    }


    // ================= SEARCH ROOMS =================

    public static void searchRooms() {

        System.out.println(
                "\n========== SEARCH ROOMS ==========");

        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");

        System.out.print(
                "Select category: ");

        int choice = scanner.nextInt();

        String category;

        if (choice == 1) {

            category = "Standard";

        } else if (choice == 2) {

            category = "Deluxe";

        } else if (choice == 3) {

            category = "Suite";

        } else {

            System.out.println(
                    "Invalid category.");

            return;
        }

        boolean found = false;

        System.out.println(
                "\nAvailable " + category + " Rooms:");

        for (Room room : rooms) {

            if (room.getCategory()
                    .equalsIgnoreCase(category)
                    && room.isAvailable()) {

                room.displayRoom();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No available rooms found.");
        }
    }


    // ================= FIND ROOM =================

    public static Room findRoom(int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {

                return room;
            }
        }

        return null;
    }


    // ================= BOOK ROOM =================

    public static void bookRoom() {

        System.out.println(
                "\n========== BOOK A ROOM ==========");

        displayRooms();

        System.out.print(
                "\nEnter room number: ");

        int roomNumber =
                scanner.nextInt();

        scanner.nextLine();

        Room room =
                findRoom(roomNumber);

        if (room == null) {

            System.out.println(
                    "Room does not exist.");

            return;
        }

        if (!room.isAvailable()) {

            System.out.println(
                    "This room is already booked.");

            return;
        }


        // Customer Name

        System.out.print(
                "Enter customer name: ");

        String customerName =
                scanner.nextLine();

        if (customerName.isEmpty()) {

            System.out.println(
                    "Customer name cannot be empty.");

            return;
        }


        // Phone

        System.out.print(
                "Enter phone number: ");

        String phone =
                scanner.nextLine();

        if (phone.isEmpty()) {

            System.out.println(
                    "Phone number cannot be empty.");

            return;
        }


        // Check-in

        System.out.print(
                "Enter check-in date: ");

        String checkIn =
                scanner.nextLine();


        // Check-out

        System.out.print(
                "Enter check-out date: ");

        String checkOut =
                scanner.nextLine();


        // Nights

        System.out.print(
                "Enter number of nights: ");

        int nights =
                scanner.nextInt();

        if (nights <= 0) {

            System.out.println(
                    "Number of nights must be greater than 0.");

            return;
        }


        // Calculate bill

        double totalAmount =
                room.getPrice() * nights;


        System.out.println(
                "\n========== BILL ==========");

        System.out.println(
                "Customer     : " + customerName);

        System.out.println(
                "Room         : " + roomNumber);

        System.out.println(
                "Category     : " +
                        room.getCategory());

        System.out.printf(
                "Price/Night  : Rs. %.2f%n",
                room.getPrice());

        System.out.println(
                "Nights       : " + nights);

        System.out.printf(
                "Total Amount : Rs. %.2f%n",
                totalAmount);

        System.out.println(
                "==========================");


        // Payment

        System.out.println(
                "\nProceed to payment?");

        System.out.println("1. Yes");
        System.out.println("2. No");

        System.out.print(
                "Enter choice: ");

        int paymentChoice =
                scanner.nextInt();

        if (paymentChoice != 1) {

            System.out.println(
                    "Booking cancelled.");

            return;
        }


        boolean paymentSuccessful =
                processPayment(totalAmount);

        if (!paymentSuccessful) {

            System.out.println(
                    "Payment failed.");

            System.out.println(
                    "Booking was not completed.");

            return;
        }


        // Create booking ID

        String bookingId =
                "BK" + bookingCounter;

        bookingCounter++;


        // Create booking object

        Booking booking =
                new Booking(
                        bookingId,
                        customerName,
                        phone,
                        roomNumber,
                        room.getCategory(),
                        checkIn,
                        checkOut,
                        nights,
                        totalAmount,
                        "PAID"
                );


        // Add booking

        bookings.add(booking);


        // Make room unavailable

        room.setAvailable(false);


        // Save booking

        saveBookingToFile(booking);


        System.out.println(
                "\nBooking successful!");

        System.out.println(
                "Your Booking ID: " + bookingId);

        booking.displayBooking();
    }


    // ================= PAYMENT =================

    public static boolean processPayment(
            double amount) {

        System.out.println(
                "\n========== PAYMENT ==========");

        System.out.printf(
                "Amount to Pay: Rs. %.2f%n",
                amount);

        System.out.println(
                "1. Credit/Debit Card");

        System.out.println(
                "2. UPI");

        System.out.println(
                "3. Cash");

        System.out.print(
                "Select payment method: ");

        int choice =
                scanner.nextInt();

        scanner.nextLine();


        switch (choice) {

            case 1:

                System.out.print(
                        "Enter card number: ");

                String cardNumber =
                        scanner.nextLine();

                if (cardNumber.length() < 4) {

                    System.out.println(
                            "Invalid card number.");

                    return false;
                }

                System.out.println(
                        "Processing card payment...");

                System.out.println(
                        "Payment successful!");

                return true;


            case 2:

                System.out.print(
                        "Enter UPI ID: ");

                String upiId =
                        scanner.nextLine();

                if (!upiId.contains("@")) {

                    System.out.println(
                            "Invalid UPI ID.");

                    return false;
                }

                System.out.println(
                        "Processing UPI payment...");

                System.out.println(
                        "Payment successful!");

                return true;


            case 3:

                System.out.println(
                        "Cash payment selected.");

                System.out.println(
                        "Payment successful!");

                return true;


            default:

                System.out.println(
                        "Invalid payment method.");

                return false;
        }
    }


    // ================= CANCEL BOOKING =================

    public static void cancelBooking() {

        System.out.println(
                "\n========== CANCEL RESERVATION ==========");

        scanner.nextLine();

        System.out.print(
                "Enter Booking ID: ");

        String bookingId =
                scanner.nextLine();

        Booking bookingToCancel = null;


        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(bookingId)) {

                bookingToCancel = booking;

                break;
            }
        }


        if (bookingToCancel == null) {

            System.out.println(
                    "Booking not found.");

            return;
        }


        // Make room available again

        Room room =
                findRoom(
                        bookingToCancel.getRoomNumber()
                );

        if (room != null) {

            room.setAvailable(true);
        }


        // Remove booking

        bookings.remove(
                bookingToCancel);


        System.out.println(
                "Booking cancelled successfully.");

        System.out.println(
                "Booking ID: " + bookingId);

        System.out.println(
                "Room " +
                        bookingToCancel.getRoomNumber() +
                        " is now available.");
    }


    // ================= VIEW BOOKING =================

    public static void viewBooking() {

        System.out.println(
                "\n========== VIEW BOOKING ==========");

        scanner.nextLine();

        System.out.print(
                "Enter Booking ID: ");

        String bookingId =
                scanner.nextLine();


        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(bookingId)) {

                booking.displayBooking();

                return;
            }
        }


        System.out.println(
                "Booking not found.");
    }


    // ================= VIEW ALL BOOKINGS =================

    public static void viewAllBookings() {

        System.out.println(
                "\n========== ALL BOOKINGS ==========");

        if (bookings.isEmpty()) {

            System.out.println(
                    "No active bookings.");

            return;
        }


        for (Booking booking : bookings) {

            booking.displayBooking();
        }
    }


    // ================= SAVE BOOKING =================

    public static void saveBookingToFile(
            Booking booking) {

        try {

            FileWriter writer =
                    new FileWriter(
                            FILE_NAME,
                            true
                    );

            writer.write(
                    booking.toFileString()
            );

            writer.write(
                    System.lineSeparator()
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving booking to file.");
        }
    }


    // ================= LOAD BOOKINGS =================

    public static void loadBookingsFromFile() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {

            return;
        }


        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;


            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");


                if (data.length != 10) {

                    continue;
                }


                try {

                    String bookingId =
                            data[0];

                    String customerName =
                            data[1];

                    String phone =
                            data[2];

                    int roomNumber =
                            Integer.parseInt(data[3]);

                    String category =
                            data[4];

                    String checkIn =
                            data[5];

                    String checkOut =
                            data[6];

                    int nights =
                            Integer.parseInt(data[7]);

                    double amount =
                            Double.parseDouble(data[8]);

                    String paymentStatus =
                            data[9];


                    Booking booking =
                            new Booking(
                                    bookingId,
                                    customerName,
                                    phone,
                                    roomNumber,
                                    category,
                                    checkIn,
                                    checkOut,
                                    nights,
                                    amount,
                                    paymentStatus
                            );


                    bookings.add(booking);


                    // Mark room as booked

                    Room room =
                            findRoom(roomNumber);

                    if (room != null) {

                        room.setAvailable(false);
                    }


                    // Update booking counter

                    if (bookingId.startsWith("BK")) {

                        try {

                            int number =
                                    Integer.parseInt(
                                            bookingId.substring(2)
                                    );

                            if (number >= bookingCounter) {

                                bookingCounter =
                                        number + 1;
                            }

                        } catch (NumberFormatException e) {

                            // Ignore invalid ID
                        }
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid booking data found.");
                }
            }


            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "Error loading bookings.");
        }
    }


    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        initializeRooms();

        loadBookingsFromFile();


        int choice;


        System.out.println(
                "==========================================");

        System.out.println(
                "        HOTEL RESERVATION SYSTEM");

        System.out.println(
                "==========================================");


        do {

            System.out.println(
                    "\n========== MAIN MENU ==========");

            System.out.println(
                    "1. View All Rooms");

            System.out.println(
                    "2. Search Available Rooms");

            System.out.println(
                    "3. Book a Room");

            System.out.println(
                    "4. Cancel Reservation");

            System.out.println(
                    "5. View Booking Details");

            System.out.println(
                    "6. View All Bookings");

            System.out.println(
                    "7. Exit");


            System.out.print(
                    "Enter your choice: ");


            while (!scanner.hasNextInt()) {

                System.out.println(
                        "Please enter a valid number.");

                scanner.next();

                System.out.print(
                        "Enter your choice: ");
            }


            choice =
                    scanner.nextInt();


            switch (choice) {

                case 1:

                    displayRooms();

                    break;


                case 2:

                    searchRooms();

                    break;


                case 3:

                    bookRoom();

                    break;


                case 4:

                    cancelBooking();

                    break;


                case 5:

                    viewBooking();

                    break;


                case 6:

                    viewAllBookings();

                    break;


                case 7:

                    System.out.println(
                            "\nThank you for using " +
                            "Hotel Reservation System!");

                    break;


                default:

                    System.out.println(
                            "Invalid choice. Try again.");
            }

        } while (choice != 7);


        scanner.close();
    }
}