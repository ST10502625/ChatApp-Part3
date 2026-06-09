import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login login = new Login();
            String username, password, firstName, lastName, cell;

            // Username input with validation
            while (true) {
                System.out.print("Enter username: ");
                username = scanner.nextLine().trim();
                if (login.isUsernameValid(username)) {   // Make sure this method exists in Login
                    break;
                }
                System.out.println("Invalid username. Must contain underscore (_) and be <= 5 characters.");
            }

            // Password input
            while (true) {
                System.out.print("Enter password: ");
                password = scanner.nextLine();
                if (login.checkPasswordComplexity(password)) {
                    break;
                }
                System.out.println("Invalid password.");
            }

            // First Name
            while (true) {
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine().trim();
                if (firstName.matches("[a-zA-Z]+")) {
                    break;
                }
                System.out.println("Invalid first name.");
            }

            // Last Name
            while (true) {
                System.out.print("Enter last name: ");
                lastName = scanner.nextLine().trim();
                if (lastName.matches("[a-zA-Z]+")) {
                    break;
                }
                System.out.println("Invalid last name.");
            }

            // Cell Number
            while (true) {
                System.out.print("Enter cell (+27...): ");
                cell = scanner.nextLine().trim();
                if (login.checkCellPhoneNumber(cell)) {
                    break;
                }
                System.out.println("Invalid cell number.");
            }

            String regResult = login.registerUser(username, password, cell, firstName, lastName);
            System.out.println("\n" + regResult);

            if (regResult.equals("User registered successfully!")) {
                System.out.println("\n######################################################");
                System.out.println("#                Welcome to QuickChat                #");
                System.out.println("######################################################");

                boolean running = true;
                while (running) {
                    System.out.println("\nMain Menu:");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show total messages sent");
                    System.out.println("3) Stored Messages");
                    System.out.println("4) Quit");
                    System.out.print("Choose option: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a number only.");
                        scanner.nextLine();
                        continue;
                    }

                    int option = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    switch (option) {
                        case 1 -> sendMessagesFlow(scanner);
                        case 2 -> System.out.println("Total messages sent: " + Messages.returnTotalMessages());
                        case 3 -> storedMessagesMenu(scanner);
                        case 4 -> {
                            running = false;
                            System.out.println("Goodbye!");
                        }
                        default -> System.out.println("Invalid option. Please try again.");
                    }
                }
            }
        }
    }

    //SEND MESSAGES FLOW
    private static void sendMessagesFlow(Scanner scanner) {
        System.out.print("How many messages would you like to send? ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid number.");
            scanner.nextLine();
            return;
        }
        int num = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= num; i++) {
            System.out.println("\n--- Message " + i + " of " + num + " ---");

            Messages msg = new Messages(i);

            // Recipient
            System.out.print("Enter recipient cell number: ");
            String recipient = scanner.nextLine().trim();
            System.out.println(msg.checkRecipientCell(recipient));

            // Message Text
            String text;
            while (true) {
                System.out.print("Enter message (max 250 chars): ");
                text = scanner.nextLine();
                String result = msg.setMessageText(text);
                System.out.println(result);
                if (result.equals("Message ready to send.")) {
                    break;
                }
            }

            msg.createMessageHash();
            System.out.println("\n" + msg.printMessages());

            // Action
            String actionResult = msg.sentMessage(scanner);
            System.out.println(actionResult);
        }
    }

    //STORED MESSAGES MENU
    private static void storedMessagesMenu(Scanner scanner) {
        Messages.loadStoredMessagesFromJSON();   // Load from JSON

        boolean back = false;
        while (!back) {
            System.out.println("\n=== Stored Messages Menu ===");
            System.out.println("1) Display all stored messages");
            System.out.println("2) Display longest stored message");
            System.out.println("3) Search by Message ID");
            System.out.println("4) Search by Recipient");
            System.out.println("5) Delete message by Hash");
            System.out.println("6) Display Full Report");
            System.out.println("0) Back to Main Menu");
            System.out.print("Enter option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> Messages.displayAllStored();
                case 2 -> Messages.displayLongestMessage();
                case 3 -> Messages.searchByMessageID(scanner);
                case 4 -> Messages.searchByRecipient(scanner);
                case 5 -> Messages.deleteByHash(scanner);
                case 6 -> Messages.displayFullReport();
                case 0 -> back = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }
}