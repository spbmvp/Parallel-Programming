package ru.spbstu.telematics.popov.lab01;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileIOTestCase {
    @Test
    public void TouchFileTest()
    {
        String nameFile = "Test";
        FilesIO fileIO = new FilesIO();
        File file = fileIO.touch(nameFile);
        Assert.assertEquals(file.getName(), nameFile);
    }

    @Test
    public void RemoveFileTest()
    {
        String nameFile = "Test";
        FilesIO fileIO = new FilesIO();
        fileIO.touch(nameFile);
        boolean resultRemove = fileIO.rm(nameFile);
        Assert.assertTrue(resultRemove);
    }
}
