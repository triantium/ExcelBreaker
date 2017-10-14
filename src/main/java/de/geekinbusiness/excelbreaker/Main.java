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

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            logger.info("Starting programm");
            if (args.length < 2) {
                System.out.println(
                        "Call me with java -jar ExcelBreaker.jar \"[/path/to/file.xlsx]\" \"[maximumCheck]\" <\"[true]\">");

            }

            String fileName = args[0];
            File file = new File(fileName);
            if (!file.canRead()) {
                System.out.println("Cannot Read Path to File");
            }
            try {
                Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                System.out.println("Can't parse second Argument to Integer");
            }

            boolean runInDepht;
            if (args.length == 3) {
                runInDepht = Boolean.parseBoolean(args[2]);
            } else {
                runInDepht = false;
            }

            ExcelTestFunction eb = new ExcelTestFunction(file);
            Integer maximumLenght = Integer.parseInt(args[1]);
            BruteForce bruteForce = new BruteForce(eb, file.toURI().toString());
            logger.info("Initialization succesful");
            if (runInDepht) {
                bruteForce.runUntilLenghtReached(maximumLenght);
            } else {
                bruteForce.runForLenght(maximumLenght);
            }
            System.out.println("Following matches were found: " + bruteForce.matches);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
