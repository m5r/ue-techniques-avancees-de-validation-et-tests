import static org.junit.Assert.*;

import org.jmlspecs.utils.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestSetAsTreeJUnit4 {

    public static void main(String args[]) {
    	String testClass = "TestSetAsTreeJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@Test
	public void  testSequence_0() {
		SetAsTree s=new SetAsTree(5);
		s.insert(10);
		s.insert(1);
		s.insert(0);
		s.insert(2);
		s.insert(20);
		s.delete(5);
	}

}
