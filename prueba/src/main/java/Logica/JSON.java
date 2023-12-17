package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import Entidad.Museo;


public class JSON {
	
	public static String filepath = "Archivos/datos.json";
	
	public static void escritura(Museo datos) {
        // Leer el JSON existente desde el archivo
		JSON j = new JSON();
		String rutaRelativa = j.obtenerRutaRelativa();
        JSONArray jsonArray = readJsonFromFile(rutaRelativa);

        // Crear un nuevo objeto de datos
        JSONObject newData = new JSONObject();
        newData.put("CENTRO", datos.getCentro());
        newData.put("DIRECCION", datos.getDireccion());
        newData.put("TELEFONO", datos.getTelefono());

        // Añadir el nuevo objeto de datos al array JSON existente
        jsonArray.put(newData);

        // Escribir el array JSON actualizado de nuevo al archivo
        writeJsonToFile(jsonArray, rutaRelativa);
	}
	
	public static JSONArray lectura(){//Funciona
		//Leer el contenido del archivo JSON como una cadena
		JSON j = new JSON();
		String rutaRelativa = j.obtenerRutaRelativa();
		StringBuilder jsonStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaRelativa))) {
	    	String line;
	    	while ((line = br.readLine()) != null) {
	        	jsonStringBuilder.append(line);
	   	 	}
		} catch (IOException e) {
	    	e.printStackTrace();
		}

		// Procesar el JSON utilizando la biblioteca org.json
		JSONArray jsonArray = new JSONArray(jsonStringBuilder.toString());
		System.out.println(jsonArray.toString());
		return jsonArray;
	}
	
	private static JSONArray readJsonFromFile(String filePath) {
		File json = new File(filePath);
		String jsonString;
		try {
			jsonString = new String(java.nio.file.Files.readAllBytes(json.toPath()));
			return new JSONArray(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONArray();
    }

    private static void writeJsonToFile(JSONArray jsonArray, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toString(4)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String obtenerRutaRelativa() {
        // Obtén el ClassLoader
        ClassLoader classLoader = getClass().getClassLoader();

        // Utiliza el ClassLoader para obtener la URL del recurso
        URL resourceUrl = classLoader.getResource("Archivos/datos.json");

        if (resourceUrl != null) {
            // Convierte la URL a una cadena de texto y devuelve la ruta relativa
            return resourceUrl.getPath();
        } else {
            return "No se pudo encontrar el archivo";
        }
    }
    
    private static String obtenerRutaAbsoluta() {
        Path pathRelativo = Paths.get("Archivos/datos.json");
        Path pathAbsoluto = pathRelativo.toAbsolutePath();
        return pathAbsoluto.toString();
    }
}
