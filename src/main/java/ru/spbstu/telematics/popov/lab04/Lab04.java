package ru.spbstu.telematics.popov.lab04;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class Lab04 {
    static ClientWindow mainWindow;
    static Client client;
    static int mcPort = 12345;
    static String mcIPStr = "230.1.1.1";

    public static void main(String[] args) throws InterruptedException, IOException {
        Integer id = new Random().nextInt(1000);
        runReceiver();
        mainWindow = new ClientWindow();
        client = new Client(id, "new client");
        client.setName("blala"+id);
        newClient();
        mainWindow.ok.addActionListener(e -> {
            if (!mainWindow.clientMessage.getText().isEmpty()) {
                client.setLastMsg(mainWindow.clientMessage.getText());
                try {
                    sendMessage(client);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private static void newClient() throws IOException {
        client.setLastMsg("new client");
        client.setServiseMsg(true);
        sendMessage(client);
    }

    public static void sendMessage(Client msg) throws IOException {
        InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
        DatagramSocket udpSocket = new DatagramSocket();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        byte[] msgByteArray = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(msgByteArray, msgByteArray.length);
        packet.setAddress(mcIPAddress);
        packet.setPort(mcPort);
        udpSocket.send(packet);
        udpSocket.close();
    }

    private static Thread runReceiver() {
        Thread t = new Thread(() -> {
            MulticastSocket mcSocket;
            InetAddress mcIPAddress;
            try {
                mcIPAddress = InetAddress.getByName(mcIPStr);
                mcSocket = new MulticastSocket(mcPort);
                mcSocket.joinGroup(mcIPAddress);
                DatagramPacket packet = new DatagramPacket(new byte[8096], 8096);
                while (!Thread.currentThread().isInterrupted()) {
                    mcSocket.receive(packet);
                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Client msg = (Client) ois.readObject();
                    handlingMessage(msg);
                }
                mcSocket.leaveGroup(mcIPAddress);
                mcSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        t.start();
        return t;
    }

    private static void handlingMessage(Client msg) throws IOException {
        if(msg.isServiseMsg()) {
            switch (msg.getLastMsg()) {
                case "new client":
                    if(client.getName() != null) {
                        client.setLastMsg("name");
                        client.setServiseMsg(true);
                        sendMessage(client);
                    }
                    break;
                case "name" :
                    mainWindow.clientsOnline.append(msg.getName()+"\n");
                    break;
            }
        } else {
            mainWindow.messagesField.append(msg.getIdClient() + ">> " + msg.getLastMsg() + "\n");
        }
    }
}
