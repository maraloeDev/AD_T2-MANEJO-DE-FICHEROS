<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <!-- errorPage="Error.jsp" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>TRATAMIENTO FICHEROS</h1>
    <fieldset>
    <form action="ServletFicheros" method="post">
    <div style="display: flex;">
        <div style="flex: 1; margin-right: 20px;">
            <p>Formato del fichero</p>
            <select name="archivos">
                <option value="XLS">XLS</option>
                <option value="JSON">JSON</option>
                <option value="XML">XML</option>
                <option value="CSV">CSV</option>
            </select>
            <hr>
            <p>¿Qué quieres hacer con el fichero?</p> 
            <p>
                <label>
                    Lectura: <input type="radio" name="accion" value="lectura" checked="checked">
                </label>
            </p>
            <p>
                <label>
                    Escritura: <input type="radio" name="accion" value="escritura">
                </label>
            </p>
        </div>
        <div style="flex: 1;">
            <p>Centro: <input type="text" name="centro"></p>
            <p>Direccion: <input type="text" name="direccion"></p>
            <p>Telefono: <input type="text" name="telefono"></p>
            <p style="color: red;"><%= application.getAttribute("mensaje")!=null?application.getAttribute("mensaje"):"" %></p>
        </div>
    </div>
    <input type="submit" name="Enviar" >
    </form>
    </fieldset>
</body>
</html>