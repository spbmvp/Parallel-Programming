package ru.spbstu.telematics.popov.lab04;

import java.io.Serializable;

class Client implements Serializable {
    private String lastMsg;
    private ServiceMessage lastServiceMsg;
    private boolean isServiceMsg;
    private String name;
    Client() {
    }

    ServiceMessage getLastServiceMsg() {
        return lastServiceMsg;
    }

    boolean isServiceMsg() {
        return isServiceMsg;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLastMsg() {
        return lastMsg;
    }

    void setLastMsg(ServiceMessage serviceMessage) {
        this.isServiceMsg = true;
        this.lastServiceMsg = serviceMessage;
    }

    void setLastMsg(String lastMsg) {
        this.isServiceMsg = false;
        this.lastMsg = lastMsg;
    }

    public enum ServiceMessage {
        NEW_CLIENT("new client"),
        END("end"),
        SEND_NAME("send name"),
        NAME("name")
        ;

        private String msg;

        ServiceMessage(String s) {
            msg=s;
        }

        @Override
        public String toString() {
            return msg;
        }
    }
}
