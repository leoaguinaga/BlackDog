<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body id="page-top">
<div id="wrapper">
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/ordersHistory">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Administrate
    </div>

    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/ingredients">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Ingredients</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/products">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Products</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Heading -->
    <div class="sidebar-heading">
        History
    </div>

    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/ordersHistory">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Orders</span></a>
    </li>
</ul>