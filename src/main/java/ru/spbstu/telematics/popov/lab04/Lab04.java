package ru.spbstu.telematics.popov.lab04;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class Lab04 {
    public static void main(String[] args) throws InterruptedException, IOException {
        Integer id = new Random().nextInt(1000);
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        runReceiver(mcIPStr, mcPort);
        Message msg = new Message(id, "Hello");
        try {
            for (int i = 0; i < 100; i++) {
                msg.setMessege(" hello " + i);
                sendMessage(msg, mcPort, mcIPStr);
                System.out.println("                    Sent a  multicast message: " + msg.getMessege());
                Thread.sleep(1000);
            }
            System.out.println("Exiting application");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(Message msg, int mcPort, String mcIPStr) throws IOException {
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

    private static Thread runReceiver(String mcIPStr, int mcPort) {
        Thread t = new Thread(() -> {
            MulticastSocket mcSocket = null;
            InetAddress mcIPAddress = null;
            try {
                mcIPAddress = InetAddress.getByName(mcIPStr);
                mcSocket = new MulticastSocket(mcPort);
                mcSocket.joinGroup(mcIPAddress);
                DatagramPacket packet = new DatagramPacket(new byte[8096], 8096);
                while (!Thread.currentThread().isInterrupted()) {
                    mcSocket.receive(packet);
                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Message msg = (Message) ois.readObject();
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

    private static void handlingMessage(Message msg) {
        System.out.println("[Multicast  Receiver] Received: " + msg.getMessege() + " от " + msg.getIdClient());
    }
}
