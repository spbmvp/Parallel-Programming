package ru.spbstu.telematics.popov.lab01;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Assert;
import org.junit.Test;

public class MathOperationTestCase {
    @Test
    public void PositiveMatrixAddTest() {
        double[][] m1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] m2 = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        double[][] m3 = {{3, 3, 3}, {3, 3, 3}, {3, 3, 3}};
        RealMatrix res = MatrixUtils.createRealMatrix(m3);
        Math matrix = new Math();
        Assert.assertEquals(res, matrix.MatrixAdd(m1, m2));
    }

    @Test
    public void PositiveMatrixMultiplyTest() {
        double[][] m1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] m2 = {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        double[][] m3 = {{6, 6, 6}, {6, 6, 6}, {6, 6, 6}};
        RealMatrix res = MatrixUtils.createRealMatrix(m3);
        Math matrix = new Math();
        Assert.assertEquals(res, matrix.MatrixMultiply(m1, m2));
    }
}
