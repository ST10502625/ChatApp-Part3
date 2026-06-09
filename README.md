# PROG5121 ChatApp Part 3

A console-based Java chat application built for the PROG5121 module. The app allows a user to register, log in, and send, store, or disregard messages.

---

## Project Structure

```
src/
├── Main.java               # Entry point and menu navigation
├── Login.java              # User registration and login validation
├── Messages.java           # Message creation, sending, storing, and reporting
├── sendMessagesFlow.java   # Helper class for the send messages flow
├── JSONArray.java          # Lightweight JSON array parser (no external libraries)
├── JSONObject.java         # Lightweight JSON object parser (no external libraries)
└── data/
    └── storedMessages.json # Pre-loaded stored messages data

test/
├── LoginTest.java          # JUnit tests for Login functionality
├── MessagesTest.java       # JUnit tests for Messages functionality
├── sendMessagesFlowTest.java
└── RootSuite.java
```

---

## Features

### Part 1 — Registration & Login
- Username must contain an underscore (`_`) and be 5 characters or fewer
- Password must be at least 8 characters and include an uppercase letter, a number, and a special character
- Cell phone number must be in South African international format (`+27XXXXXXXXX`)

### Part 2 — Messaging
- Send multiple messages in a session
- Each message gets a unique 10-digit Message ID and a generated hash
- Messages can be **Sent**, **Stored**, or **Disregarded**
- Tracks total messages sent across the session

### Part 3 — Stored Messages
- Loads stored messages from `src/data/storedMessages.json` at startup
- Display all stored messages
- Find the longest stored message
- Search by Message ID or recipient number
- Delete a message by its hash
- View a full formatted report of all stored messages

---

## Author

Karabo Trishia Marema — PROG5121 Assessment
