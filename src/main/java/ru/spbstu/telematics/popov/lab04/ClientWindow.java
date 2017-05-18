package ru.spbstu.telematics.popov.lab04;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {

    public JTextArea messagesField;
    public JTextArea clientsOnline;
    public JTextField clientMessage;
    public JLabel clientName;
    public JButton ok;

    ClientWindow() {
        setTitle("Chat");
        setBounds(0, 0, 400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messagesField = new JTextArea(20, 20);
        clientsOnline = new JTextArea(10,10);
        messagesField.setEditable(false);
        messagesField.setForeground(Color.BLACK);
        messagesField.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollBar = new JScrollPane(messagesField);
        add(scrollBar, BorderLayout.CENTER);
        add(clientsOnline, BorderLayout.EAST);

        clientName = new JLabel("Enter your name >>");
        clientMessage = new JTextField();
        clientMessage.setForeground(Color.BLACK);
        ok = new JButton("Send");
        JPanel clientMessagesPanel = new JPanel(new BorderLayout());
        clientMessagesPanel.add(clientName, BorderLayout.WEST);
        clientMessagesPanel.add(clientMessage, BorderLayout.CENTER);
        clientMessagesPanel.add(ok, BorderLayout.EAST);
        add(clientMessagesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
