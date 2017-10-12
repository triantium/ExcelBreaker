package de.geekinbusiness.excelbreaker;

import static de.geekinbusiness.excelbreaker.BruteForce.fileNameTemplate;
import java.io.File;
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

    /**
     * Test of buildList method, of class BruteForce.
     */
    @Test
    public void testBuildList() {
        int i = 0;
        File file = new File(String.format(fileNameTemplate, i));;

//        do {
//            file.delete();
//            i++;
//            file = new File(String.format(fileNameTemplate, i));
//        } while (file.canRead());
        BruteForce bf = new BruteForce();

        bf.buildFiles(4);
        System.out.println(bf.computedFiles);
    }

}
