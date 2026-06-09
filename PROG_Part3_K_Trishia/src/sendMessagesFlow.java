import java.util.Scanner;

/**
 * Helper class for the send-messages flow.
 * Note: Main.java contains its own inline copy of this logic.
 * This class is kept for reference/modularity.
 */
public class sendMessagesFlow {

    public static void run(Scanner scanner) {
        System.out.print("How many messages would you like to send? ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        int numMessages = scanner.nextInt();
        scanner.nextLine();

        int successfulSends = 0;

        for (int i = 1; i <= numMessages; i++) {
            System.out.println("\n--- Message " + i + " of " + numMessages + " ---");

            Messages msg = new Messages(i);

            // Recipient Input
            System.out.print("Enter recipient cell number: ");
            String recipient = scanner.nextLine().trim();
            String recipientStatus = msg.checkRecipientCell(recipient);
            System.out.println(recipientStatus);

            if (!recipientStatus.equals("Cell phone number successfully captured.")) {
                System.out.println("Skipping this message due to invalid recipient...");
                continue;
            }

            // Message Text Input
            while (true) {
                System.out.print("Enter message (max 250 chars): ");
                String text = scanner.nextLine();
                String msgStatus = msg.setMessageText(text);
                System.out.println(msgStatus);
                if (msgStatus.equals("Message ready to send.")) break;
            }

            // Generate Hash and Show Details
            msg.createMessageHash();
            System.out.println("\nMessage Details:");
            System.out.println(msg.printMessages());

            // Action Choice (Send / Disregard / Store)
            String actionResult = msg.sentMessage(scanner);
            System.out.println(actionResult);

            if (actionResult.equals("Message successfully sent.")) {
                successfulSends++;
            }
        }

        System.out.println("\n=================================");
        System.out.println("Session Summary");
        System.out.println("Messages sent this session : " + successfulSends);
        System.out.println("Total messages sent overall: " + Messages.returnTotalMessages());
        System.out.println("=================================");
    }
}
