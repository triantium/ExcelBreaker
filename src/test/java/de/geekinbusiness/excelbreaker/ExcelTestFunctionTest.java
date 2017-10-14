package de.geekinbusiness.excelbreaker;

import java.io.File;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class ExcelTestFunctionTest {

    public ExcelTestFunctionTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of apply method, of class ExcelBreaker.
     */
    @Test
    public void testApply() throws IOException {
        System.out.println("de.geekinbusiness.excelbreaker.ExcelTestFunctionTest.testApply()");
        ExcelTestFunction eb = new ExcelTestFunction(new File("test.xlsx"));
        Assertions.assertThat(eb.apply("test").booleanValue()).isTrue();
        Assertions.assertThat(eb.apply("wrong").booleanValue()).isFalse();
    }

    /**
     * Test of test method, of class ExcelBreaker.
     */
    @Test
    public void testTest() throws Exception {
        System.out.println("de.geekinbusiness.excelbreaker.ExcelTestFunctionTest.testTest()");
        ExcelTestFunction eb = new ExcelTestFunction(new File("12.xlsx"));
        BruteForce bf = new BruteForce(eb);
        bf.runForLenght(2);
        Assertions.assertThat(bf.matches.size()).isBetween(1, 2);
        assertThat(bf.matches.get(0)).isEqualToIgnoringCase("12");
        for (String match : bf.matches) {
            Assertions.assertThat(eb.apply(match).booleanValue()).isTrue();
        }
    }
}
