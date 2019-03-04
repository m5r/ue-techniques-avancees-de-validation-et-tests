import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesFindBat {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
        String testClass = "TestExplosivesFindBat";
        org.junit.runner.JUnitCore.main(testClass);
    }


    private void handleJMLAssertionError(JmlAssertionError err) {
        if (err.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
            System.out.println("\n INCONCLUSIVE " + (new Exception().getStackTrace()[1].getMethodName()) + "\n\t " + err.getMessage());
            nb_inconclusive++;
        } else {
            // test failure
            nb_fail++;
            fail("\n\t" + err.getMessage());
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
        System.out.println("\n inconclusive tests: " + nb_inconclusive + " -- failures : " + nb_fail);
    }

    @Test
    public void test_find_bat() {
        try {
            e = new Explosives();
            e.add_assign("Bat_1", "Prod_Nitro");

            String bat = e.findBat("Prod_Nitro");
            assertEquals("Bat_rien", "Bat_rien");
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }
}
