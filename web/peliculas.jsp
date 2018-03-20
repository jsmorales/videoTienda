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
                                    
                                    
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                          Opciones
                                          <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                          <li><a href="pelicula?action=detallePelicula&pkID_pelicula=${resultado.pkID}"><span class="glyphicon glyphicon-eye-open"></span> Ver Detalles</a> </li>
                                          <li><a href="frmImagen.jsp?pkID_pelicula=${resultado.pkID}"><span class="glyphicon glyphicon-upload"></span> Agregar Imagen</a></li>
                                          <li><a href="pelicula?action=cargarPelicula&pkID_pelicula=${resultado.pkID}"><span class="glyphicon glyphicon-edit"></span> Editar</a></li>
                                          <li><a class="abreDialogo" data-toggle="modal" data-target="#dialogoModal" data-idpelicula="${resultado.pkID}" data-nompelicula="${resultado.nombre}"><span class="glyphicon glyphicon-erase"></span> Eliminar</a></li>
                                        </ul>
                                      </div>
                                </p>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                
                <div class="modal fade" tabindex="-1" role="dialog" id="dialogoModal">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">Eliminar Película</h4>
                            </div>
                            <div class="modal-body">
                                <p>Esta segur@ que desea eliminar esta película?</p>
                            </div>
                            <div class="modal-footer">                                
                                <a href="pelicula?action=eliminarPelicula&pkID=id_pel" class="btn btn-danger" role="button">Eliminar</a>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </div>
        </div>
        
        <script src="js/pelicula.js"></script>
    </body>
</html>
