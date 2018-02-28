<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="header.jsp" />

  <body>
    <jsp:include page="menuNavGen.jsp" />
    <div class="container">
      <br>
      <div class="panel panel-primary">
        <div class="panel-heading">
          <h3 class="panel-title">videoTienda</h3>          
        </div>
        <div class="panel-body">
            <h4>${mensaje}</h4>
            <c:if test="${link != null}">
                <a href="${link}">Volver</a>
            </c:if>
        </div>
      </div>

      

    </div> <!-- /container -->

  </body>
</html>

