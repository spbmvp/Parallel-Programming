package ru.spbstu.telematics.popov.lab04;

import java.io.Serializable;

public class Message implements Serializable {
    private Integer idClient;
    private String messege;

    Message(Integer id, String mes) {
        this.idClient = id;
        this.messege = mes;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }
}
