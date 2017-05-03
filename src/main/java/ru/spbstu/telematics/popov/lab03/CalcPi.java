package ru.spbstu.telematics.popov.lab03;

import java.math.BigDecimal;

public class CalcPi {
    private BigDecimal pi;
    private BigDecimal square;
    private BigDecimal dx;
    public int countOperation = 0;

    public CalcPi(BigDecimal dx) {
        this.pi = BigDecimal.ZERO;
        this.square = BigDecimal.ZERO;
        this.dx = dx;
    }

    public synchronized BigDecimal getSquare() {
        return square.multiply(dx);
    }

    public synchronized void addSquere(BigDecimal s) {
        this.square = this.square.add(s);
    }

    public synchronized void iterOperation() {
        this.countOperation++;
    }

    public synchronized BigDecimal calcPi() {
        this.pi = getSquare().multiply(new BigDecimal(4.0));
        return this.pi;
    }
}
