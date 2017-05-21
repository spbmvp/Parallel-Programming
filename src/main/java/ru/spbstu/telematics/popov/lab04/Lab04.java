package ru.spbstu.telematics.popov.lab04;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.LinkedList;
import java.util.List;

public class Lab04 {
    static Client client;
    private static ClientWindow mainWindow;
    private static int mcPort = 12345;
    private static String mcIPStr = "230.1.1.1";
    static List<String> onlineNameClients = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        onlineNameClients.add("Online:");
        runReceiver();
        mainWindow = new ClientWindow();
        client = new Client();
        newClient();
    }

    private static void newClient() {
        client.setLastMsg(Client.ServiceMessage.SEND_NAME);
        sendMessage(client);
    }

    static void sendMessage(Client msg) {
        InetAddress mcIPAddress;
        try {
            mcIPAddress = InetAddress.getByName(mcIPStr);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                client.setLastMsg(Client.ServiceMessage.END);
                sendMessage(client);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        t.start();
        return t;
    }

    private static void handlingMessage(Client msg) throws IOException {
        if (msg.isServiceMsg()) {
            switch (msg.getLastServiceMsg()) {
                case NEW_CLIENT:
                    mainWindow.addMessage("Client \"" + msg.getName() + "\" attached of this chat!");
                    break;
                case SEND_NAME:
                    if (client.getName() != null) {
                        client.setLastMsg(Client.ServiceMessage.NAME);
                        sendMessage(client);
                    }
                    break;
                case NAME:
                    if (!onlineNameClients.contains(msg.getName())) {
                        onlineNameClients.add(msg.getName());
                        mainWindow.updateOnlineClients(onlineNameClients);
                    }
                    break;
                case END:
                    if (onlineNameClients.contains(msg.getName())) {
                        onlineNameClients.remove(msg.getName());
                        mainWindow.updateOnlineClients(onlineNameClients);
                        mainWindow.addMessage("Client \"" + msg.getName() + "\" came out of this chat!");
                    }
                    break;
            }
        } else {
            mainWindow.addMessage(msg.getName() + ">> " + msg.getLastMsg());
        }
    }
}
