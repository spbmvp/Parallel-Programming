package ru.spbstu.telematics.popov.initrlab;

/**
 * Вводная лабораторная работа.
 * В данной лабораторной работе произведено изучение Gradle,
 * сборки проекта, команды операционной системы Linux и математические
 * операции на примере библиотек commons-io и commons-math3
 */
public class Introduction {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        FilesIO fileIO = new FilesIO();
        fileIO.touch("Test");
        fileIO.rm("Test");
    }
}


