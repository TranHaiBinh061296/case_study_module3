
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
  <meta charset="utf-8">
  <title>List Product</title>
  <jsp:include page="/WEB-INF/admin/layout/js_footer.jsp"></jsp:include>
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
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


    <!-- Table Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="row g-4">
        <div class="col-sm-12 col-xl-6">

        </div>
        <div class="col-sm-12 col-xl-6">

        </div>
        <div class="col-sm-12 col-xl-6">

        </div>
        <div class="col-sm-12 col-xl-6">

        </div>
        <div class="col-sm-12 col-xl-6">

        </div>
        <div class="col-sm-12 col-xl-6">
          <a href="/product?action=create"
             class="fa bg-success"> ADD PRODUCT </a>
        </div>
        <div class="col-12">
          <div class="bg-light rounded h-100 p-4">
            <h4 class="mb-4">List Product</h4>
            <div class="table-responsive">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">ID</th>
                  <th scope="col">Name Product</th>
                  <th scope="col">Quantity</th>
                  <th scope="col">Price</th>
                  <th scope="col">Image</th>
                  <th scope="col">Description</th>
                  <th scope="col">Category</th>
                  <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${requestScope.listProduct}">
                  <tr>
                    <td><c:out value="${product.getId()}"/></td>
                    <td><c:out value="${product.getName()}"/></td>
                    <td><c:out value="${product.getQuantity()}"/></td>
                    <td><fmt:formatNumber value="${product.getPrice()}" type="currency" pattern="#,### ₫"/></td>
                    <td><img src="${product.getImage()}"  style="width: 90px; height: 90px"></td>
                    <td>
                      <c:out value="${product.getDescription()}"></c:out>
                    </td>
                    <td>
                      <c:forEach items="${applicationScope.listCategory}" var="category">
                        <c:if test="${category.getId()==product.getIdcategory()}">
                          ${category.getName()}
                        </c:if>
                      </c:forEach>
                    </td>
                    <td>
                      <a href="/product?action=edit&id=${product.getId()}"
                         class="fas fa-edit"> Edit </a>
                      <a onclick="showMessage(${product.getId()})"
                         class="fas fa-trash-alt fa-warning">Delete</a>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Table End -->


    <!-- Footer Start -->
    <jsp:include page="/WEB-INF/admin/layout/footer_end.jsp"></jsp:include>
    <!-- Footer End -->
  </div>
  <!-- Content End -->


  <!-- Back to Top -->
  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>
<script>
  function showMessage(id) {
    if (confirm("Bạn có muốn xóa sản phẩm này không?") === true){
      window.location.href ="/product?action=delete&id=" +id;
    }
  }
</script>

<!-- JavaScript Libraries -->
<jsp:include page="/WEB-INF/admin/layout/js_end.jsp"></jsp:include>
</body>

</html>