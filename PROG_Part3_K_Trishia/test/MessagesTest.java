import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MessagesTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMessageLengthSuccess() {
        Messages msg = new Messages(1);

        String result = msg.setMessageText("Hello this is a short message");

        assertEquals("Message ready to send.", result);
        assertEquals("Hello this is a short message", msg.getMessageText());
    }

    @Test
    public void testMessageLengthFailure() {
        Messages msg = new Messages(1);

        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }

        String result = msg.setMessageText(longMsg.toString());

        assertTrue(result.contains("Message exceeds 250 characters by"));
    }

    @Test
    public void testRecipientSuccess() {
        Messages msg = new Messages(1);

        String result = msg.checkRecipientCell("+27831234567");

        assertEquals("Cell phone number successfully captured.", result);
        assertEquals("+27831234567", msg.getRecipient());
    }

    @Test
    public void testRecipientFailure() {
        Messages msg = new Messages(1);

        String result = msg.checkRecipientCell("0812345678");

        assertEquals(
            "Cell phone number incorrectly formatted or does not contain an international code.",
            result
        );
    }

    @Test
    public void testMessageIDValid() {
        Messages msg = new Messages(1);

        assertNotNull(msg.getMessageID());
        assertEquals(10, msg.getMessageID().length());
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageHashGeneration() {
        Messages msg = new Messages(1);

        msg.checkRecipientCell("+27831234567");
        msg.setMessageText("Hi Mike can you join us tonight");
        String hash = msg.createMessageHash();

        assertNotNull(hash);
        assertTrue(hash.contains(":1:"));
    }

    @Test
    public void testSendMessageOption1() {
        Messages msg = new Messages(1);

        msg.checkRecipientCell("+27831234567");
        msg.setMessageText("Test message");
        msg.createMessageHash();

        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        String result = msg.sentMessage(scanner);

        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSendMessageOption2() {
        Messages msg = new Messages(1);

        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        String result = msg.sentMessage(scanner);

        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testSendMessageOption3() {
        Messages msg = new Messages(1);

        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        String result = msg.sentMessage(scanner);

        assertEquals("Message successfully stored.", result);
    }

    /**
     * Test of loadStoredMessagesFromJSON method, of class Messages.
     */
    @Test
    public void testLoadStoredMessagesFromJSON() {
        System.out.println("loadStoredMessagesFromJSON");
        Messages.loadStoredMessagesFromJSON();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageID method, of class Messages.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        Messages instance = null;
        String expResult = "";
        String result = instance.getMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class Messages.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        Messages instance = null;
        String expResult = "";
        String result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageText method, of class Messages.
     */
    @Test
    public void testGetMessageText() {
        System.out.println("getMessageText");
        Messages instance = null;
        String expResult = "";
        String result = instance.getMessageText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class Messages.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        Messages instance = null;
        String expResult = "";
        String result = instance.getMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlag method, of class Messages.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        Messages instance = null;
        String expResult = "";
        String result = instance.getFlag();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageID method, of class Messages.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Messages instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class Messages.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String number = "";
        Messages instance = null;
        String expResult = "";
        String result = instance.checkRecipientCell(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessageText method, of class Messages.
     */
    @Test
    public void testSetMessageText() {
        System.out.println("setMessageText");
        String text = "";
        Messages instance = null;
        String expResult = "";
        String result = instance.setMessageText(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class Messages.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Messages instance = null;
        String expResult = "";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessages method, of class Messages.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Messages instance = null;
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sentMessage method, of class Messages.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        Scanner scanner = null;
        Messages instance = null;
        String expResult = "";
        String result = instance.sentMessage(scanner);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayAllStored method, of class Messages.
     */
    @Test
    public void testDisplayAllStored() {
        System.out.println("displayAllStored");
        Messages.displayAllStored();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLongestMessage method, of class Messages.
     */
    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        Messages.displayLongestMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByMessageID method, of class Messages.
     */
    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        Scanner scanner = null;
        Messages.searchByMessageID(scanner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchByRecipient method, of class Messages.
     */
    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        Scanner scanner = null;
        Messages.searchByRecipient(scanner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByHash method, of class Messages.
     */
    @Test
    public void testDeleteByHash() {
        System.out.println("deleteByHash");
        Scanner scanner = null;
        Messages.deleteByHash(scanner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayFullReport method, of class Messages.
     */
    @Test
    public void testDisplayFullReport() {
        System.out.println("displayFullReport");
        Messages.displayFullReport();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class Messages.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 0;
        int result = Messages.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class Messages.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Messages.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}