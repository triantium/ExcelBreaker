package de.geekinbusiness.excelbreaker;

import java.util.function.Function;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class BruteForceJobTest {

    public BruteForceJobTest() {
    }

    public class testBooleanSup implements Function<String, Boolean> {

        String match = "#";
        String match2 = "zhn8";

        @Override
        public Boolean apply(String t) {
            return (t.equals(match) || t.equals(match2));
        }
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of run method, of class BruteForceJob.
     */
    @Test
    public void testRun() {
        for (int i = 0; i < 8; i++) {

            BruteForceJob bruteForceJob = new BruteForceJob(i, new testBooleanSup());
            bruteForceJob.run();
        }
    }

}
