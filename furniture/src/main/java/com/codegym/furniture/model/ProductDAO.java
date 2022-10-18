package com.codegym.furniture.model;

import com.codegym.furniture.view.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/case_md3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "haibinh";

    private static final String INSERT_PRODUCT = "INSERT INTO `products` (`name`, `quantity`, `price`, `image`, `description`,`idcategory`) " +
            "VALUES (?, ?, ? , ?, ? , ?)";
    private String SELECT_ALL_PRODUCT = "SELECT * FROM products";
    private String SELECT_PRODUCT_BYID = "SELECT id, name, quantity, price, image, description, idcategory FROM products where id = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM products where id = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE products SET name = ?,quantity= ?, price = ?, image = ?, description= ?, idcategory = ?  where id = ?";
    private static final String CHECK_NAME_EXISTS = "SELECT * FROM products where name = ?";

    private int noOfRecords;
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

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
    public void insertProduct(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6,product.getIdcategory());
            System.out.println(getClass() + " insertProduct " + preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BYID)) {
            preparedStatement.setInt(1, id);
            System.out.println(getClass() + " selectProduct " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                product = new Product(id, name, quantity, price, image, description, idcategory);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAllProducts() {
        List<Product> listProduct = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println(getClass() + " selectAllProducts " + preparedStatement);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                Product product = new Product(id, name, quantity, price, image, description, idcategory);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return listProduct;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getIdcategory());
            preparedStatement.setInt(7, product.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }





    @Override
    public boolean checkNameExits(String productName) {
        try {
            Connection connection =getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_NAME_EXISTS);
            preparedStatement.setString(1, productName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (SQLException ex) {
          printSQLException(ex);
        }
        return false;
    }

    @Override
    public List<Product> selectAllProductsPaggingFilter(int offset, int noOfRecords, String q, int idcategory) {
        List<Product> listProduct = new ArrayList<>();
        Product product = null;
        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            if (idcategory != -1) {
                String query = "select SQL_CALC_FOUND_ROWS * from products where name like ? and idcategory = ? limit "
                        + offset + ", " + noOfRecords;
                stmt = connection.prepareStatement(query);
                stmt.setString(1, '%' + q + '%');
                stmt.setInt(2, idcategory);
            } else {
                if (idcategory == -1) {
                    String query = "select SQL_CALC_FOUND_ROWS * from products where name like ? limit "
                            + offset + ", " + noOfRecords;
                    stmt = connection.prepareStatement(query);
                    stmt.setString(1, '%' + q + '%');
                }
            }
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getInt("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setIdcategory(rs.getInt("idcategory"));
                listProduct.add(product);
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listProduct;
    }

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
