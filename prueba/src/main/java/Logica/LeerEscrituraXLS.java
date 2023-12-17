package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Entidad.Museo;

public class LeerEscrituraXLS {
	public static List<Museo> leerXLS(String fichero) {
		List<Museo> museos = new ArrayList<>();
		try {
			
		Workbook workbook = WorkbookFactory.create(new File(fichero));
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1 ; i < rows ; i++) {
			 Row row = sheet.getRow(i);
			 Museo museo= new Museo();
			 museo.setCentro(row.getCell(0).getStringCellValue());
			 museo.setDireccion(row.getCell(1).getStringCellValue());
			 museo.setTelefono(row.getCell(2).getStringCellValue());
			 museos.add(museo);

		}
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return museos;
	}
	
	public static void escribirXLS(Museo museo, String fichero) {
		try {
			FileInputStream fis = new FileInputStream(fichero.toString());
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0);
			int newRow = sheet.getLastRowNum();
			Row row = sheet.createRow(newRow+1);
			row.createCell(0).setCellValue(museo.getCentro());
			row.createCell(1).setCellValue(museo.getDireccion());
			row.createCell(2).setCellValue(museo.getTelefono());
			
            fis.close();

            // Guardar los cambios en el archivo
            FileOutputStream fos = new FileOutputStream(fichero);
            workbook.write(fos);

            // Cerrar el flujo de salida
            fos.close();

		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
}
