import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestDivParZero {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@Test
	public void DeuxPositifs() {
		assertEquals(2, DivisionEntiere.IntDiv(7, 3));
	}
	
	@Test
	public void DeuxNegatifs(){
		assertEquals(3, DivisionEntiere.IntDiv(-12, -4));
	}

	@Test(timeout = 1000)
	public void DivisionParZero() {
		DivisionEntiere.IntDiv(5, 0);
	}
	
	@Test(timeout = 1000)
	public void DivisionParZero2() {
		DivisionEntiere.IntDiv(0, 0);
	}
}
