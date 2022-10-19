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

<%--        <c:if test="${!requestScope.errors.isEmpty()&&requestScope.errors!=null }">--%>
<%--            <c:forEach items="${requestScope.errors}" var="item">--%>
<%--                <div class="alert alert-warning" role="alert">--%>
<%--                    <div class="alert alert-danger alert-dismissible" role="alert">--%>
<%--                        <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span--%>
<%--                                class="mdi mdi-close" aria-hidden="true"></span></button>--%>
<%--                        <div class="icon"><span class="mdi mdi-close-circle-o"></span></div>--%>
<%--                        <div class="message">--%>
<%--                            <span>Error!</span>--%>
<%--                                ${item}--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>


<%--        <c:if test="${requestScope.message!=null}">--%>
<%--            <div class="alert alert-success alert-dismissible" role="alert">--%>
<%--                <button class="close" type="button" data-dismiss="alert" aria-label="Close"><span--%>
<%--                        class="mdi mdi-close" aria-hidden="true"></span></button>--%>
<%--                <div class="icon"><span class="mdi mdi-check"></span></div>--%>
<%--                <div class="message">--%>
<%--                    <strong><i class="fa-solid fa-check"></i></strong>--%>
<%--                        ${requestScope.message}--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:if>--%>

        <!-- Form Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-light rounded h-100 p-4">
                        <h6 class="mb-4">ADD USER</h6>
                        <form action="" method="post">
                            <c:if test="${requestScope.user != null}">
                                <input type="hidden" name="id" value="<c:out value='${user.getId()}' />"/>
                            </c:if>
                            <div class="mb-3">
                                <label for="" class="form-label">UserName</label>
                                <input type="text" class="form-control" name="username" id="name" value="${user.getUsername()}"  placeholder="Enter username">
                                <div  class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label">Password</label>
                                <input min="0" type="password" name="password" id="password" value="${user.getPassword()}" class="form-control" placeholder="">
                                <div  class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label">Full Name</label>
                                <input  type="text" name="fullname" id="fullname" value="${user.getFullname()}" class="form-control" placeholder="Enter full name">
                                <div id="" class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label">Phone </label>
                                <input type="number" name="phone" id="phone" value="${user.getPhone()}" class="form-control" placeholder="Enter phone (0987654321)">
                                <div  class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label">Email </label>
                                <input  type="text" name="email" id="email" value="${user.getEmail()}" class="form-control" placeholder="Enter email (hayhoi@gmail.com)">
                                <div  class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label"> Address</label>
                                <input  type="text" name="address" id="address" value="${user.getAddress()}" class="form-control" placeholder="Enter address (Huế)">
                                <div  class="form-text">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="" class="form-label">Image</label>
                                <input class="form-control" id="image" type="text" name="image"
                                       placeholder="Input image"
                                       onchange="chooseFile()"
                                       value="${user.getImage()}">
                                <div class="form-text">
                                </div>
                            </div>
                            <div style="display: flex; padding: 10px" >
                                <div><button type="submit" class="btn btn-primary">Create</button></div>
                                <div class="btn-group">
                                    <a href="/users" title="Exit" class="btn btn-outline-warning" style="color: black; font-weight: bold">
                                        <i class="glyphicon glyphicon-floppy-disk " aria-hidden="true" ></i> Exit
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-light rounded h-100 p-4">
                        <img id="imageUser"
                             src="https://vnn-imgs-a1.vgcloud.vn/image1.ictnews.vn/_Files/2020/03/17/trend-avatar-1.jpg"
                             alt="user image"
                             style="width: 100%">
                    </div>
                    <%--                    <div class="bg-light rounded h-10 p-4">--%>
                    <%--                        <img--%>
                    <%--                                src="https://donggia.vn/wp-content/uploads/2018/12/mau-thiet-ke-noi-that-nha-pho-dep-moi-2020-5-1-1024x576.jpg"--%>
                    <%--                                alt="user image"--%>
                    <%--                                style="width: 100%">--%>
                    <%--                    </div>--%>
                </div>
            </div>
        </div>
        <!-- Form End -->


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
<script>
    function chooseFile() {
        let image_src = document.querySelector("#image").value;
        let image = document.querySelector("#imageUser");
        image.setAttribute("src", image_src);
    }
</script>
</body></html>