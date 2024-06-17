package com.example.finance.service;

import com.example.finance.dao.UserDAO;
import com.example.finance.model.User;

public class AuthenticationService {
    private final UserDAO userDAO = new UserDAO();

    public boolean register(User user) {
        if (userDAO.getUserByUsername(user.getUsername()) == null) {
            return userDAO.createUser(user);
        }
        return false;
    }

    public User login(String username, String password) {
        return userDAO.verifyPassword(username, password);
    }

    public User retrieveUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
