package com.codegym.furniture.controller;

import com.codegym.furniture.model.IUserDAO;
import com.codegym.furniture.model.UserDAO;
import com.codegym.furniture.view.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( value = "/login" , name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    IUserDAO iUserDAO;

    public LoginServlet() {
        iUserDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        try {
            User user = iUserDAO.getLogin(username);
            if(user != null){
                if(user.getPassword().equals(password)){
                    User userLogin  = user;
                    HttpSession httpSession = req.getSession(true);
                    httpSession.setAttribute("userLogin", userLogin);
                    req.setAttribute("userLogin", userLogin);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list_product.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("message", "Login unsuccessful. Please log in again");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(req, resp);
                }
            }else{
                req.setAttribute("message", "Account does not exist. Please log in again");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
