package de.geekinbusiness.excelbreaker;

import java.io.File;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class ExcelBreakerTest {

    public ExcelBreakerTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of apply method, of class ExcelBreaker.
     */
    @Test
    public void testApply() {
    }

    /**
     * Test of test method, of class ExcelBreaker.
     */
    @Ignore
    @Test
    public void testTest() throws Exception {
        ExcelBreaker eb = new ExcelBreaker(new File("test.xlsx"));
//        eb.test("test");
//        eb.test("wrong");
    }

}
