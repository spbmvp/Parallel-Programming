package ru.spbstu.telematics.popov.lab04;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class Lab04 {
    public static void main(String[] args) throws InterruptedException, IOException {
        Integer id = new Random().nextInt(1000);
        Thread resiver = runResiver(id);
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        DatagramSocket udpSocket = null;
        Message mes = new Message(id, "Hello");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(mes);
        try {
            udpSocket = new DatagramSocket();
            InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
            byte[] msg = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(msg, msg.length);
            packet.setAddress(mcIPAddress);
            packet.setPort(mcPort);
            for (int i = 0; i < 100; i++) {
                udpSocket.send(packet);
                System.out.println("                    Sent a  multicast message: " + mes.getMessege());
                Thread.sleep(5000);
            }
            System.out.println("Exiting application");

            udpSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Thread runResiver(int id) {
        Thread t = new Thread(() -> {
            int mcPort = 12345;
            String mcIPStr = "230.1.1.1";
            MulticastSocket mcSocket = null;
            InetAddress mcIPAddress = null;
            try {
                mcIPAddress = InetAddress.getByName(mcIPStr);
                mcSocket = new MulticastSocket(mcPort);
                System.out.println("Multicast Receiver running at:"
                        + mcSocket.getLocalSocketAddress());
                mcSocket.joinGroup(mcIPAddress);

                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                for (int i = 0; i < 1000000; i++) {
                    mcSocket.receive(packet);
                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Message msg = (Message) ois.readObject();
                    System.out.println("[Multicast  Receiver] Received: " + msg.getMessege() + " от " + msg.getIdClient());
                }
                mcSocket.leaveGroup(mcIPAddress);
                mcSocket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        t.start();
        return t;
    }
}
