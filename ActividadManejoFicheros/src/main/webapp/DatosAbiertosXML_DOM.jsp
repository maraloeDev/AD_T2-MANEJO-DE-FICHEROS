<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.io.File"%>
<%@ page import="org.w3c.dom.Element"%>
<%@ page import="org.w3c.dom.NodeList"%>
<%@ page import="org.w3c.dom.Node"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
// Inicializaci�n de NodeList para almacenar los elementos del archivo XML
NodeList nList;
// Ruta del archivo XML
File file = new File("../ActividadManejoFicheros/Archivos/Resultados de participaci�n electoral - �mbito Comarcas de Arag�n.xml");
try {
    // Creaci�n de objetos para procesar el documento XML
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);

    // Normalizaci�n del documento
    doc.getDocumentElement().normalize();

    // Obtenci�n de la lista de nodos con etiqueta "list-item"
    nList = doc.getElementsByTagName("list-item");
} catch (Exception e) {
    e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>AD_T2: MANEJO DE FICHEROS - Archivos XML con DOM</title>
</head>
<body>
    <form action="ServletAcceso" method="post">
        <!-- Tabla para mostrar los datos del archivo XML -->
        <table border="2">
            <tr>
                <!-- Encabezados de la tabla -->
                <td>NOMBRE DE LA ELECCI�N</td>
                <td>NOMBRE</td>
                <td>CODIGO DE LA ELECCION</td>
                <td>CODIGO DE LA COMARCA</td>
                <td>DERECHO DE LA POBLACION</td>
                <td>MESA</td>
                <td>VOTANTES</td>
                <td>ABSTENCIONES</td>
                <td>NULOS</td>
                <td>BLANCOS</td>
                <td>CANDIDATURA</td>
                <td>ELECTORES</td>
                <td>VALIDOS</td>
                <td>NUMERO DE ELECTORES</td>
                <td>NUMERO DE CONSEJEROS</td>
            </tr>

            <%
            // Iteraci�n sobre la lista de nodos obtenida del archivo XML
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                // Verificaci�n de que el nodo sea de tipo ELEMENT_NODE
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // Extracci�n de datos del elemento XML
                    String nombre_elec = eElement.getElementsByTagName("nombre_elec").item(0).getTextContent();
                    String nombre_o = eElement.getElementsByTagName("nombre_o").item(0).getTextContent();
                    String cod_elec = eElement.getElementsByTagName("cod_elec").item(0).getTextContent();
                    String cod_comarca = eElement.getElementsByTagName("cod_comarca").item(0).getTextContent();
                    String pobl_derecho_o = eElement.getElementsByTagName("pobl_derecho_o").item(0).getTextContent();
                    String mesas_o = eElement.getElementsByTagName("mesas_o").item(0).getTextContent();
                    String votantes_o = eElement.getElementsByTagName("votantes_o").item(0).getTextContent();
                    String abstencion_o = eElement.getElementsByTagName("abstencion_o").item(0).getTextContent();
                    String nulos_o = eElement.getElementsByTagName("nulos_o").item(0).getTextContent();
                    String blancos_o = eElement.getElementsByTagName("blancos_o").item(0).getTextContent();
                    String candidatura_o = eElement.getElementsByTagName("candidatura_o").item(0).getTextContent();
                    String electores_o = eElement.getElementsByTagName("electores_o").item(0).getTextContent();
                    String validos_o = eElement.getElementsByTagName("validos_o").item(0).getTextContent();
                    String n_electos_o = (eElement.getElementsByTagName("n_electos_o").getLength() > 0)
                            ? eElement.getElementsByTagName("n_electos_o").item(0).getTextContent()
                            : "NINGUNO";
                    String n_consejeros_o = eElement.getElementsByTagName("n_consejeros_o").item(0).getTextContent();
            %>
            <!-- Fila de datos en la tabla -->
            <tr>
                <td><%=nombre_elec%></td>
                <td><%=nombre_o%></td>
                <td><%=cod_elec%></td>
                <td><%=cod_comarca%></td>
                <td><%=pobl_derecho_o%></td>
                <td><%=mesas_o%></td>
                <td><%=votantes_o%></td>
                <td><%=abstencion_o%></td>
                <td><%=nulos_o%></td>
                <td><%=blancos_o%></td>
                <td><%=candidatura_o%></td>
                <td><%=electores_o%></td>
                <td><%=validos_o%></td>
                <td><%=n_electos_o%></td>
                <td><%=n_consejeros_o%></td>
            </tr>
            <%
            }
            }
            %>
        </table>
        <!-- Bot�n para volver -->
        <br> <input type="submit" name="volver" value="Volver">
    </form>
</body>
</html>