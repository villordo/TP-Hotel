package habitaciones;

import java.io.Serializable;

public abstract class Habitacion implements Serializable{
	protected int nroHabitacion;
	protected float precio;
	protected int capacidad;
	protected boolean estado;
	protected String descripcion;


	public Habitacion(int nroHabitacion, float precio, int capacidad, boolean estado, String descripcion)
	{
		this.nroHabitacion = nroHabitacion;
		this.precio = precio;
		this.capacidad = capacidad;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public float getPrecio()
	{
		return precio;
	}
	
	public int getCapacidad()
	{
		return capacidad;
	}
	
	public boolean getEstado()
	{
		return estado;
	}
	
	public int getNroHabitacion(){
		return nroHabitacion;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public abstract String toString();

	public String mostrarHab()
	{
		return "\nNro Habitacion: "+nroHabitacion;
	}
	
	public boolean equals(Habitacion h) {
		if(h.getNroHabitacion()==nroHabitacion) {
			return true;
		}
		return false;
	}
}