package habitaciones;

public class HabSimple extends Habitacion {

	public HabSimple(int nroHabitacion,float precio,boolean estado,String descripcion)
	{
		super(nroHabitacion,precio,2,estado,descripcion);
	}	

	public String toString()
	{
		return "Nro Habitacion: "+nroHabitacion+"\nPrecio: "+precio+"\nCapacidad: "+capacidad+"\nEstado: "+estado+"\nDescripcion: "+descripcion;
	}

}