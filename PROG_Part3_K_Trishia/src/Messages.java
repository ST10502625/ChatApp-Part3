import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Messages {

    //STATIC COLLECTIONS
    private static final ArrayList<Messages> sentMessages = new ArrayList<>();
    private static final ArrayList<Messages> storedMessages = new ArrayList<>();
    private static final ArrayList<Messages> disregardedMessages = new ArrayList<>();

    private static int totalMessages = 0;

    //INSTANCE VARIABLES
    private String messageID;
    private final int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String flag;   // "Sent", "Stored", "Disregarded"

    // Constructor
    public Messages(int messageNumber) {
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    // Generate 10-digit Message ID
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append((int) (Math.random() * 10));
        }
        return id.toString();
    }

    //GETTERS
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }

    //VALIDATION METHODS
    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    public String checkRecipientCell(String number) {
        if (number != null && number.matches("^\\+27\\d{9}$")) {
            this.recipient = number;
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain an international code.";
        }
    }

    public String setMessageText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Message cannot be empty. Please enter a valid message.";
        }
        if (text.length() > 250) {
            int extra = text.length() - 250;
            return "Message exceeds 250 characters by " + extra;
        }
        this.messageText = text;
        return "Message ready to send.";
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        String cleaned = messageText.replaceAll("\\s+", "").replaceAll("[^a-zA-Z]", "").toUpperCase();
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":" + cleaned;
        return messageHash;
    }

    public String printMessages() {
        return "Message ID : " + messageID +
               "\nHash       : " + (messageHash != null ? messageHash : "N/A") +
               "\nRecipient  : " + recipient +
               "\nMessage    : " + messageText;
    }

    //SEND / STORE / DISREGARD
    public String sentMessage(Scanner scanner) {
        System.out.println("\n1) Send");
        System.out.println("2) Disregard");
        System.out.println("3) Store");
        System.out.print("Choose action: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                this.flag = "Sent";
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            }
            case 2 -> {
                this.flag = "Disregarded";
                disregardedMessages.add(this);
                return "Press 0 to delete the message.";
            }
            case 3 -> {
                this.flag = "Stored";
                storedMessages.add(this);
                return "Message successfully stored.";
            }
            default -> {
                return "Invalid option.";
            }
        }
    }

    //STORED MESSAGES METHODS

    public static void loadStoredMessagesFromJSON() {
        storedMessages.clear();
        try {
            String path = "src/data/storedMessages.json";
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Messages msg = new Messages(i + 1);
                msg.messageID = obj.getString("messageID");
                msg.recipient = obj.getString("recipient");
                msg.messageText = obj.getString("messageText");
                msg.messageHash = obj.getString("messageHash");
                msg.flag = "Stored";
                storedMessages.add(msg);
            }
            System.out.println(jsonArray.length() + " stored messages loaded from JSON.");
        } catch (IOException e) {
            System.out.println("Warning: Could not load JSON file. " + e.getMessage());
        }
    }

    public static void displayAllStored() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages found.");
            return;
        }
        for (Messages msg : storedMessages) {
            System.out.println("\n" + msg.printMessages());
        }
    }

    public static void displayLongestMessage() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        Messages longest = storedMessages.get(0);
        for (Messages msg : storedMessages) {
            if (msg.messageText.length() > longest.messageText.length()) {
                longest = msg;
            }
        }
        System.out.println("Longest Stored Message:");
        System.out.println(longest.printMessages());
    }

    public static void searchByMessageID(Scanner scanner) {
        System.out.print("Enter Message ID: ");
        String id = scanner.nextLine();
        for (Messages msg : storedMessages) {
            if (msg.messageID.equals(id)) {
                System.out.println("Found:\n" + msg.printMessages());
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    public static void searchByRecipient(Scanner scanner) {
        System.out.print("Enter recipient number: ");
        String recip = scanner.nextLine();
        boolean found = false;
        for (Messages msg : storedMessages) {
            if (msg.recipient.equals(recip)) {
                System.out.println(msg.printMessages());
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");
    }

    public static void deleteByHash(Scanner scanner) {
        System.out.print("Enter Message Hash to delete: ");
        String hash = scanner.nextLine();
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).messageHash.equals(hash)) {
                Messages deleted = storedMessages.remove(i);
                System.out.println("Message: \"" + deleted.messageText + "\" successfully deleted.");
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    public static void displayFullReport() {
        System.out.println("\nFULL STORED MESSAGES REPORT");
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages to display.");
            return;
        }
        for (Messages msg : storedMessages) {
            System.out.println("Message ID : " + msg.messageID);
            System.out.println("Recipient   : " + msg.recipient);
            System.out.println("Message     : " + msg.messageText);
            System.out.println("Hash        : " + msg.messageHash);
            System.out.println("Flag        : " + msg.flag);
            System.out.println("---------------------------------------------");
        }
    }

    //STATIC HELPERS
    public static int returnTotalMessages() {
        return totalMessages;
    }

    public static void reset() {
        totalMessages = 0;
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
    }
}
