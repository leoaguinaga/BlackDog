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
        <a href="#" class="btn-custom">
            Carta
            <i class="bx bxs-right-arrow"></i>
        </a>
        <a href="#" class="btn2">
            Ordenar
            <i class="bx bxs-right-arrow"></i>
        </a>
    </div>

    <jsp:include page="modal.jsp" />
    <jsp:include page="singin.jsp" />

    <div class="home-img">
        <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="10000">
                    <img src="img/homenburgerclasica.png" class="d-block w-60" alt="...">
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
                    <img src="img/homechaufadepollo.png" class="d-block w-100vh" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
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
        <h2>
            Una frase <br>
            que impacte
        </h2>
        <p>
            textotextotextotextotextotextotextotexto
            textotextotextotextotextotextotextotexto
            textotextotexto <br> <br>
            textotextotextotextotextotextotextotexto
            textotextotextotextotextotextotextotexto
            textotextotextotexto
        </p>
        <a href="#" class="btn-custom">
            Sobre nosotros <i class="bx bxs-right-arrow"></i>
        </a>
    </div>
</section>
<jsp:include page="components/footer.jsp" />