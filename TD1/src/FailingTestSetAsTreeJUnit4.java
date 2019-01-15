import org.jmlspecs.utils.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class FailingTestSetAsTreeJUnit4 {

    @org.jmlspecs.annotation.SkipRac
    public static void main(String args[])  {
    	
    	String testClass = "FailingTestSetAsTreeJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}


	@Test(expected = JmlAssertionError.class)
	public void  testSequence_1() {
		SetAsTree s5=new SetAsTree(5);
		SetAsTree s1=new SetAsTree(1);
		s5.setRtree(s1);
	}

	@Test(expected = JmlAssertionError.class)
	public void  testSequence_2() {
		SetAsTree s5=new SetAsTree(5);
		SetAsTree s0=new SetAsTree(0);
		s5.setLtree(s0);
		s0.setVal(null);
		s5.skip();
	}

	@Test(expected = JmlAssertionError.class)
	public void  testSequence_3() {
		SetAsTree s5=new SetAsTree(5);
		SetAsTree s6=new SetAsTree(6);
		s5.setLtree(s6);
	}
}
