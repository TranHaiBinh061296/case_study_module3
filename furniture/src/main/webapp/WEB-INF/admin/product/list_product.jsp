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
    <div id="spinner"
         class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <!-- Sidebar Start -->
    <jsp:include page="/WEB-INF/admin/layout/sidebar_left.jsp">
        <jsp:param name="fullname" value="${sessionScope.userLogin.getFullname()}"/>
        <jsp:param name="image" value="${sessionScope.userLogin.getImage()}"/>
    </jsp:include>
    <!-- Sidebar End -->


    <!-- Content Start -->
    <div class="content">
        <!-- Navbar Start -->
        <jsp:include page="/WEB-INF/admin/layout/headerhtml.jsp">
            <jsp:param name="fullname" value="${sessionScope.userLogin.getFullname()}"/>
            <jsp:param name="image" value="${sessionScope.userLogin.getImage()}"/>
        </jsp:include>
        <!-- Navbar End -->


        <!-- Table Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
<%--                <div class="col-sm-10 col-xl-7">--%>
<%--                    <a style="font-size: larger" class="btn btn-outline-success" href="/product?action=create"--%>
<%--                       title="create"><i class="fa-solid fa-square-plus"></i>--%>
<%--                        Create Product</a>--%>
<%--                </div>--%>
                <div class="col-sm-6">
                    <form action="/product" method="get" class="form-horizontal form-group row">
                        <div class="col-5" style="padding-right: 5px">
                            <input class="form-control" type="text" placeholder="search.." name="q"
                                   value="${requestScope.q}">
                        </div>
                        <div class="col-5" style="padding-right: 5px">
                            <select name="idcategory" class="form-control">
                                <option value="-1">All</option>
                                <c:forEach items="${applicationScope.listCategory}" var="category">
                                    <option
                                            <c:if test="${requestScope.idcategory == category.getId()}">selected</c:if>
                                            value="${category.getId()}">${category.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-2" style="padding-left: 0px;">
                            <input style="padding-right: 12px" type="submit" value="Search"
                                   class="form-control bg-primary"/>
                        </div>
                    </form>
                </div>
                <div class="col-12">
                    <div class="bg-light rounded h-100 p-4">
                        <h4 class="mb-4">List Product</h4>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr style="text-align: center">
                                    <th scope="col">ID</th>
                                    <th scope="col">Name Product</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Category</th>
                                    <th scope="col" colspan="2">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="product" items="${requestScope.listProduct}">
                                    <tr>
                                        <td><c:out value="${product.getId()}"/></td>
                                        <td><c:out value="${product.getName()}"/></td>
                                        <td><c:out value="${product.getQuantity()}"/></td>
                                        <td><fmt:formatNumber type="number" maxFractionDigits="3"
                                                              value="${product.getPrice()}"/></td>
                                        <td><img src="${product.getImage()}" style="width: 120px; height: 120px"></td>
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
                                            <a href="/product?action=edit&id=${product.getId()}"><i
                                                    class="btn btn-outline-warning fa-regular fa-pen-to-square"></i>
                                            </a>
                                        </td>
                                        <td><a onclick="showMessage(${product.getId()})"><i
                                                class="btn-outline-danger btn fa-solid fa-trash-can"></i></a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <nav class=col-sm-12 style="display: flex; justify-content: center" aria-label="Page navigation example"
             style="position: relative; left: 500px;">
            <ul class="pagination">
                <c:if test="${requestScope.currentPage != 1}">
                    <li class="pagination">
                        <a class="page-link" href="product?page=${requestScope.currentPage - 1}"
                           style="background-color: #fff3cd"><i class="fa fa-angle-double-left"></i></a>
                    </li>
                </c:if>


                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="page-item"><a class="page-link" href="product?page=${i}">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item ">
                                <a class="page-link"
                                   href="product?page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <li class="pagination">
                        <a class="page-link" href="product?page=${requestScope.currentPage + 1}"
                           style="background-color: #fff3cd"><i class="fa fa-angle-double-right"></i></a>
                    </li>
                </c:if>
            </ul>
        </nav>
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
        if (confirm("Bạn có muốn xóa sản phẩm này không?") === true) {
            window.location.href = "/product?action=delete&id=" + id;
        }
    }
</script>
<div class="fb-customerchat"
     attribution=setup_tool
     page_id="104261141362825"
     theme_color="#0084ff">
</div>
<div id="_zalo" style="position: FIXED; Z-INDEX: 99999; BOTTOM: 15px; left: 21px;">
    <a href="https://zalo.me/0969455420" target="_blank" alt="chat zalo"><img
            src="https://stc.sp.zdn.vn/chatwidget/images/stick_zalo.png" alt="zalo" title="zalo"></a>
</div>
<!-- JavaScript Libraries -->
<jsp:include page="/WEB-INF/admin/layout/js_end.jsp"></jsp:include>
</body>

</html>