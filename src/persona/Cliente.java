package persona;

public class Cliente extends Persona{
	private int idCliente;
	
	public Cliente(String nombre, String dni, String origen, String direccion, int idCliente){
		super(nombre, dni, origen, direccion);
		this.idCliente=idCliente;
	}
	
	public String toString(){
		return "ID Cliente: "+idCliente+"\nNombre: "+nombre+"\nDNI: "+dni+"\nOrigen: "+origen+"\nDireccion: "+direccion;
	}
}
