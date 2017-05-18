package ru.spbstu.telematics.popov.lab04;

import java.io.Serializable;

public class Client implements Serializable {
    private Integer idClient;
    private String lastMsg;
    private boolean isServiseMsg;
    private String name;

    Client(Integer id, String mes) {
        this.idClient = id;
        this.lastMsg = mes;
    }

    public boolean isServiseMsg() {
        return isServiseMsg;
    }

    public void setServiseMsg(boolean serviseMsg) {
        isServiseMsg = serviseMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.isServiseMsg = false;
        this.lastMsg = lastMsg;
    }
}
