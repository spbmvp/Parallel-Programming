package ru.spbstu.telematics.popov.lab05;

public class Motor {

    public enum Command {
        START_CLOSE("start_close"),
        STOP_CLOSE("stop_close"),
        START_OPEN("start_open"),
        STOP_OPEN("stop_open")
        ;

        private String command;

        Command(String c) {
            command=c;
        }

        @Override
        public String toString() {
            return command;
        }
    }
}
