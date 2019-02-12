import static org.junit.Assert.*;

import java.util.Random;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRandom4JML {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;


    public static void main(String args[]) {
    	String testClass = "TestRandom4JML";
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



	@Test(timeout = 1000)
	public void Gen1000000paires() {
		int x = 0;
		int y = 0;
		int z = 0;
		try{
				Random rand1 = new Random(1);
				System.out.println("Debut du test");
				for (int i=0;i<1000000;i++){
					 x = rand1.nextInt();
					 y = rand1.nextInt();
					 z = DivisionEntiere.IntDiv(x,y);
				}
				System.out.println("Fin du test");

		} 	catch(JmlAssertionError e){
			System.out.println("x = "+x+" y= "+y+" z= "+z);
			handleJMLAssertionError(e); 
	
		}  
	}
	
}
