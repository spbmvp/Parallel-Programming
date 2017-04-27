package ru.spbstu.telematics.popov.lab3;

import java.util.ArrayList;
import java.util.List;

public class Lab3 {
    private static CalcPi p;

    public static void main(String[] args) throws InterruptedException {
        for(int i = 10; i<=10; i++){
            System.out.println("CountThread = " + i);
            calculatePiThread(i);
        }

    }

    private static void calculatePiThread(int countThread) throws InterruptedException {
        double dx = 0.000000001;
        System.out.println(dx);
        p = new CalcPi(dx);
        double x0;
        double x1 = 0;
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= countThread; i++) {
            x0 = x1;
            x1 = (i==countThread)?1.0:(round(1.0 / countThread * i, 1));
            System.out.print("    с " + x0 + " по " + x1);
            threadList.add(runCalcPiThread(x0, x1, dx));
        }
        System.out.println();
        long timeStart = System.currentTimeMillis();
        for (Thread t : threadList) {
            t.join();
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("    Count operation = " + p.countOperation);
        System.out.println("    Pi = " + p.calcPi());
        System.out.println("    Time:" + (timeEnd - timeStart));
    }

    private static Thread runCalcPiThread(double x1, double x2, double dx) {
        Thread t = new Thread(() -> {
            double s = 0;
            for (double x = x1; x < x2; x += dx) {
                s += Math.sqrt(1 - x * x);
                p.iterOperation();
            }
            p.addSquere(s);
        });
        t.start();
        return t;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
