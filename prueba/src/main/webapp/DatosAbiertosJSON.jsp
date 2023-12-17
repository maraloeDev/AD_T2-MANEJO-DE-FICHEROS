<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.IOException" %>
<%JSONArray jsonArray = (JSONArray)application.getAttribute("json"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
            <td>CENTRO</td>
            <td>DIRECCION</td>
            <td>TELEFONO</td>
        </tr>
        <% for (int i = 0; i < jsonArray.length(); i++) { 
           JSONObject dato = jsonArray.getJSONObject(i); %>
            <tr>
                <td><%= dato.getString("CENTRO") %></td>
                <td><%= dato.getString("DIRECCION") %></td>
                <td><%= dato.getString("TELEFONO") %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>