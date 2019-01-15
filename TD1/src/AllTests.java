import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FailingTestSetAsTreeJUnit4.class, TestSetAsTreeJUnit4.class })
public class AllTests {

    public static void main(String args[]) {
    	String testClass = "AllTests";
     	org.junit.runner.JUnitCore.main(testClass);
     }

}
