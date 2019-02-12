import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDivParZero4JML {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;


    public static void main(String args[]) {
    	String testClass = "TestDivParZero4JML";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}



	@Test
	public void DeuxPositifs() {
		try{
		assertEquals(2, DivisionEntiere.IntDiv(7, 3));
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e); 
	
		}  
	}
	
	@Test
	public void DeuxNegatifs(){
		try{
		assertEquals(3, DivisionEntiere.IntDiv(-12, -4));
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e); 
	
		}  
	}

	@Test(timeout = 1000)
	public void DivisionParZero() {
		try{
		DivisionEntiere.IntDiv(5, 0);
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e); 
	
		}  
	}
	
	@Test(timeout = 1000)
	public void DivisionParZero2() {
		try{
		DivisionEntiere.IntDiv(0, 0);
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e); 
	
		}  
	}
}
