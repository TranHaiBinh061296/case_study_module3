package com.codegym.furniture.controller;

import com.codegym.furniture.model.*;
import com.codegym.furniture.utils.ValidateUtils;
import com.codegym.furniture.view.Category;
import com.codegym.furniture.view.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        HttpSession session = req.getSession();
        if (session.getAttribute("account") == null) {
            resp.sendRedirect("/login?option=user");
            return;
        }
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
                case "view":
                    showList(req,resp);
                    break;
                case "edits":
                    listProductPage(req,resp);
                    break;
                default:
                    listProductNoAction(req, resp);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void listProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4;
        String q = "";
        int idcategory = -1;
        if (req.getParameter("q") != null) {
            q = req.getParameter("q");
        }
        if (req.getParameter("idcategory") != null) {
            idcategory = Integer.parseInt(req.getParameter("idcategory"));
        }
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        List<Product> listProduct = iProductDAO.selectAllProductsPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, idcategory);
        int noOfRecords = iProductDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        System.out.println(getClass() + " listProductPage " + listProduct);
        req.setAttribute("listProduct", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("q", q);
        req.setAttribute("idcategory", idcategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list_product.jsp");
        dispatcher.forward(req, resp);
    }

    private void listProductNoAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4;
        String q = "";
        int idcategory = -1;
        if (req.getParameter("q") != null) {
            q = req.getParameter("q");
        }
        if (req.getParameter("idcategory") != null) {
            idcategory = Integer.parseInt(req.getParameter("idcategory"));
        }
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        List<Product> listProduct = iProductDAO.selectAllProductsPaggingFilter((page - 1) * recordsPerPage, recordsPerPage, q, idcategory);
        int noOfRecords = iProductDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        System.out.println(getClass() + " listProductPage " + listProduct);
        req.setAttribute("listProduct", listProduct);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("q", q);
        req.setAttribute("idcategory", idcategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list.jsp");
        dispatcher.forward(req, resp);
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
        req.setAttribute("product", product);
        int id = Integer.parseInt(req.getParameter("id"));
        Product existingProduct = iProductDAO.selectProduct(id);


        req.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/edit_product.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/create_product.jsp");
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
                    editProduct(req, resp);
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

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<String> errors = new ArrayList<>();
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/edit_product.jsp");
//        Product product = new Product();
//        try {
//            int id = Integer.parseInt(req.getParameter("id").trim());
//            String name = req.getParameter("name").trim();
//            int quantity = Integer.parseInt(req.getParameter("quantity").trim());
//            int price = Integer.parseInt(req.getParameter("price").trim());
//            String image = req.getParameter("image").trim();
//            String description = req.getParameter("description").trim();
//            int idcategory = Integer.parseInt(req.getParameter("idcategory"));
//
//            product = iProductDAO.selectProduct(id);
//            boolean checkName = false;
//            if (product.getName().equals(name)) {
//                checkName = true;
//            }
//            product.setName(name);
//            product.setQuantity(quantity);
//            product.setPrice(price);
//            product.setImage(image);
//            product.setDescription(description);
//            product.setIdcategory(idcategory);
//            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//            Validator validator = validatorFactory.getValidator();
//            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
//            if (!constraintViolations.isEmpty()) {
//                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
//                    errors.add(constraintViolation.getMessage());
//                }
//                req.setAttribute("product", product);
//                req.setAttribute("errors", errors);
//            } else {
//                Category category = iCategoryDAO.selectCategory(idcategory);
//                boolean isNameValid = (checkName == true || !iProductDAO.checkNameExits(product.getName()));
//                if (isNameValid) {
//                    if (category == null) {
//                        errors.add("Invalid category");
//                        req.setAttribute("errors", errors);
//                    } else {
//                        req.setAttribute("message", "Update success!!.....");
//                        iProductDAO.updateProduct(product);
//                        List<Product> listProduct = iProductDAO.selectAllProducts();
//                        req.setAttribute("listProduct", listProduct);
//
//                        dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list_product.jsp");
//                    }
//                } else {
//                    if (category == null) {
//                        errors.add("Category invalid");
//                        req.setAttribute("errors", errors);
//                    }
//                    req.setAttribute("product", product);
//                    errors.add("Product name cannot be empty !!!");
//                    req.setAttribute("errors", errors);
//                }
//
//            }
//            dispatcher.forward(req, resp);
//        } catch (NumberFormatException | SQLException numberFormatException) {
//            //
//            errors.add("Định dạng của category không hợp lệ");
//            req.setAttribute("errors", errors);
//            req.setAttribute("product", product);
//            dispatcher.forward(req, resp);
//        }
        String name, description, image;
        int price;
        int quantity;
        List<String> errors = new ArrayList<>();
        int id = Integer.parseInt(req.getParameter("id"));
        Product oldProduct = iProductDAO.selectProduct(id);
        try {
            name = req.getParameter("name");
            if (!oldProduct.getName().equals(name)) {
                if (iProductDAO.checkNameExits(name)) {
                    errors.add(" Product already exists ! ! !");
                }
            }
            if (name.trim().equals("")) errors.add("Product name cannot be empty !!!");
            quantity = Integer.parseInt(req.getParameter("quantity"));
            price = Integer.parseInt((req.getParameter("price")));
            image = req.getParameter("image");
            if (!ValidateUtils.isImageValid(image)) errors.add("Image link is not correct !!!");

            description = req.getParameter("description");
            int idcategory = Integer.parseInt(req.getParameter("idcategory"));
            if (errors.isEmpty()) {
                Product newProduct = new Product(id,name,quantity,price,image,description,idcategory);
                iProductDAO.updateProduct(newProduct);
                updateListProduct();
                req.setAttribute("message", "Edit product successfully!");
                req.setAttribute("product", newProduct);


            }
        }
        catch (NumberFormatException numberFormatException) {
            errors.add("Invalid price or quantity format !!!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (req.getAttribute("product") == null)
                req.setAttribute("product", oldProduct);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/edit_product.jsp");
            req.setAttribute("errors", errors);

            requestDispatcher.forward(req, resp);
        }

    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();

        String name = req.getParameter("name").trim();
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int price = Integer.parseInt(req.getParameter("price"));
        String image = req.getParameter("image");
        String description = req.getParameter("description");
        int idcategory = Integer.parseInt(req.getParameter("idcategory"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/create_product.jsp");

        Product product = new Product();
        try {

            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            if (!ValidateUtils.isPriceValid(String.valueOf(price))) errors.add("Price 1 - 1.000.000.000");
            product.setImage(image);
            if (!ValidateUtils.isImageValid(image)) {
                errors.add("Incorrect image format");
            }
            product.setDescription(description);
            product.setIdcategory(idcategory);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (!constraintViolations.isEmpty()) {
                // Lỗi ràng buộc
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors.add(constraintViolation.getMessage());
                }
                req.setAttribute("product", product);
                req.setAttribute("errors", errors);
//                errors.isEmpty()
            } else {
                if (iProductDAO.checkNameExits(name)) {
                    req.setAttribute("product", product);
                    errors.add("Name Product is exists !!!");
                    req.setAttribute("errors", "Same product name");
                } else {
                    iProductDAO.insertProduct(product);
                    req.setAttribute("message", "Insert success!!.....");
                }
            }
            dispatcher.forward(req, resp);
        } catch (NumberFormatException numberFormatException) {
            errors.add("Invalid product format");
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            dispatcher.forward(req, resp);
        }

    }
    private void updateListProduct() {
        this.getServletContext().removeAttribute("listProduct");
        this.getServletContext().setAttribute("listProduct", iProductDAO.selectAllProducts());
    }

    @Override
    public void init() throws ServletException {
        iProductDAO = new ProductDAO();
        iCategoryDAO = new CategoryDAO();
        List<Category> listCategory = iCategoryDAO.selectAllCategory();
        if (this.getServletContext().getAttribute("listCategory") == null) {
            this.getServletContext().setAttribute("listCategory", listCategory);
        }
    }
}

