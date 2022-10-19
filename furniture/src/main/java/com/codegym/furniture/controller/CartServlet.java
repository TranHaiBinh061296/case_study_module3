package com.codegym.furniture.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CartServlet", urlPatterns = "/view")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "checkout":
                showCartPage(req, resp);
                break;
            default:
                showCartCheckoutPage(req, resp);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "order":
                showCartPage(req, resp);
                break;
            default:
        }
    }

//    private void processOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Product product = new Gson();
//        OrderDTO orderDTO = gson.fromJson(req.getReader(), OrderDTO.class);
//        System.out.println(orderDTO);
//
//        orderDTO.setName("Quang CODEGYM");
//        String objRespone = gson.toJson(orderDTO);
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println(objRespone);
//    }

    private void showCartCheckoutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/frontend/view.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/frontend/cart.jsp");
        requestDispatcher.forward(req, resp);
    }
}
