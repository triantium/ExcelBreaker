package de.geekinbusiness.excelbreaker;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class BruteForce {

    Logger logger = Logger.getLogger(BruteForce.class.getName());

    HashMap<Integer, List<String>> computedStringListMap = new HashMap<>();
    HashMap<Integer, File> computedFiles = new HashMap<>();

    public List<String> allElements = new ArrayList<>();

    public final static String fileNameTemplate = "passwords_%d.txt";

    //http://www.asciitable.com
    final static char upperAsciBound = 126;
    final static char lowerAsciBound = 32;

    public BruteForce() {
        List<String> startList = new ArrayList<>();
        startList.add("");

        int i = 0;
        File file = new File(String.format(fileNameTemplate, i));;
        appendToFile("", i);
        do {
            computedFiles.put(i, file);
            i++;
            file = new File(String.format(fileNameTemplate, i));
        } while (file.canRead());

    }

    public void buildFiles(int stringLength) {
        if (stringLength > 1) {
            buildFiles(stringLength - 1);
        }
        if (stringLength > 0) {
            if (!computedFiles.containsKey(stringLength));
            File writeFile = new File(String.format(fileNameTemplate, stringLength));
            computedFiles.put(stringLength, writeFile);
            Charset charset = Charset.forName("UTF-8");
            try (BufferedReader reader = Files.newBufferedReader(computedFiles.get(stringLength - 1).toPath(), charset)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    //logger.finest(line);
                    line.trim();
                    if (line.length() + 1 == stringLength) {
                        for (Character i = lowerAsciBound; i <= upperAsciBound; i++) {
                            appendToFile(line.concat(i.toString()), stringLength);
                        }
                    }
                }
                reader.close();

            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }

            logger.info("Werte für " + stringLength + " berechnet");
        }
    }

    private void appendToFile(String string, Integer i) {
        File file = computedFiles.get(i);
        if (file == null || !file.canWrite()) {
            file = new File(String.format(fileNameTemplate, i));
        }
        string = string.concat("\n");
        byte data[] = string.getBytes();
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(
                    Files.newOutputStream(file.toPath(), StandardOpenOption.CREATE, APPEND));

            out.write(data, 0, data.length);
            out.close();
        } catch (IOException x) {
            System.err.println(x);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }
}
