package com.example.finance.service;

import com.example.finance.dao.TransactionDAO;
import com.example.finance.model.Transaction;
import com.example.finance.model.User;

import java.util.List;

public class TransactionService {

    private final TransactionDAO transactionDAO = new TransactionDAO();

    public boolean addTransaction(Transaction transaction, String username) {
        return transactionDAO.createTransaction(transaction, username);
    }

    public Transaction getTransaction(int id, String username) {
        return transactionDAO.getTransaction(id, username);
    }

    public List<Transaction> getAllTransactions(String username) {
        return transactionDAO.getAllTransactionsOfUser(username);
    }

    public boolean updateTransaction(Transaction transaction) {
        return transactionDAO.updateTransaction(transaction);
    }

    public boolean deleteTransaction(int id) {
        return transactionDAO.deleteTransaction(id);
    }

}
