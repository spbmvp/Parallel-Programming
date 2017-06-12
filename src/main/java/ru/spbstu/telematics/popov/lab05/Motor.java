package ru.spbstu.telematics.popov.lab05;

public class Motor implements Runnable {
    private int R; //время на открытие и закрытите окна в мсек.
    private int st; // осталось времени на открытие окна в мсек.
    private int T; //если меньше этого времени происходит нажатие кнопки, то окно будет полностью открыто/закрыто в мсек.
    private boolean openedWindow;
    private boolean closedWindow;
    private Command cm;

    public Motor(int R, int T) {
        openedWindow = false;
        closedWindow = true;
        this.R = st = R;
        this.T = T;
        cm = Command.STOP_CLOSE;
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

    public void setCommand(Command cm) {
        this.cm = cm;
    }

    @Override
    public void run() {
        long timeClose = 0;
        long timeOpen = 0;
        while (true) {
            switch (cm) {
                case START_CLOSE:
                    if (timeClose == 0) {
                        System.out.println("START_CLOSE");
                        timeClose = System.currentTimeMillis();
                        if (!closedWindow) {
                            openedWindow=false;
                            st++;
                            if (st >= R) {
                                closedWindow = true;
                                st=R;
                            }
                        }
                    }
                    break;
                case STOP_CLOSE:
                    if (timeClose != 0 && (timeClose + T <= System.currentTimeMillis())) {
                        System.out.println("STOP_CLOSE");
                        getStatus();
                    }
                    timeClose = 0;
                    break;
                case START_OPEN:
                    if (timeOpen == 0) {
                        System.out.println("START_OPEN");
                        timeOpen = System.currentTimeMillis();
                        if (!openedWindow) {
                            closedWindow=false;
                            st-=500;
                            if (st <= 0) {
                                openedWindow = true;
                                st=0;
                            }
                        }
                    }
                    break;
                case STOP_OPEN:
                    if (timeOpen != 0 && (timeOpen + T <= System.currentTimeMillis())) {
                        System.out.println("STOP_OPEN");
                        getStatus();
                    }
                    timeOpen = 0;
                    break;
            }
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
