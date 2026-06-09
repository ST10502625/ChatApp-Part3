import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MessagesTest.class,
    sendMessagesFlowTest.class,
    LoginTest.class
})
public class RootSuite {
    // No code needed here
}