package com.codegym.furniture.controller;

import com.codegym.furniture.model.CategoryDAO;
import com.codegym.furniture.model.ICategoryDAO;
import com.codegym.furniture.view.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/category"} , name = "CategoryServlet")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICategoryDAO iCategoryDAO;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                    deleteCategory(req, resp);
                    break;
                default:
                    listCategory(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> listCategory = iCategoryDAO.selectAllCategory();
        req.setAttribute("listCategory", listCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/category/list_category.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        iCategoryDAO.deleteCategory(id);
        List<Category> listCategory = iCategoryDAO.selectAllCategory();
        req.setAttribute("listCategory", listCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/category/list_category.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category existingCategory = iCategoryDAO.selectCategory(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/category/edit_category.jsp");
        req.setAttribute("category", existingCategory);
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/category/create_category.jsp");
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
                    insertCategory(req, resp);
                    break;
                case "edit":
                    updateCategory(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Category category = new Category(id, name);
        iCategoryDAO.updateCategory(category);
        resp.sendRedirect("/category");
    }

    private void insertCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        Category category = new Category();
        category.setName(name);
        iCategoryDAO.insertCategory(category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/category/create_category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        iCategoryDAO = new CategoryDAO();
    }
}
