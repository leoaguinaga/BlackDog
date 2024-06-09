<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Inicia sesión</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <div class="container-modal">
                    <div class="row d-flex align-items-center justify-content-center">
                        <div class="col-md-8 col-lg-7 col-xl-6">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid" alt="Phone image">
                        </div>
                        <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                            <form action="LoginServlet" method="post">
                                <!-- Email input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="email">Ingresa tu correo</label>
                                    <input type="text" id="email" name="email" class="form-control form-control-lg" />
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="password">Ingresa tu contraseña</label>
                                    <input type="password" id="password" name="password" class="form-control form-control-lg" />
                                </div>

                                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block d-flex align-items-center my-4">Ingresar</button>

                                <div class="d-flex justify-content-around align-items-center mb-4">
                                    <label class="form-check-label">No tienes una cuenta? <a href="singIn.jsp">Regístrate!</a></label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>