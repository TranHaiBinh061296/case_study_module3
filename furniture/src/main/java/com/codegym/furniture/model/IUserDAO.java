package com.codegym.furniture.model;

import com.codegym.furniture.view.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;
    public User selectUser(int id);
    public List<User> selectAllUsers();
    public boolean updateUser(User user) throws SQLException;
    boolean deleteUser (int id) throws SQLException;
    boolean existsByUser(String userName);
    public User getLogin(String username ) throws SQLException;
}

