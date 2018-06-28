package persona;

import java.io.IOException;
import java.util.ArrayList;

import archivos.ArchivosUtility;
import clases.Usuario;

public class Administrador extends Persona{
	private int idEmpleado;
	private Usuario cuenta;

	public Administrador(String nombre, String dni, String origen, String direccion, int id, String usuario, String password){
		super(nombre, dni, origen, direccion);
		this.idEmpleado=id;
		cuenta = new Usuario(usuario, password, 0);
	}
	
	public String toString(){
		return "ID Empleado: "+idEmpleado+"\nNombre: "+nombre+"\nDNI: "+dni+"\nOrigen: "+origen+"\nDireccion: "+direccion;
	}
	
	public void crearUsuario(String usuario, String password, int tipoCuenta) throws IOException, ClassNotFoundException{
		ArrayList<Usuario> aux = ArchivosUtility.volcarArchivo("usuarios.dat");
		Usuario u = new Usuario(usuario, password, tipoCuenta);
		aux.add(u);
		ArchivosUtility.escribir("usuarios.dat", aux);
	}
}
