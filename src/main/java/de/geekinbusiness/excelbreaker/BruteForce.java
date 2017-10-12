package de.geekinbusiness.excelbreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class BruteForce {

    Logger logger = Logger.getLogger(BruteForce.class.getName());

    final static char upperAsciBound = 126;
    final static char lowerAsciBound = 32;

    Function<String, Boolean> testClass;
    List<String> matches = new ArrayList();

    public BruteForce(Function<String, Boolean> testClass) {
        this.testClass = testClass;
    }

    public void runUntilLenghtReached(Integer maximumLenght) {
        for (int i = 1; i <= maximumLenght; i++) {
            runForLenght(i);
        }
    }

    public void runForLenght(Integer n) {
        char[] charac = initCharacters(n);
        beginWithRecursion(charac);
        logger.info("Ended at " + String.copyValueOf(charac));
    }

    private char[] initCharacters(int lenght) {
        char[] charac = new char[lenght];
        for (int i = 0; i < lenght; i++) {
            charac[i] = lowerAsciBound;
        }
        return charac;
    }

    private void beginWithRecursion(char[] charac) {
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
