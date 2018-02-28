<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />

    <body>
        
        <jsp:include page="menuNavGen.jsp" />
        
        <div class="container">
            

            <div class="jumbotron">
                <h1>Bienvenido a videoTienda</h1>
                <p>Sitio creado para gestionar películas en línea.</p>
                <p><a class="btn btn-primary btn-lg" href="pelicula?action=all" role="button">Listado de Películas</a></p>
            </div>
            
            <div class="page-header">
                <h1>Películas Recientes <br> <small>Más reciente a más antigua.</small></h1>
                <hr>
                <div class="row">
                    <c:forEach items="${resultado}" var="resultado" varStatus="status">

                        <div class="col-sm-3 col-md-4">
                            <div class="thumbnail">
                                <img src="uploads/${resultado.imagenes[0].ruta}" alt="imagen" height="200" width="200">
                                <div class="caption">
                                    <h3>${resultado.nombre}</h3>

                                    <c:if test="${resultado.descripcion.length() > 150}" >
                                       <p>${resultado.descripcion.substring(0,150)}...</p> 
                                    </c:if>

                                    <c:if test="${resultado.descripcion.length() < 150}" >
                                       <p>${resultado.descripcion}</p> 
                                    </c:if>

                                    <p>
                                        <a href="pelicula?action=detallePelicula&pkID_pelicula=${resultado.pkID}" class="btn btn-primary" role="button">Ver Detalles</a>                                                                                                             
                                    </p>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
