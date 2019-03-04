import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
        String testClass = "TestExplosivesJUnit4";
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
    public void test_prop_1() {
        try {
            e = new Explosives();
            for (int i = 0; i < 26; i++) {
                e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            }
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_2() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            for (int i = 0; i < 31; i++) {
                String prod = "Prod_Nitro_" + i;
                e.add_assign("Bat_1", prod);
            }
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_3() {
        try {
            e = new Explosives();
            e.add_incomp("Pas_Prod_Nitro", "Pas_Prod_Glycerine");
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_4() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            e.add_assign("Pas_Bat_1", "Prod_Nitro");
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_5() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Nitro");
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_6() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            assertEquals(e.incomp[0][0], e.incomp[1][1]);
            assertEquals(e.incomp[0][1], e.incomp[1][0]);
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }

    @Test
    public void test_prop_7() {
        try {
            e = new Explosives();
            e.add_incomp("Prod_Nitro", "Prod_Glycerine");
            e.add_assign("Bat_1", "Prod_Nitro");
            e.add_assign("Bat_1", "Prod_Glycerine");
        } catch (JmlAssertionError err) {
            handleJMLAssertionError(err);
        }
    }
}
