package com.codegym.furniture.model;

import com.codegym.furniture.view.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/case_md3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "haibinh";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" +
            "(username,password,fullname,phone,email,address,image,idrole) VALUES " +
            " (?, ?, ?,? , ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select username,password,fullname,phone,email,address,image,idrole " +
            " from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String SELECT_USER_BY_USERNAME = "select u.id,u.username,u.password,u.fullname, u.phone,u.email,u.address,u.image,u.idrole  "
            + "    		 from users as u inner join roles as r "
            + "    		where u.username = ? and u.idrole = r.id;";
    public static String USER_EXIST_BY_USER = "" +
            "SELECT COUNT(*) AS COUNT " +
            "FROM users AS u " +
            "WHERE u.username = ?;";
    private static final String UPDATE_USERS_SQL = "update users set" +
            " username=?" +
            ",password=?" +
            ",fullname=?" +
            ",phone=?" +
            ",email=?" +
            ",address=?" +
            ",image=?" +
            ",idole=?" +
            " where id = ?;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getImage());
            preparedStatement.setInt(8, user.getIdrole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(getClass() + " selectUser " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int idrole = Integer.parseInt(rs.getString("idrole"));
                user = new User(id, username, password, fullname, phone, email, address, image, idrole);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullName = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int idrole = rs.getInt("idrole");
                User user = new User(id, username, password, fullName, phone, email, address, image, idrole);
                listUser.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listUser;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullname());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getImage());
            statement.setInt(8, user.getIdrole());
            statement.setInt(9, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean existsByUser(String userName) {
        boolean exist = false;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareCall(USER_EXIST_BY_USER);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return exist;
    }

    @Override
    public User getLogin(String username) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String image = rs.getString("image");
                int idrole = rs.getInt("idrole");
                user = new User(id, username, password, fullname, phone, address, email, image, idrole);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

//    @Override
//    public boolean checkUserNamePassword(String username, String password) {
//        try {
//            Connection connection = getConnection();
//            String query = "select * from users where username = ? and password = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String fullname = rs.getString("fullname");
//                String phone = rs.getString("phone");
//                String email = rs.getString("email");
//                String address = rs.getString("address");
//                String image = rs.getString("image");
//                int idrole = rs.getInt("idrole");
//
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return true;
//    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
