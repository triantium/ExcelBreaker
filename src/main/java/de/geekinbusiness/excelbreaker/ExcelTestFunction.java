package de.geekinbusiness.excelbreaker;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.function.Function;
import java.util.logging.Logger;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelTestFunction implements Function<String, Boolean> {

    Logger logger = Logger.getLogger(ExcelTestFunction.class.getName());

    File excelFile;

    POIFSFileSystem filesystem;
    EncryptionInfo info;
    Decryptor decryptor;

    public ExcelTestFunction(File excelFile) throws IOException {
        this.excelFile = excelFile;
        this.filesystem = new POIFSFileSystem(this.excelFile, true);
        this.info = new EncryptionInfo(this.filesystem);
        this.decryptor = Decryptor.getInstance(this.info);
    }

    @Override
    public Boolean apply(String password) {
        try {
            return decryptor.verifyPassword(password);
        } catch (GeneralSecurityException ex) {
            logger.info("Unable to process encrypted document: " + ex.getMessage());
        }
        return false;
    }

}
