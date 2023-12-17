package Servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import Entidad.Museo;
import Logica.CSV;
import Logica.JSON;
import Logica.LeerEscrituraXLS;
import Logica.XmlFileHandler;



public class ServletFicheros extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletFicheros() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		String tipo = request.getParameter("archivos");
		String accion = request.getParameter("accion");
		Museo datos = new Museo(request.getParameter("centro"), request.getParameter("direccion"), request.getParameter("telefono"));
		String pagina = "";
		String mensaje = "";
		

		switch (tipo) {
			case "XLS":
				// Obtén el ClassLoader
		        ClassLoader classLoaderxls = getClass().getClassLoader();
		        // Utiliza el ClassLoader para obtener la URL del recurso
		        URL resourceUrlxls = classLoaderxls.getResource("Archivos/turismo-museos.xlsx");
				String rutaxls = resourceUrlxls.getPath();
				if(accion.equals("lectura")) {
					List<Museo> museos = LeerEscrituraXLS.leerXLS(rutaxls);
					System.out.println(rutaxls);
					sc.setAttribute("museos", museos);
					pagina = "/DatosAbiertosXLS.jsp";
				}else {
					LeerEscrituraXLS.escribirXLS(datos, rutaxls);
					Desktop.getDesktop().open(new File(rutaxls));
					pagina = "/DatosAbiertosXLS.jsp";
				}
				break;
			case "XML":
				// Obtén el ClassLoader
		        ClassLoader classLoaderxml = getClass().getClassLoader();
		        // Utiliza el ClassLoader para obtener la URL del recurso
		        URL resourceUrlxml = classLoaderxml.getResource("Archivos/turismo-museos-XML.xml");
				String rutaxml = resourceUrlxml.getPath();
				XmlFileHandler xml = new XmlFileHandler(rutaxml);
				if(accion.equals("lectura")) {
					sc.setAttribute("xmlHandler", xml);
					pagina = "/DatosAbiertosXML.jsp";
				}else {
					xml.writeXmlAndMap(datos);
					pagina = "/Acceso.jsp";
					mensaje = "El fichero se ha escrito correctamente.";
				}
				break;
			case "CSV":
				// Obtén el ClassLoader
		        ClassLoader classLoadercsv = getClass().getClassLoader();
		        // Utiliza el ClassLoader para obtener la URL del recurso
		        URL resourceUrlcsv = classLoadercsv.getResource("Archivos/turismo-museos.csv");
				String rutacsv = resourceUrlcsv.getPath();
				System.out.println(rutacsv);
				if(accion.equals("lectura")) {
					List lista = CSV.leerCSV(rutacsv);
					sc.setAttribute("csv", lista);
					pagina = "/DatosAbiertosCSV.jsp";
				}else {
					CSV.escribirCSV(rutacsv, datos);
					pagina = "/Acceso.jsp";
					mensaje = "El fichero se ha escrito correctamente.";
				}
				break;
			case "JSON":
				if(accion.equals("lectura")) {
					JSONArray jsonArray =  JSON.lectura();
					sc.setAttribute("json", jsonArray);
					pagina = "/DatosAbiertosJSON.jsp";
				}else {
					JSON.escritura(datos);
					pagina = "/Acceso.jsp";
					mensaje = "El fichero se ha escrito correctamente.";
				}
				break;
			default:
				break;
		}

		//mensaje = "(*) Los campos no pueden estar vacios.";
		sc.setAttribute("mensaje", mensaje);
		

		request.getRequestDispatcher(pagina).forward(request, response);
	}

}
