package com.example.finance.view;

import com.example.finance.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 2));

        // Username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        AuthenticationService authService = new AuthenticationService();

        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing password or username");
            return;
        }

        if (authService.login(username, password) != null) {
            JOptionPane.showMessageDialog(this, "Login successful");
            mainFrame.showPanel("TransactionPanel", username);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }
}
