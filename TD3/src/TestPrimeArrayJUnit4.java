import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPrimeArrayJUnit4 {

    public static void main(String args[]) {
    	String testClass = "TestPrimeArrayJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@Test
	public void  testSequence_0() {
		PrimeArray p = new PrimeArray();
		p.grow();
	}
		
}
