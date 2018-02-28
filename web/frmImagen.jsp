
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp" />
    <body>

        <jsp:include page="menuNavGen.jsp" />

        <div class="container">
            
            <div class="page-header">
                <h1>Agregar Imágenes a Película</h1>
            </div>

            <form action="imagen" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="pkID_pelicula">Num. Película</label>
                    <input type="text" class="form-control" id="pkID_pelicula" name="pkID_pelicula" value='<%=request.getParameter("pkID_pelicula")%>'>
                </div>
                <!--<div class="form-group">
                    <label for="archivo">Archivo</label>
                    <input type="file" required id="archivo" name="archivo">
                    <p class="help-block">Solo se permiteo archivos [ jpg,jpeg,png ]</p>
                </div>-->
                
                <div class="form-group">
                    <label for="archivo">Archivos</label>
                    <input type="file" required id="archivo" name="archivo" multiple>
                    <p class="help-block">Solo se permiten archivos [ jpg,jpeg,png ]</p>
                </div>
                
                <button type="submit" class="btn btn-success">Subir</button>
            </form>
        </div>
    </body>
</html>
