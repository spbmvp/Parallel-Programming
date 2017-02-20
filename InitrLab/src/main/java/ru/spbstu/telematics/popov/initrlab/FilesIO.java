package ru.spbstu.telematics.popov.initrlab;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.sun.activation.registries.LogSupport.log;

/**
 * Created by mihail on 21.02.17.
 */
public class FilesIO {

    public File touch(String nameFile) {
        File file = FileUtils.getFile(nameFile);
        if (file==null) {
            file = new File(file, nameFile);
        }
        try {
            FileUtils.touch(file);
        } catch (IOException e) {
            log("Problems touching time stamp file in Grails RunOnServer", e);
        }
        return file;
    }

    public boolean rm(String nameFile) {
        File file = FileUtils.getFile(nameFile);
        return FileUtils.deleteQuietly(file);
    }
}
