package persona;

import java.util.ArrayList;
import java.util.Date;

import clases.Usuario;
import interfaces.IHabitacion;
import habitaciones.Habitacion;

public class Recepcionista extends Persona implements IHabitacion{
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
	/**
	 * Muestra las habitaciones del Hotel.
	 */
public void mostrarHabitaciones(ArrayList<Habitacion> list)
{
	int aux=list.size();
	for(int i=0;i<aux;i++) {
		list.get(i).toString();
	}
}
/**
 * Muestra las habitaciones disponibles en el Hotel.
 */
public void mostrarHabDisponibles(ArrayList<Habitacion> list)
{
	int aux=list.size();
	for(int i=0;i<aux;i++) {
		Habitacion auxHab=list.get(i);
		if(auxHab.getEstado())
		{
			auxHab.toString();
		}

	}
}
public int hacerUnaReserva(ArrayList<Habitacion> reserva,Date fecha_inic,Date fecha_fin,int idCliente)
{
	return 2;
}

public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitaciones> habDisponibles,int cantPersonas)
{
	
}


}
