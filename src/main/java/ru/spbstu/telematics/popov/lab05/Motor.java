package ru.spbstu.telematics.popov.lab05;

public class Motor {
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

    public Thread startMotor() {
        Thread t = new Thread(() -> {
            run();
        });
        t.start();
        return t;
    }

    private void run() {
        long timeStartClose = 0;
        long timeStopClose = 0;
        long timeStartOpen = 0;
        long timeStopOpen = 0;
        while (!Thread.interrupted()) {
            switch (cm) {
                case START_CLOSE:
                    timeStopClose = 0;
                    if (timeStartClose == 0) {
                        System.out.println("START_CLOSE");
                        timeStartClose = System.currentTimeMillis();
                    }
                    if (!closedWindow) {
                        openedWindow = false;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        st++;
                        if (st >= R) {
                            closedWindow = true;
                            st = R;
                        }
                    }
                    break;
                case STOP_CLOSE:
                    if (timeStopClose == 0) {
                        System.out.println("STOP_CLOSE");
                        timeStopClose = System.currentTimeMillis();
                    }
                    if (timeStopClose >= timeStartClose && (timeStopClose - timeStartClose <= T)) {
                        timeStartClose = 0;
                        timeStopClose = T;
                        if (!closedWindow) {
                            openedWindow = false;
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            st++;
                            if (st >= R) {
                                closedWindow = true;
                                st = R;
                                timeStartClose = 0;
                            }
                        }
                    } else {
                        timeStartClose = 0;
                    }
                    break;
                case START_OPEN:
                    timeStopOpen = 0;
                    if (timeStartOpen == 0) {
                        System.out.println("START_OPEN");
                        timeStartOpen = System.currentTimeMillis();
                    }
                    if (!openedWindow) {
                        closedWindow = false;
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        st--;
                        if (st <= 0) {
                            openedWindow = true;
                            st = 0;
                        }
                    }
                    break;
                case STOP_OPEN:
                    if (timeStopOpen == 0) {
                        System.out.println("STOP_OPEN");
                        timeStopOpen = System.currentTimeMillis();
                    }
                    if (timeStopOpen >= timeStartOpen && (timeStopOpen - timeStartOpen <= T)) {
                        timeStartOpen = 0;
                        timeStopOpen = T;
                        if (!openedWindow) {
                            closedWindow = false;
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            st--;
                            if (st <= 0) {
                                openedWindow = true;
                                st = 0;
                                timeStartOpen = 0;
                            }
                        }
                    } else {
                        timeStartOpen = 0;
                    }
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
