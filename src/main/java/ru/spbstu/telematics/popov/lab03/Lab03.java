package ru.spbstu.telematics.popov.lab03;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Lab03 {
    private static CalcPi p;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 3) {
            for (int i = Integer.valueOf(args[0]); i <= Integer.valueOf(args[1]); i++) {
                System.out.println("CountThread = " + i);
                calculatePiThread(i, Double.valueOf(args[2]));
            }
        }
    }

    private static void calculatePiThread(int countThread, double eps) throws InterruptedException {
        BigDecimal dx = BigDecimal.valueOf(eps);
        System.out.println("    Eps = " + dx);
        p = new CalcPi(dx);
        BigDecimal x0;
        BigDecimal x1 = BigDecimal.ZERO;
        List<Thread> threadList = new ArrayList<>();
        System.out.print("    Разбиваем отрезок на: ");
        for (int i = 1; i <= countThread; i++) {
            x0 = x1;
            x1 = (i == countThread) ? BigDecimal.ONE : BigDecimal.valueOf(round(1.0 / countThread * i));
            System.out.print(" с " + x0 + " по " + x1);
        }
        System.out.println();
        x1 = BigDecimal.ZERO;
        for (int i = 1; i <= countThread; i++) {
            x0 = x1;
            x1 = (i == countThread) ? BigDecimal.ONE : BigDecimal.valueOf(round(1.0 / countThread * i));
            threadList.add(runCalcPiThread(x0, x1, dx));
        }
        long timeStart = System.currentTimeMillis();
        for (Thread t : threadList) {
            t.join();
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("    Count operation = " + p.countOperation);
        System.out.println("    Pi = " + p.calcPi());
        System.out.println("    Time:" + (timeEnd - timeStart) + " ms");
    }

    private static Thread runCalcPiThread(BigDecimal x1, BigDecimal x2, BigDecimal dx) {
        Thread t = new Thread(() -> {
            BigDecimal s = BigDecimal.ZERO;
            for (BigDecimal x = x1; x.max(x2).equals(x2); x = x.add(dx)) {
                s = s.add(BigDecimal.valueOf(Math.sqrt(1 - x.multiply(x).doubleValue())));
                p.iterOperation();
                //System.out.println(Thread.currentThread().getName() + ": x = [" + x + "]");
            }
            p.addSquere(s);
        });
        t.start();
        return t;
    }

    private static double round(double value) {
        long factor = (long) Math.pow(10, 3);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
