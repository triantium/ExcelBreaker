package de.geekinbusiness.excelbreaker;

import de.geekinbusiness.excelbreaker.BruteForceTest.testBooleanSup;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class BruteForceTest {

    public BruteForceTest() {
    }

    @Before
    public void setUp() {
    }

    public class testBooleanSup implements Function<String, Boolean> {

        String match = "#";
        String match2 = "zhn8";

        @Override
        public Boolean apply(String t) {
//            System.out.println("trying with " + t);
            return (t.equals(match) || t.equals(match2));
        }

    }

    /**
     * Test of process method, of class BruteFor.
     */
    @Test
    public void testProcess() {
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start);
        BruteForce bf = new BruteForce(new testBooleanSup());
        bf.process(5);

        LocalDateTime end = LocalDateTime.now();
        System.out.println(end);
        long seconds = ChronoUnit.SECONDS.between(start, end);
        System.out.println("Needed " + seconds + "s");
        System.out.println("matches: " + bf.matches);
        Assert.assertEquals(2, bf.matches.size());
    }

}
