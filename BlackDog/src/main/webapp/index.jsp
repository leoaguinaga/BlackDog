<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="components/head.jsp" />
<jsp:include page="components/header.jsp" />
<section class="home" id="home">
    <div class="home-text">
        <h1>
            Conoce,
            <span>Come y</span> <br>
            Disfruta una buena <br>
            burger
        </h1>
        <a href="${pageContext.request.contextPath}/menu?type=HAMBURGER" class="btn-custom">
            Carta
            <i class="bx bxs-right-arrow"></i>
        </a>
    </div>

    <jsp:include page="modal.jsp" />
    <jsp:include page="singin.jsp" />

    <div class="home-img">

        <div id="carouselExampleFade" class="carousel slide carousel-fade">
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="10000">
                    <img src="img/homenburgerclasica.png" class="d-block w-100vh" alt="...">
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                    <img src="img/homeburgercheeseburger.png" class="d-block w-100vh" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="img/homeburgeralopobre.png" class="d-block w-100vh" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="img/homeburgerdelacasa.png" class="d-block w-100vh" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="img/homechaufadepollo.png" class="d-block w-100vh" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="img/homechaufadececina.png" class="d-block w-100vh" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

    </div>
</section>

<section class="about" id="about">

    <div class="about-img">
        <img src="img/logo final.png">
    </div>

    <div class="about-text">
        <h2>Misión</h2>
        <p>
            Deleitar a los habitantes de Chiclayo con alimentos frescos, sabrosos y de alta calidad,
            como hamburguesas, salchipapas y chaufa, preparados con pasión y entregados
            directamente a sus puertas. Nos dedicamos a brindar una experiencia de servicio al
            cliente excepcional, asegurando rapidez, conveniencia y satisfacción en cada pedido,
            adaptándonos a las necesidades de nuestros clientes en un mundo cada vez más digital.
        </p>
    </div>
    <div class="about-text">>
        <h2>Visión</h2>
        <p>
            Convertirnos en el referente líder de la comida rápida delivery en Chiclayo, reconocidos
            por nuestra calidad, sabor inigualable y compromiso con la satisfacción del cliente.
            Aspiramos a expandir nuestra presencia y capacidad de servicio, logrando que cada
            comida entregada represente una experiencia culinaria única. Nos vemos en el futuro
            como una marca consolidada y preferida, con planes de expansión que incluyan locales
            físicos para una mayor cercanía con nuestros clientes, manteniendo siempre nuestra
            esencia innovadora y centrada en el cliente.
        </p>
    </div>
    <div class="about-img">
        <img src="img/logo final.png">
    </div>
</section>
<jsp:include page="components/footer.jsp" />