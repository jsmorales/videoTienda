<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />

    <body>

        <jsp:include page="menuNavGen.jsp" />

        <div class="container">

            <div class="page-header">
                <h1>${resultado.nombre} <small>${resultado.codigo}</small></h1>
            </div>

            <div class='col-md-12'>
                <div class="col-md-4">
                    <div class="row">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="width: 300px;">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <!--<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>-->
                                <c:forEach items="${resultado.imagenes}" var="pelicula" varStatus="loop">
                                    <li data-target="#carousel-example-generic" data-slide-to="${loop.index}" <c:if test="${loop.index==0}">class='active'</c:if> ></li>
                                    </c:forEach>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner text-center" role="listbox">


                                <c:forEach items="${resultado.imagenes}" var="imagen" varStatus="loop">
                                    <div class='item <c:if test="${loop.index==0}">active</c:if>'>
                                        <img src="uploads/${resultado.imagenes[loop.index].ruta}" alt="rutaImagen" width="300">                               
                                    </div>
                                </c:forEach>

                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <h2>Descripción</h2>

                    <p class="lead">${resultado.descripcion}</p>
                </div>
            </div>

        </div>
    </body>
</html>
