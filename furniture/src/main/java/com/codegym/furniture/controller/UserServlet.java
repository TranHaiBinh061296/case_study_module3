package com.codegym.furniture.controller;

import com.codegym.furniture.model.IRoleDAO;
import com.codegym.furniture.model.IUserDAO;
import com.codegym.furniture.model.RoleDAO;
import com.codegym.furniture.model.UserDAO;
import com.codegym.furniture.view.Role;
import com.codegym.furniture.view.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/users"}, name = "UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserDAO iUserDAO;
    private IRoleDAO iRoleDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                    deleteUser(req, resp);
                    break;
                default:
                    listUser(req, resp);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = iUserDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/list_user.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iUserDAO.deleteUser(id);
        List<User> listUser = iUserDAO.selectAllUsers();
        req.setAttribute("listUser", listUser);
        resp.sendRedirect("/users");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();

        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = iUserDAO.selectUser(id);
        req.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/edit_user.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/create_user.jsp");
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
                    insertUser(req, resp);
                    break;
                case "edit":
                    updateUser(req, resp);
                    break;
                default:
                    listUser(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String phone = req.getHeader("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String image = req.getParameter("image");
        String idrole = String.valueOf(Integer.parseInt(req.getParameter("idrole")));
        User user = new User(username, password, fullname, phone, email, address, image, idrole);
        iUserDAO.insertUser(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/create_user.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        iUserDAO = new UserDAO();
        iRoleDAO = new RoleDAO();
        List<Role> listRole = iRoleDAO.selectAllRole();
        if (this.getServletContext().getAttribute("listRole") == null) {
            this.getServletContext().setAttribute("listRole",listRole);
        }
    }
}


