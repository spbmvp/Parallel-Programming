package ru.spbstu.telematics.popov.lab05;

/**
 * Задание:
 * Автомобильное окно.Электрическое окно в автомобиле управляется двумя кнопками-переключателями: вверх и вниз.
 * Когда нажата кнопка вверх, окно начинает закрываться. Если кнопка вверх нажата меньше, чем Т секунд окно
 * закрывается полностью. Если кнопка вверх нажата больше, чем Т секунд, то когда кнопка будет отпущена,
 * окна остановит закрываться. Кнопка вниз работает точно также. Механическая блокировка препятствует нажатию
 * двух кнопок одновременно.Окно перемещается моторчиком, который отвечает на команды start_close, stop_close,
 * start_open и stop_open. Два датчика, закрыто и открыто, определяют соответственно, когда окно полностью закрыто
 * и когда оно полностью открыто. Окну требуется R единиц времени для перемещения из полностью открытой позиции до
 * полностью закрытой и наоборот.
 */
public class Lab05 {
    public static void main(String[] args) throws InterruptedException {
        Motor m = new Motor(10000, 2000);
        m.startMotor();
        showStatus(m);
/*
 * Проверка открытия окна и остановки открытия. Кнопка открытия нажата больше T мс
 */
        m.setCommand(Motor.Command.START_OPEN);
        Thread.sleep(3000);
        m.setCommand(Motor.Command.STOP_OPEN);
        Thread.sleep(1000);
 /*
 * Проверка автоматического открытия окна. Кнопка открытия нажата меньше T мс
 */
        m.setCommand(Motor.Command.START_OPEN);
        Thread.sleep(1000);
        m.setCommand(Motor.Command.STOP_OPEN);
        Thread.sleep(1000);
/*
 * Проверка закрытия окна и остановки закрытия. Кнопка закрывания нажата больше T мс
 */
        m.setCommand(Motor.Command.START_CLOSE);
        Thread.sleep(3000);
        m.setCommand(Motor.Command.STOP_CLOSE);
        Thread.sleep(1000);
 /*
 * Проверка автоматического закрытия окна. Кнопка закрытия нажата меньше T мс
 */
        m.setCommand(Motor.Command.START_CLOSE);
        Thread.sleep(1000);
        m.setCommand(Motor.Command.STOP_CLOSE);
        Thread.sleep(2000);

        System.exit(0);
    }

    private static Thread showStatus(Motor m) {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(500);
                    m.getStatus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        return t;
    }
}
