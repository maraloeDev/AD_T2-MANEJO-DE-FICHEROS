package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import Entidad.Museo;

public class CSV {
	public static List<Museo> leerCSV(String fichero) {
		String line;
        List<Museo> museos = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
        	br.readLine();
        	while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                
                Museo persona = new Museo(datos[0], datos[1], datos[2]);
                museos.add(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return museos;

	
	}
	
	
	

	public static void escribirCSV(String fichero, Museo museo) {
		System.out.println("Empezar a escribir");

		List<Museo> museos = leerCSV(fichero);
		museos.add(museo);

		try (FileWriter writer = new FileWriter(fichero)) {
			new StatefulBeanToCsvBuilder<Museo>(writer).withSeparator(',').build().write(museos);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("Terminar de escribir");
		
	}
}
	

