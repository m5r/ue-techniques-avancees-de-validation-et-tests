import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesJUnit4Public {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
        String testClass = "TestExplosivesJUnit4Public";
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
    public void test_public_prop_6() {
        try {
            e = new Explosives();
            e.incomp[e.nb_inc][0] = "Prod_A";
            e.incomp[e.nb_inc][1] = "Prod_B";
            e.nb_inc = e.nb_inc + 1;
            e.skip();
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_public_prop_8() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            e.add_assign("Bat_1", "Prod_Nitro");
            e.nb_assign = e.nb_assign + 1;
            e.assign[e.nb_assign][0] = "Bat_1";
            e.assign[e.nb_assign][1] = "Prod_Nitro";
            e.skip();
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }
}
