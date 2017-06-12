package ru.spbstu.telematics.popov.lab05;

public class Motor {
    private int R; //время на открытие и закрытите окна в мксек.
    private int st; // осталось времени на открытие окна в мксек.
    private int T; //если меньше этого времени происходит нажатие кнопки, то окно будет полностью открыто/закрыто в мксек.
    private boolean openedWindow;
    private boolean closedWindow;

    public Motor(int R, int T) {
        openedWindow = false;
        closedWindow = true;
        this.R = st = R;
        this.T = T;
    }

    public void getStatus() {
        if (openedWindow) {
            System.out.println("Window opened");
        } else if (closedWindow) {
            System.out.println("Window closed");
        } else {
            System.out.println("Window is close " + (st * 100 / R) + "%");
        }
    }

    public enum Command {
        START_CLOSE("start_close"),
        STOP_CLOSE("stop_close"),
        START_OPEN("start_open"),
        STOP_OPEN("stop_open");

        private String command;

        Command(String c) {
            command = c;
        }

        @Override
        public String toString() {
            return command;
        }
    }
}
