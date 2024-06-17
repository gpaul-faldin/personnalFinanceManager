package com.example.finance.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Personal Finance Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        addLoginPanel();
//        addTransactionPanel();

        getContentPane().add(mainPanel);
    }

    private void addLoginPanel() {
        LoginPanel loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "LoginPanel");
    }

    private void addTransactionPanel(String username) {
        TransactionPanel transactionPanel = new TransactionPanel(username);
        mainPanel.add(transactionPanel, "TransactionPanel");
    }

    public void showPanel(String panelName, String username) {
        if (panelName.equals("TransactionPanel")) {
            addTransactionPanel(username);
        }
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
