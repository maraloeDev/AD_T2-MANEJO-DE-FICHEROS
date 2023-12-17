package Entidad;

import com.opencsv.bean.CsvBindByName;

public class Museo {
	@CsvBindByName(column = "CENTRO")
	private String Centro;
	@CsvBindByName(column = "DIRECCION")
	private String Direccion;
	@CsvBindByName(column = "TELEFONO")
	private String Telefono;
	
	public Museo(String centro, String direccion, String telefono) {
		Centro = centro;
		Direccion = direccion;
		Telefono = telefono;
	}
	
	

	public Museo() {
	}



	public String getCentro() {
		return Centro;
	}

	public void setCentro(String centro) {
		Centro = centro;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

}
