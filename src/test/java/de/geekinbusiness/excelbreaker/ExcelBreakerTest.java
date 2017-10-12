package de.geekinbusiness.excelbreaker;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
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
    public void testApply() throws IOException {
        ExcelBreaker eb = new ExcelBreaker(new File("test.xlsx"));
        Assert.assertTrue(eb.apply("test").booleanValue());
        Assert.assertTrue(!eb.apply("wrong").booleanValue());
    }

    /**
     * Test of test method, of class ExcelBreaker.
     */
//    @Ignore
//    @Test
//    public void testTest() throws Exception {
//        ExcelBreaker eb = new ExcelBreaker(new File("test.xlsx"));
////        eb.test("test");
////        eb.test("wrong");
//    }
}
