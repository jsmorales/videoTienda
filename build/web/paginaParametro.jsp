<!-- Esto codigo se unsa en el jsp que manda a llamar esta vistacomo ejemplo
<jsp:include page="paginaParametro.jsp">  
    <jsp:param name="parametro1" value="El parametro ha sido llamado." />
    <jsp:param name="parametro2" value="false" />
    <jsp:param name="parametro3" value="menuNavGen.jsp" />
</jsp:include>-->

<h3><%=request.getParameter("parametro1") %></h3>

<%if(request.getParameter("parametro2").equals("true")){%>
    <p>El parametro2 es true.</p>
<%}else{%>
    <p>El parametro2 es false.</p>
    <jsp:include page='<%=request.getParameter("parametro3") %>'/>
<%}%>