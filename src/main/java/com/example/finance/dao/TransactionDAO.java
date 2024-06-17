package com.example.finance.dao;

import com.example.finance.model.Transaction;
import com.example.finance.model.User;
import com.example.finance.service.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private Connection connect() {
        return DatabaseHelper.connect();
    }

    public boolean createTransaction(Transaction transaction, String username) {
        String sql = "insert into transactions(description, amount, date, category, owner) values(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, transaction.getDescription());
            pstmt.setDouble(2, transaction.getAmount());
            pstmt.setString(3, transaction.getDate());
            pstmt.setString(4, transaction.getCategory());
            pstmt.setString(5, username);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Transaction getTransaction(int id, String username) {
        String sql = "select * from transactions where id = ? and owner = ?";
        Transaction transaction = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                String category = rs.getString("category");

                transaction = new Transaction(description, amount, LocalDate.parse(date), category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transaction;
    }

    public List<Transaction> getAllTransactionsOfUser(String username) {
        String sql = "select * from transactions where owner = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                String category = rs.getString("category");
                transactions.add(new Transaction(description, amount, LocalDate.parse(date), category));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }

    public boolean updateTransaction(Transaction transaction) {
        String sql = "update transactions set description = ?, amount = ?, date = ? where id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            if (transaction.getId() == null) {
                return false;
            }
            pstmt.setString(1, transaction.getDescription());
            pstmt.setDouble(2, transaction.getAmount());
            pstmt.setString(3, transaction.getDate());
            pstmt.setInt(4, transaction.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteTransaction(int id) {
        String sql = "delete from transactions where id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
