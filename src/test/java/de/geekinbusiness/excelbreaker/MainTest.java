package de.geekinbusiness.excelbreaker;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class MainTest {

    public MainTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        Main.main(new String[]{"12.xlsx", "2"});
    }

    @Ignore
    @Test
    public void testMainComplex() {
        Main.main(new String[]{"test.xlsx", "4"});
    }

}
