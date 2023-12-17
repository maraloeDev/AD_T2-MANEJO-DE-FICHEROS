<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Entidad.Museo"%>
<%@page import="java.util.List"%>
<%!List<Museo> lista;%>
<%
lista = (List<Museo>) application.getAttribute("csv");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Resultados CSV</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>CENTRO</td>
			<td>DIRECCION</td>
			<td>TELEFONO</td>

		</tr>
		<%
		for (Museo museo : lista) {
			out.print("<tr>" + "<td>" + museo.getCentro() + "</td>" + "<td>" + museo.getDireccion() + "</td>" + "<td>"
			+ museo.getTelefono() + "</td></tr>");
		}
		%>
	</table>
</body>
</html>


