<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="vi"><head>
  <meta charset="utf-8">
  <title>EDIT PRODUCT</title>
  <jsp:include page="/WEB-INF/admin/layout/js_footer.jsp"></jsp:include>
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner" class="bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->


  <!-- Sidebar Start -->
  <jsp:include page="/WEB-INF/admin/layout/sidebar_left.jsp"></jsp:include>
  <!-- Sidebar End -->


  <!-- Content Start -->
  <div class="content">
    <!-- Navbar Start -->
    <jsp:include page="/WEB-INF/admin/layout/headerhtml.jsp"></jsp:include>
    <!-- Navbar End -->


    <!-- Sale & Revenue Start -->
    <div class="container-fluid pt-4 px-4">

    </div>
    <!-- Sale & Revenue End -->


    <!-- Sales Chart Start -->
    <div class="container-fluid pt-4 px-4">

    </div>
    <!-- Sales Chart End -->


    <!-- Recent Sales Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="bg-light text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
          <h4 class="mb-0">Edit Product</h4>
          <a href="">Show All</a>
        </div>
        <div class="table-responsive">
          <table class="table text-start align-middle table-bordered table-hover mb-0">
            <thead>
            <tr class="text-dark" style="text-align: center">
              <th scope="col">ID</th>
              <th scope="col">Name Product</th>
              <th scope="col">Quantity</th>
              <th scope="col">Price</th>
              <th scope="col">Image</th>
              <th scope="col">Description</th>
              <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <tr style="text-align: center">
              <td>01 Jan 2045</td>
              <td>INV-0123</td>
              <td>Jhon Doe</td>
              <td>$123</td>
              <td>
                <img src="">Paid</td>
              <td>paid</td>
              <td>
                <a class="btn btn-sm btn-primary" href="">Update</a>
                <a class="btn btn-sm btn-danger" href="">Delete</a>
              </td>

            </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- Recent Sales End -->


    <!-- Widgets Start -->

    <!-- Widgets End -->


    <!-- Footer Start -->
    <jsp:include page="/WEB-INF/admin/layout/footer_end.jsp"></jsp:include>
    <!-- Footer End -->
  </div>
  <!-- Content End -->


  <!-- Back to Top -->
  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top" style="display: none;"><i class="bi bi-arrow-up"></i></a>
</div>

<!-- JavaScript Libraries -->
<jsp:include page="/WEB-INF/admin/layout/js_end.jsp"></jsp:include>

</body></html>