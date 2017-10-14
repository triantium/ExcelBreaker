package de.geekinbusiness.excelbreaker;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class BruteForce {

    Logger logger = Logger.getLogger(BruteForce.class.getName());

    final static char upperAsciBound = 126;
    final static char lowerAsciBound = 32;

    private String testFileName = "FileNameNotSet";

    Function<String, Boolean> testClass;
    List<String> matches = new ArrayList();

    public BruteForce(Function<String, Boolean> testClass) {
        this.testClass = testClass;
    }

    public BruteForce(Function<String, Boolean> testClass, String fn) {
        this.testClass = testClass;
        this.testFileName = fn;
    }

    public void runUntilLenghtReached(Integer maximumLenght) {
        for (int i = 1; i <= maximumLenght && matches.isEmpty(); i++) {
            runForLenght(i);
        }
    }

    public void runForLenght(Integer n) {
        logger.info("Starting for Lenght " + n);
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
//            logger.info("Rekursiontiefe:\t" + modifyAt);
            for (char j = lowerAsciBound; (j < upperAsciBound && !test(charac)); j++) {
                charac[modifyAt] = j;
//                if (test(charac)) {
//                    return;
//                }
                recurseAndTest(charac, modifyAt - 1);
            }
        }
    }

    private boolean test(char[] characters) {
        String test = String.copyValueOf(characters);
        logger.finest("trying with " + test);
        boolean result = testClass.apply(test);
        if (result) {
            logger.info("match found for: " + test);
            writeToPasswordFile(test);
            logger.finest("File Written");
            matches.add(test);
            logger.finest(test + "|:|:|added to List");
        }
        return result;
    }

    private void writeToPasswordFile(String password) {

        File writeFile = new File("passwords.txt");

        String line = String.format("%s:\t%s\n", this.testFileName, password);
        byte data[] = line.getBytes();
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(
                    Files.newOutputStream(writeFile.toPath(), StandardOpenOption.CREATE, APPEND));

            out.write(data, 0, data.length);
            out.close();
        } catch (IOException x) {
            logger.info(x.getMessage());
        } finally {
            try {
                out.close();

            } catch (IOException ex) {
            }
        }
    }

}
