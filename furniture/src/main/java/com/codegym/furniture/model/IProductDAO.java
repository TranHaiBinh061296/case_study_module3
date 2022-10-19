package com.codegym.furniture.model;

import com.codegym.furniture.view.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    public int getNoOfRecords();
    public void insertProduct(Product product) throws SQLException;

    public Product selectProduct(int id);

    public List<Product> selectAllProducts();
    public boolean deleteProduct(int id) throws SQLException;
    public void updateProduct(Product product) throws SQLException;
    public  boolean checkNameExits(String productName);
    public List<Product> selectAllProductsPaggingFilter(int offset, int noOfRecords, String q, int idcategory);

}
