import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class LogicXML_DOM {
	// Método para sobrescribir el archivo XML y agregar un nuevo elemento
	void sobrescribirArchivoXML(String nombreEleccion, String nombreComarca, String codigoEleccion, String codigoComarca, String poblacionDerecho, String mesas, String votantes, String abstencion, String nulos, String blancos, String candidatura, String electores, String validos, String nElectos, String nConsejeros) throws IOException {
	    File file = new File("../ActividadManejoFicheros/Archivos/Resultados de participación electoral - Ámbito Comarcas de Aragón.xml");
		try {
	        // Cargar el documento XML existente
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(file);

	        // Crear un nuevo elemento
	        Element nuevoElemento = (Element) doc.createElement("list-item");

	        // Crear subelementos y agregar valores
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "nombre_elec", nombreEleccion));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "nombre_o", nombreComarca));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "cod_elec", codigoEleccion));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "cod_comarca", codigoComarca));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "pobl_derecho_o", poblacionDerecho));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "mesas_o", mesas));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "votantes_o", votantes));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "abstencion_o", abstencion));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "nulos_o", nulos));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "blancos_o", blancos));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "candidatura_o", candidatura));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "electores_o", electores));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "validos_o", validos));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "n_electos_o", nElectos));
	        ((Node) nuevoElemento).appendChild((Node) crearElemento(doc, "n_consejeros_o", nConsejeros));

	        // Obtener el elemento raíz y agregar el nuevo elemento
	        Node raiz = doc.getDocumentElement();
	        raiz.appendChild((Node) nuevoElemento);

	        // Transformar y sobrescribir el archivo XML
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(file);
	        transformer.transform(source, result);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// Método auxiliar para crear un elemento con un valor
	private Element crearElemento(Document doc, String nombreElemento, String valor) {
	    Element elemento = (Element) doc.createElement(nombreElemento);
	    ((Node) elemento).appendChild(doc.createTextNode(valor));
	    return elemento;
	}
}
