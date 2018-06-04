package clases;

public class Consumo {
	private int idArticulo;
	private String nombre;
	private float precio;
	
	public Consumo(int idArticulo, String nombre, float precio){
		this.idArticulo=idArticulo;
		this.nombre=nombre;
		this.precio=precio;
		
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public int getIdArticulo(){
		return idArticulo;
	}
	
	/**
	 * Muestra el estado del objeto.
	 */
	public String toString() {
		return "Nombre: "+nombre+"\nPrecio: "+precio;
	}

}
