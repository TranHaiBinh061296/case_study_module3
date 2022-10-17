package com.codegym.furniture.controller;

import com.codegym.furniture.model.*;
import com.codegym.furniture.view.Category;
import com.codegym.furniture.view.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/product"}, name = "ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IProductDAO iProductDAO;
   private ICategoryDAO iCategoryDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html/charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    showDeleteProduct(req, resp);
                    break;
                default:
                    listProduct(req,resp);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listNumberPage(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iProductDAO.deleteProduct(id);
        List<Product> listProduct = iProductDAO.selectAllProducts();
        req.setAttribute("listProduct", listProduct);
        resp.sendRedirect("/product");
    }


    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        req.setAttribute("product",product);
        int id = Integer.parseInt(req.getParameter("id"));
        Product existingProduct = iProductDAO.selectProduct(id);


        req.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/edit_product.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        req.setAttribute("product",product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list_product.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(req, resp);
                    break;
                case "edit":
                    updateProduct(req, resp);
                    break;
                default:
                    listProduct(req, resp);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listProduct = iProductDAO.selectAllProducts();
        req.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list_product.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String name = req.getParameter("name");
        int quantity = Integer.valueOf(req.getParameter("quantity"));
        int price = Integer.parseInt(req.getParameter("price"));
        String image = req.getParameter("image");
        String description = req.getParameter("description");
        int idcategory = Integer.parseInt(req.getParameter("idcategory"));
        Product newProduct = new Product(name, quantity,price,image,description,idcategory);
        iProductDAO.insertProduct(newProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/create_product.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        iProductDAO = new ProductDAO();
        iCategoryDAO = new CategoryDAO();
        List<Category> listCategory = iCategoryDAO.selectAllCategory();
        if (this.getServletContext().getAttribute("listCategory") == null) {
            this.getServletContext().setAttribute("listCategory",listCategory);
        }
    }
}
