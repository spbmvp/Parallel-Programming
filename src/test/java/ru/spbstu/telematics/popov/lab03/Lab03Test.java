package ru.spbstu.telematics.popov.lab03;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class Lab03Test {
    @Test
    public void calculatedPi() {
        BigDecimal pi = BigDecimal.ZERO;
        try {
            pi = Lab03.calculatePiThread(4,0.001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(pi, new BigDecimal("3.14355546691102768516"));
    }

}