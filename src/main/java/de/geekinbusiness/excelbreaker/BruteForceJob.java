package de.geekinbusiness.excelbreaker;

import java.util.List;
import java.util.function.Function;

public class BruteForceJob implements Runnable {

    BruteForce bruteForce;
    Integer n;
    Function<String, Boolean> testClass;

    public List<String> getMatches() {
        return bruteForce.matches;
    }

    public BruteForceJob(Integer n, Function<String, Boolean> testClass) {
        this.n = n;
        this.testClass = testClass;
        this.bruteForce = new BruteForce(testClass);
    }

    @Override
    public void run() {
        bruteForce.runForLenght(n);
        System.out.println("Matches found: " + bruteForce.matches);
    }

}
