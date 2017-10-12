package de.geekinbusiness.excelbreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class BruteFor {

    Logger logger = Logger.getLogger(BruteFor.class.getName());

    final static char upperAsciBound = 126;
    final static char lowerAsciBound = 32;

    char[] charac;

    Function<String, Boolean> testClass;
    List<String> matches = new ArrayList();

    public BruteFor(Function<String, Boolean> testClass) {
        this.testClass = testClass;
    }

    public void process(Integer n) {
        for (int i = 1; i <= n; i++) {
            runFor(i);
        }
    }

    public void runFor(Integer n) {
        char[] charac = new char[n];
        initCharacters(charac);
        start(charac);
        logger.info("Ended at " + String.copyValueOf(charac));
    }

    private void initCharacters(char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            characters[i] = lowerAsciBound;
        }
    }

    private void start(char[] charac) {
        recurseAndTest(charac, charac.length - 1);
    }

    private void recurseAndTest(char[] charac, int modifyAt) {
        if (modifyAt >= 0) {
            for (char j = lowerAsciBound; j < upperAsciBound; j++) {
                charac[modifyAt] = j;
                test(charac);
                recurseAndTest(charac, modifyAt - 1);
            }
        }
    }

    private void test(char[] characters) {
        String test = String.copyValueOf(characters);
        logger.finest("trying with " + test);

        if (testClass.apply(test)) {
            logger.info("match found for: " + test);
            matches.add(test);
            logger.finest(test + "|:|:|added to List");
        }
    }

}
