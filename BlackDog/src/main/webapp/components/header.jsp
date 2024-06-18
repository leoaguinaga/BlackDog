<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .modal-content {
        background-color: rgba(255, 255, 255, 0.2);
        backdrop-filter: blur(10px);
    }

    .modal-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .modal-title {
        font-size: var(--h2-font);
        color: var(--main-color);
    }

    .close {
        color: white;
        font-size: var(--h2-font);
        text-shadow: 0 0 5px red;
        opacity: 1;
        background-color: transparent;
        border: none;
        padding: 0;
    }

    .close:hover {
        color: red;
        text-shadow: 0 0 10px red;
    }

    .signUp {
        color: var(--main-color);
        text-decoration: none;
    }

    .signUp:hover,
    .signUp:focus {
        color: var(--other-color);
        text-decoration: underline;
    }

    /* Estilo adicional para la etiqueta del formulario */
    .form-label {
        font-weight: bold;
    }

    /* Eliminar padding para el form-control */
    .form-control {
        padding: .375rem .75rem;
    }

    .modal-body {
        padding: 2rem;
    }
</style>

<header>
    <a href="#" class="logo"> <i class="bx bx-home"></i> Black Dog</a>

    <ul class="navlist">
        <li> <a href="index.jsp" class="active"> Home </a></li>
        <li> <a href="#about"> About us </a></li>
        <li> <a href="${pageContext.request.contextPath}/menu?type=HAMBURGER"> Carta </a></li>
        <li> <a href="#"> Contáctanos </a></li>
    </ul>

    <%
        String name = (String) session.getAttribute("name");
        if (name == null) { %>
    <div class="nav-icons">
        <a href="#" data-toggle="modal" data-target="#myModal" class="nav-link">Log in</a>
    </div>
    <%
    } else { %>
    <li><a class="nav-link scrollto" href="dashboard.jsp">Hola, <%= name %></a></li>
    <li class="nav-item" style="padding-left: 5px">
        <form action="logout" method="post" class="d-inline">
            <button type="submit" class="btn btn-primary">Logout</button>
        </form>
    </li>
    <%
        }
    %>



</header>
