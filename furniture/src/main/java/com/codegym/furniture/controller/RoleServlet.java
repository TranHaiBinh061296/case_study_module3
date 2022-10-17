package com.codegym.furniture.controller;

import com.codegym.furniture.model.IRoleDAO;
import com.codegym.furniture.model.RoleDAO;
import com.codegym.furniture.view.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/role"}, name = "RoleServlet")
public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IRoleDAO iRoleDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewFrom(req,resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteRole(req, resp);
                    break;
                default:
                    listRole(req, resp);
                    break;
            }
        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> listRole = iRoleDAO.selectAllRole();
        req.setAttribute("listRole", listRole);
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req, resp);
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));

        iRoleDAO.deleteRole(id);

        List<Role> listRole = iRoleDAO.selectAllRole();
        req.setAttribute("listRole", listRole);
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Role existingRole = iRoleDAO.selectRole(id);

        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        req.setAttribute("role", existingRole);

        dispatcher.forward(req, resp);
    }

    private void showNewFrom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
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
                    insertRole(req, resp);
                    break;
                case "edit":
                    updateRole(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("role");
        Role role = new Role(id, name);
        iRoleDAO.updateRole(role);
        resp.sendRedirect("/role");
    }

    private void insertRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("role");
        Role role = new Role();
        role.setRole(name);
        iRoleDAO.inserRole(role);
        RequestDispatcher dispatcher = req.getRequestDispatcher("");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        iRoleDAO = new RoleDAO();
    }
}
