package de.geekinbusiness.excelbreaker;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel müller <manuel.mueller@geekinbusiness.de>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //TODO check Arguments
            ExcelBreaker eb = new ExcelBreaker(new File(args[0]));
            BruteForce bruteForce = new BruteForce(eb);
            Integer maximumLenght = Integer.parseInt(args[1]);
            bruteForce.runUntilLenghtReached(maximumLenght);
            System.out.println("Following matches were found: " + bruteForce.matches);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
