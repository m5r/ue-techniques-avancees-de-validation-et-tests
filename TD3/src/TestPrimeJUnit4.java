import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPrimeJUnit4 {

    public static void main(String args[]) {
        String testClass = "TestPrimeJUnit4";
        org.junit.runner.JUnitCore.main(testClass);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        org.jmlspecs.utils.Utils.useExceptions = true;
    }

    @Test
    public void testSequence_0() {
        boolean b = Prime.is_prime(5);
    }

    @Test
    public void testSequence_1() {
        boolean b = Prime.is_prime(4);
    }

    @Test
    public void test_is_prime_aupif() {
        boolean b1 = Prime.is_prime(4);
        assertFalse(b1);

        boolean b2 = Prime.is_prime(5);
        assertTrue(b2);

        boolean b3 = Prime.is_prime(400);
        assertFalse(b3);

        boolean b4 = Prime.is_prime(13);
        assertTrue(b4);

        boolean b5 = Prime.is_prime(2);
        assertTrue(b5);
    }

    @Test
    public void testSequence_10() {
        Prime s = new Prime(5);
    }

    @Test(expected = JmlAssertionError.PreconditionEntry.class)
    public void testSequence_11() {
        Prime s = new Prime(4);
    }

    @Test(expected = JmlAssertionError.PreconditionEntry.class)
    public void testSequence_12() {
        Prime s = new Prime(5);
        s.set_p(4);
    }

    @Test()
    public void testSequence_13() {
        Prime s = new Prime();
        s.set_p(5);
    }
}
