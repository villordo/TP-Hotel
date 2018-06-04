package persona;

import clases.Usuario;

public class Recepcionista extends Persona{
	private int idEmpleado;
	private Usuario cuenta;
	
	public Recepcionista(String nombre, String dni, String origen, String direccion, int id, Usuario cuenta){
		super(nombre, dni, origen, direccion);
		this.idEmpleado=id;
		this.cuenta=cuenta;
	}
	
	public String toString(){
		return "ID Empleado: "+idEmpleado+"\nNombre: "+nombre+"\nDNI: "+dni+"\nOrigen: "+origen+"\nDireccion: "+direccion;
	}

}
