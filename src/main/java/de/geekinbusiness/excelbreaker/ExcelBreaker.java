package de.geekinbusiness.excelbreaker;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.function.Function;
import java.util.logging.Logger;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;

public class ExcelBreaker implements Function<String, Boolean> {

    Logger logger = Logger.getLogger(ExcelBreaker.class.getName());

    File excelFile;

    NPOIFSFileSystem filesystem;
    EncryptionInfo info;
    Decryptor decryptor;

    public ExcelBreaker(File excelFile) throws IOException {
        this.excelFile = excelFile;
        this.filesystem = new NPOIFSFileSystem(this.excelFile, true);
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

    public void test(String pw) throws IOException {
        NPOIFSFileSystem filesystem = new NPOIFSFileSystem(this.excelFile, true);
        EncryptionInfo info = new EncryptionInfo(filesystem);
        Decryptor d = Decryptor.getInstance(info);

        try {
            if (!d.verifyPassword(pw)) {
                throw new RuntimeException("Unable to process: document is encrypted");
            }

//            InputStream dataStream = d.getDataStream(filesystem);
//            dataStream.close();
            // parse dataStream
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException("Unable to process encrypted document", ex);
        }
    }

}
