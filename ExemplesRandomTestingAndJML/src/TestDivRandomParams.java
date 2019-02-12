import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class TestDivRandomParams {

	@Parameters(name="{index}: x={0},y={1}")
	public static Collection<Object[]> params(){
		 ArrayList<Object[]> alo = new ArrayList<Object[]>(10);
		 Random rand1 = new Random(1);
			for (int i=0;i<1000;i++){
				alo.add(new Object[] {rand1.nextInt(100),rand1.nextInt(100)});
				}
			return alo;
	}	
	
	@Parameter (value = 0)
	public int x;
	@Parameter (value = 1)
	public int y;
	
    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    public static void main(String args[]) {
    	String testClass = "TestDivRandomParams";
     	org.junit.runner.JUnitCore.main(testClass);
     }

   

    private void handleJMLAssertionError(JmlAssertionError e, int paramX, int paramY) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    		System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+" with params "+ paramX + " " + paramY +"\n\t "+ e.getMessage());
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
	public void Division() {
		try {
		int z =  DivisionEntiere.IntDiv(x, y);
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e,x,y); 
			}  
	}
	
}
