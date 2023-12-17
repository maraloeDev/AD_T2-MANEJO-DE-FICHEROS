<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Entidad.Museo"%>
<!DOCTYPE html>
<%
List<Museo> museos = (List<Museo>) application.getAttribute("museos");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<tr>
		<td>Centro</td>
		<td>Direccion</td>
		<td>Telefono</td>
	</tr>
	<%
	for(Museo museo : museos){ %>
	<tr>
		<td><%=museo.getCentro() %></td>
		<td><%=museo.getDireccion() %></td>
		<td><%=museo.getTelefono() %></td>
	</tr>
	<%} %>
</table>
</body>
</html>