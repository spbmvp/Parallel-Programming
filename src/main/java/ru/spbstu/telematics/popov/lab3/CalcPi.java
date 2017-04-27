package ru.spbstu.telematics.popov.lab3;

public class CalcPi {
    private double pi;
    private double square;
    private double dx;
    public int countOperation = 0;

    public CalcPi(double dx) {
        this.pi = 0.0;
        this.square = 0.0;
        this.dx = dx;
    }

    public double getSquare() {
        return square * dx;
    }

    public synchronized void addSquere(double s) {
        this.square += s;
    }

    public synchronized void iterOperation() {
        this.countOperation++;
    }

    public synchronized double calcPi() {
        this.pi = 4 * this.square * dx;
        return this.pi;
    }
}
