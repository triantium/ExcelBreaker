package de.geekinbusiness.excelbreaker;

import de.geekinbusiness.excelbreaker.BruteForceTest.testBooleanSup;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
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

        String match = "ee#";
        String match2 = "8e";
        String match3 = "8e#";

        @Override
        public Boolean apply(String t) {
            return (t.equals(match) || t.equals(match2) || t.equals(match3));
        }
    }

    public class testBooleanSpeed implements Function<String, Boolean> {

        @Override
        public Boolean apply(String t) {
            return false;
        }
    }

    /**
     * Test of process method, of class BruteFor.
     */
    @Test
    public void testProcess() {
        System.out.println("de.geekinbusiness.excelbreaker.BruteForceTest.testProcess()");
        LocalDateTime start = LocalDateTime.now();
        System.out.println(start);
        BruteForce bf = new BruteForce(new testBooleanSup());
        bf.runUntilLenghtReached(8);
        LocalDateTime end = LocalDateTime.now();
        System.out.println(end);
        long seconds = ChronoUnit.SECONDS.between(start, end);
        System.out.println("Needed " + seconds + "s");
        System.out.println("matches: " + bf.matches);
        Assertions.assertThat(bf.matches.size()).isBetween(1, 6);
    }

    @Test
    public void speedTest() {
        System.out.println("de.geekinbusiness.excelbreaker.BruteForceTest.speedTest()");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        int i = 0;
        for (long seconds = 0; (seconds < 100 && i < 6); i++) {
            start = LocalDateTime.now();
            System.out.println(start);
            BruteForce bf = new BruteForce(new testBooleanSpeed());
            bf.runForLenght(i);
            end = LocalDateTime.now();
            seconds = ChronoUnit.SECONDS.between(start, end);
            System.out.println(end);
            if (seconds < 10) {
                System.out.println("Needed " + ChronoUnit.MILLIS.between(start, end) + " ms");
            } else {
                System.out.println("Needed " + seconds + "s");
            }

        }
        System.out.println("Run for lenght " + i);
    }

}
