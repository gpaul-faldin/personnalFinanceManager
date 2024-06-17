package com.example.finance.view;

import com.example.finance.model.Transaction;
import com.example.finance.service.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TransactionPanel extends JPanel {
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField categoryField;
    private JList<String> transactionList;
    private DefaultListModel<String> listModel;
    private TransactionService transactionService;
    private String username;

    public TransactionPanel(String username) {
        transactionService = new TransactionService();
        this.username = username;

        setLayout(new BorderLayout());

        // Top panel for adding transactions
        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        topPanel.add(descriptionField);
        topPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        topPanel.add(amountField);
        topPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        topPanel.add(categoryField);
        JButton addButton = new JButton("Add Transaction");
        addButton.addActionListener(e -> addTransaction());
        topPanel.add(addButton);

        // Center panel for displaying transactions
        listModel = new DefaultListModel<>();
        transactionList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(transactionList);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadTransactions();
    }

    private void addTransaction() {
        String description = descriptionField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String category = categoryField.getText();

        Transaction transaction = new Transaction(description, amount, LocalDate.now(), category);
        transactionService.addTransaction(transaction, this.username);

        descriptionField.setText("");
        amountField.setText("");
        categoryField.setText("");

        loadTransactions();
    }

    private void loadTransactions() {
        listModel.clear();
        List<Transaction> transactions = transactionService.getAllTransactions(this.username);
        for (Transaction transaction : transactions) {
            listModel.addElement(transaction.getDescription() + " - " + transaction.getAmount() + " - " + transaction.getCategory());
        }
    }
}
