package ru.spbstu.telematics.popov.lab01;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealMatrixFormat;

public class Math {
    /**
     * Метод сложения двух матриц
     *
     * @param matrix1,matrix2- матрицы
     * @return возвращает результат суммирования матриц
     */
    public RealMatrix matrixAdd(double[][] matrix1, double[][] matrix2) {
        RealMatrix res = null;
        RealMatrix m = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix n = MatrixUtils.createRealMatrix(matrix2);
        try {
            res = m.add(n);
            System.out.println("");
            matrixOutput(m);
            System.out.println("+");
            matrixOutput(n);
            System.out.println("=");
        } catch (MatrixDimensionMismatchException e) {
            System.out.println("Add exception: secondMatrix is not the same size as firstMatrix; " + e);
        }
        return res;
    }

    /**
     * Метод перемномножения двух матриц
     *
     * @param matrix1,matrix2 - матрицы
     * @return возвращает результат перемножения двух матриц
     */
    public RealMatrix matrixMultiply(double[][] matrix1, double[][] matrix2) {
        RealMatrix res = null;
        RealMatrix m = MatrixUtils.createRealMatrix(matrix1);
        RealMatrix n = MatrixUtils.createRealMatrix(matrix2);
        try {
            res = m.multiply(n);
            System.out.println("");
            matrixOutput(m);
            System.out.println("*");
            matrixOutput(n);
            System.out.println("=");
        } catch (DimensionMismatchException e) {
            System.out.println("Multiply exception: columnDimension(firstMatrix) != rowDimension(secondMatrix); " + e);
        }
        return res;
    }

    /**
     * Метод вывода матрицы в основной поток
     *
     * @param m - матрица
     */
    public void matrixOutput(RealMatrix m) {
        RealMatrixFormat formatter = new RealMatrixFormat("\t", "", "", "", "\n\t", "\t");
        System.out.println(formatter.format(m));
    }
}
