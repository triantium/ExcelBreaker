package de.geekinbusiness.excelbreaker;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
@Ignore
public class MainTest {

    public MainTest() {
    }

    @Before
    public void setUp() {
    }

    @Ignore
    @Test
    public void testInvalidCall() {
        System.out.println("de.geekinbusiness.excelbreaker.MainTest.testInvalidCall()");
        Main.main(new String[]{""});
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        System.out.println("de.geekinbusiness.excelbreaker.MainTest.testMain()");
        Main.main(new String[]{"12.xlsx", "2", "false"});
    }

    @Ignore
    @Test
    public void testMissingFile() {
        System.out.println("de.geekinbusiness.excelbreaker.MainTest.testMissingFile()");
        Main.main(new String[]{"missing.xlsx", "2"});
    }

    @Ignore
    @Test
    public void testMainComplex() {
        System.out.println("de.geekinbusiness.excelbreaker.MainTest.testMainComplex()");
        Main.main(new String[]{"test.xlsx", "4", "false"});
    }

}
