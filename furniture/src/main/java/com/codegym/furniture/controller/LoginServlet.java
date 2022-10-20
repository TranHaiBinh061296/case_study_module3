package com.codegym.furniture.controller;

import com.codegym.furniture.model.IUserDAO;
import com.codegym.furniture.model.UserDAO;
import com.codegym.furniture.view.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet( value = "/login" , name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserDAO iUserDAO;

    public LoginServlet() {
        iUserDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/login.jsp");
        String option = req.getParameter("option");
        if (option == null) {
            option = "";
        }
        switch (option) {
            case "user":
                showLoginForm(req,resp);
                break;
            case "logout":
                logout(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        if (option == null) {
            option = "";
        }
        switch (option) {
            case "user":
                loginUser(req,resp);
                break;
            default:
                break;
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        response.sendRedirect("/login?option=user");
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher;
        User user = iUserDAO.checkUserNamePassword(username, password);
        if (user == null) {
            request.setAttribute("message", "Wrong user name or password !!!");
            requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Cookie usernameAcc = new Cookie("username", username);
            Cookie passwordAcc = new Cookie("password", password);
            usernameAcc.setMaxAge(10*60);
            passwordAcc.setMaxAge(10*60);
            response.addCookie(passwordAcc);
            response.addCookie(usernameAcc);
            HttpSession session = request.getSession();
            session.setAttribute("account", user);
            response.sendRedirect("/product");
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        if (iUserDAO.checkUserNamePassword(username, password)) {
//            Cookie cookieUserName = new Cookie("username", username);
//            Cookie cookiePassword = new Cookie("password", password);
//            cookieUserName.setMaxAge(10*60);
//            cookiePassword.setMaxAge(10*60);
//            resp.addCookie(cookieUserName);
//            resp.addCookie(cookiePassword);
//
////            req.getSession().setAttribute("username", username);
////            req.getSession().setAttribute("password", password);
//            resp.sendRedirect("/product");
//        }else{
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/login.jsp");
//            req.setAttribute("message", "Account does not exist. Please log in again");
//            requestDispatcher.forward(req, resp);
//        }
//    }
//@Override
//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    req.setCharacterEncoding("UTF-8");
//    resp.setCharacterEncoding("UTF-8");
//    String username = req.getParameter("username");
//    String password =  req.getParameter("password");
//    try {
//        User user = iUserDAO.getLogin(username);
//        if(user != null){
//            if(user.getPassword().equals(password)){
//                User userLogin  = user;
//                HttpSession httpSession = req.getSession(true);
//                httpSession.setAttribute("userLogin", userLogin);
//                req.setAttribute("userLogin", userLogin);
//                resp.sendRedirect("/product");
//            } else {
//                req.setAttribute("message", "Login unsuccessful. Please log in again");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
//                dispatcher.forward(req, resp);
//            }
//        }else{
//            req.setAttribute("message", "Account does not exist. Please log in again");
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
//            dispatcher.forward(req, resp);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}

//    @Override
//    public void init() throws ServletException {
//        iUserDAO = new UserDAO();
//    }
}
