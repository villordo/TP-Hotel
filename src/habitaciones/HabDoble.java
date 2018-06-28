package habitaciones;


public class HabDoble extends Habitacion {

	public HabDoble(int nroHabitacion,float precio,boolean estado,String descripcion)
	{
		super(nroHabitacion,precio,4,estado,descripcion);
	}

	public String toString()
	{
		return "Nro Habitacion: "+nroHabitacion+"\nPrecio: "+precio+"\nCapacidad: "+capacidad+"\nEstado: "+estado+"\nDescripcion: "+descripcion;
	}

}

