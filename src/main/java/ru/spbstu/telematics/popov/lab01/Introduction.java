package ru.spbstu.telematics.popov.lab01;

/**
 * Вводная лабораторная работа.
 * В данной лабораторной работе произведено изучение Gradle,
 * сборки проекта, команды операционной системы Linux и математические
 * операции на примере библиотек commons-io и commons-math3
 */
public class Introduction {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        /*
        *Пример использования commons-io на примере работы с файлами
         */
        FilesIO fileIO = new FilesIO();
        fileIO.touch("Test");
        fileIO.rm("Test");

        /*
        *Пример использования commons-io на примере работы с файлами
         */
        double[][] m1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] m2 = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        Math matrix = new Math();
        matrix.matrixOutput(matrix.matrixAdd(m1, m2));
        matrix.matrixOutput(matrix.matrixMultiply(m1, m2));
    }
}


