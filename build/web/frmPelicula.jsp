<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />
    <body>

        <jsp:include page="menuNavGen.jsp" />

        <div class="container">
            
            <div class="page-header">
                <h1>${mensaje}</h1>
            </div>
            
            <form action="pelicula" method="post">
                <div class="form-group hidden">
                    <label for="pkID">pkID</label>
                    <input type="text" class="form-control" id="pkID" name="pkID" value='${resultado.pkID}'>
                </div>
                <div class="form-group">
                    <label for="codigo">Código</label>
                    <input type="text" class="form-control" id="codigo" name="codigo" placeholder="Código" value='${resultado.codigo}'>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" value='${resultado.nombre}'>
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <textarea class="form-control" name="descripcion" id="descripcion" rows="4" placeholder="Escriba la descripcion de la película" value='${resultado.descripcion}'>${resultado.descripcion}
                    </textarea>
                </div>
                <button type="submit" class="btn btn-success">${mensaje}</button>
            </form>
            
            <hr>
            
            <div class="row">
                <c:forEach items="${resultado.imagenes}" var="pelicula" varStatus="loop">    
                    <div class="col-xs-6 col-md-3">
                        <a href='uploads/${resultado.imagenes[loop.index].ruta}' class="thumbnail" title="Ver imagen">                     
                            <img src="uploads/${resultado.imagenes[loop.index].ruta}" alt="...">
                            
                            <a href='imagen?action=eliminarImagen&pkID=${resultado.imagenes[loop.index].pkID}&pkID_pelicula=${resultado.pkID}' class="btn btn-danger">Eliminar</a>
                        </a>
                        
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
