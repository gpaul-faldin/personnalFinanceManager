package com.example.finance;

import com.example.finance.service.AuthenticationService;
import com.example.finance.service.TransactionService;
import com.example.finance.model.Transaction;
import com.example.finance.model.User;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthenticationService authenticationService = new AuthenticationService();
        TransactionService transactionService = new TransactionService();
//        User newUser = new User("Polo", "caca");

        User newUser = authenticationService.login("Polo", "caca");

        LocalDate today = LocalDate.now();

        Transaction transaction = new Transaction("Fraise", 4.99, today, "magasin");
//        transactionService.addTransaction(transaction, "Polo");
        List<Transaction> transactionList = transactionService.getAllTransactions("Polo");

        for (Transaction transaction1 : transactionList) {
            System.out.println(transaction1.getDescription());
        }

    }
}
