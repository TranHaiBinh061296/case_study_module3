<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/fontawesome-free-6.2.0-web/css/all.min.css">
<nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
  <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
  </a>
  <a href="#" class="sidebar-toggler flex-shrink-0">
    <i class="fa fa-bars"></i>
  </a>
  <div class="navbar-nav align-items-center ms-auto">
    <div class="nav-item dropdown">
      <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <i class="fa fa-envelope me-lg-2"></i>
        <span class="d-none d-lg-inline-flex">Message</span>
      </a>
      <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
        <a href="#" class="dropdown-item">
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item">
          <div class="d-flex align-items-center">
            <i class="fa-brands fa-facebook"></i>
            <div class="ms-2">
              <h6 class="fw-normal mb-0">Facebook</h6>
            </div>
          </div>
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item">
          <div class="d-flex align-items-center">
            <img class="rounded-circle" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
            <div class="ms-2">
              <h6 class="fw-normal mb-0">Zalo</h6>

            </div>
          </div>
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item text-center">See all message</a>
      </div>
    </div>
    <div class="nav-item dropdown">
      <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <i class="fa fa-bell me-lg-2"></i>
        <span class="d-none d-lg-inline-flex">Blog</span>
      </a>
      <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
        <a href="#" class="dropdown-item">
          <h6 class="fw-normal mb-0">Profile updated</h6>
          <small>15 minutes ago</small>
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item">
          <h6 class="fw-normal mb-0">New user added</h6>
          <small>15 minutes ago</small>
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item">
          <h6 class="fw-normal mb-0">Password changed</h6>
          <small>15 minutes ago</small>
        </a>
        <hr class="dropdown-divider">
        <a href="#" class="dropdown-item text-center">See all notifications</a>
      </div>
    </div>
    <div class="nav-item dropdown">
      <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
        <img class="rounded-circle me-lg-2"src="<%=request.getParameter("image") %>" alt="" style="width: 40px; height: 40px;">
        <span class="d-none d-lg-inline-flex"><%=request.getParameter("fullname") %></span>
      </a>
      <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
        <a href="#" class="dropdown-item">My Profile</a>
        <a href="#" class="dropdown-item">Settings</a>
        <a href="#" class="dropdown-item">Log Out</a>
      </div>
    </div>
  </div>
</nav>