import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
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

public class TestPrimeJUnit4Param {

    @Parameters(name = "{index}: n={0}")
    public static Collection<Object[]> params() {
        ArrayList<Object[]> alo = new ArrayList<Object[]>(10);
        Random rand1 = new Random(1);
        int min = 0;
        int max = 10000;
        for (int i = 0; i < 10; i++) {
            alo.add(new Object[]{rand1.nextInt(max - min + 1) + min});
        }
        return alo;
    }

    @Parameter(value = 0)
    public int n;


    static int nb_inconclusive = 0;


    public static void main(String args[]) {
        String testClass = "TestPrimeJUnit4Param";
        org.junit.runner.JUnitCore.main(testClass);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        nb_inconclusive = 0;
        org.jmlspecs.utils.Utils.useExceptions = true;
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("\n inconclusive tests: " + nb_inconclusive);
    }

    @Test
    public void testSequence_0() {
        boolean b = Prime.is_prime(n);
    }

    // Le test ci-dessous peut devenir inconclusif.
    @Test
    public void testSequence_1() {
        try {
            Prime s = new Prime(n);
        } catch (JmlAssertionError.PreconditionEntry e) {
            System.out.println("\n INCONCLUSIVE " + (new Exception().getStackTrace()[0].getMethodName()) + " with param " + n + "\n\t " + e.getMessage());
            nb_inconclusive++;
        }
    }

}
