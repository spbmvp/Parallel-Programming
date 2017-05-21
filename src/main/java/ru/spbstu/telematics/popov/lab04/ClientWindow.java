package ru.spbstu.telematics.popov.lab04;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

class ClientWindow extends JFrame {

    private JTextField clientMessage;
    private JLabel clientName;
    private JTextArea clientsOnline;
    private JTextArea messagesField;

    ClientWindow() {
        setTitle("Chat");
        setBounds(0, 0, 400, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messagesField = new JTextArea(20, 20);
        clientsOnline = new JTextArea(10, 10);
        messagesField.setEditable(false);
        clientsOnline.setEditable(false);
        messagesField.setForeground(Color.BLACK);
        messagesField.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollBar = new JScrollPane(messagesField);
        JScrollPane scrollBar2 = new JScrollPane(clientsOnline);
        add(scrollBar, BorderLayout.CENTER);
        add(scrollBar2, BorderLayout.WEST);
        DefaultCaret caret = (DefaultCaret) messagesField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        clientName = new JLabel("Enter your name >> ");
        clientMessage = new JTextField();
        clientMessage.setForeground(Color.BLACK);
        JButton ok = new JButton("Send");
        JPanel clientMessagesPanel = new JPanel(new BorderLayout());
        clientMessagesPanel.add(clientName, BorderLayout.WEST);
        clientMessagesPanel.add(clientMessage, BorderLayout.CENTER);
        clientMessagesPanel.add(ok, BorderLayout.EAST);
        add(clientMessagesPanel, BorderLayout.SOUTH);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Lab04.client.setLastMsg(Client.ServiceMessage.END);
                Lab04.sendMessage(Lab04.client);
            }
        });
        ok.addActionListener(getActionListener());
        clientMessage.addActionListener(getActionListener());
    }

    private ActionListener getActionListener() {
        return e -> {
            if (!clientMessage.getText().isEmpty()) {
                if (Lab04.client.getName() == null) {
                    if (Lab04.onlineNameClients.contains(clientMessage.getText())) {
                        addMessage("This name is already used!");
                    } else {
                        Lab04.client.setName(clientMessage.getText());
                        Lab04.client.setLastMsg(Client.ServiceMessage.NAME);
                        Lab04.sendMessage(Lab04.client);
                        clientMessage.setText(null);
                        clientName.setText(Lab04.client.getName() + ">> ");
                        Lab04.client.setLastMsg(Client.ServiceMessage.NEW_CLIENT);
                        Lab04.sendMessage(Lab04.client);
                    }
                } else {
                    Lab04.client.setLastMsg(clientMessage.getText());
                    Lab04.sendMessage(Lab04.client);
                    clientMessage.setText(null);
                }
            }
        };
    }

    void addMessage(String msg) {
        messagesField.append(msg + "\n");
    }

    void updateOnlineClients(List<String> onlineNameClients) {
        clientsOnline.setText("");
        for (String onlineClient : onlineNameClients) {
            clientsOnline.append(onlineClient + "\n");
        }
    }
}
