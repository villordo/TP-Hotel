package persona;

import java.io.Serializable;

public abstract class Persona implements Serializable{
	protected String nombre;
	protected String dni;
	protected String origen;
	protected String direccion;
	
	public Persona(String nombre, String dni, String origen, String direccion){
		this.nombre = nombre;
		this.dni = dni;
		this.origen = origen;
		this.direccion = direccion;
	}
	
	/**
	 * Muestra el estado del objeto.
	 */
	public abstract String toString();

}
