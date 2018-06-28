package consumos;

import java.io.Serializable;

public abstract class Consumo implements Serializable {
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
	public String getNombre()
	{
		return nombre;
	}
	
	/**
	 * Muestra el estado del objeto.
	 */
	public abstract String toString();

}
