package ru.spbstu.telematics.popov.lab01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FilesIO {
    /**
     * Метод изменяет время последнего изменения файла
     * или время последнего доступа к нему, при утсотвии такого файла создается новый.
     *
     * @param nameFile - имя создаваемого файла,
     *                 либо обновить информацию о времени существующего файла
     * @return возвращает дескриптор файла, либо null
     */
    public File touch(String nameFile) {
        File file = FileUtils.getFile(nameFile);
        if (file == null) {
            file = new File(file, nameFile);
            System.out.println("Create new file " + nameFile);
        }
        try {
            FileUtils.touch(file);
            System.out.println("Touch file " + nameFile);
        } catch (IOException e) {
            System.out.println("Problems touching" + e);
            return null;
        }
        return file;
    }

    /**
     * Метод удаляет существующий файл, не распространяется на директории
     *
     * @param nameFile - имя удаляемого файла
     * @return возвращает true при успешном удалении, либо false при ошибке
     */
    public boolean rm(String nameFile) {
        File file = FileUtils.getFile(nameFile);
        boolean resultRemove = FileUtils.deleteQuietly(file);
        if (resultRemove) {
            System.out.println("Remove file " + nameFile);
        }
        return resultRemove;
    }
}
