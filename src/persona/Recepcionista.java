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
/**
 * Crea una reserva y lo agrega al arrar de reservas.
 * @param reserva : array que contiene las habitaciones que ocupara dicha Reserva.
 * @param fecha_inic : fecha en el que inicia la Reserva.
 * @param fecha_fin : fecha en que se finaliza la Reserva.
 * @param idCliente : es el ID del cliente que realizara la Reserva.
 * @return : el id de la reserva.
 */
public int hacerUnaReserva(ArrayList<Habitacion> reserva,Date fecha_inic,Date fecha_fin,int idCliente)
{
	return 2;
}
/**
 * Crea un ArrayList con las habitaciones elegidas por el Usuario y las retorna. 
 * @param habDisponibles : es un array que contiene las habitaciones disponibles.
 * @param cantPersonas : cantidad de personas que se desean alojar en el Hotel.
 * @return un ArrayList con las habitaciones elegidas por el Usuario.
 */
public ArrayList<String> elegirHabitaciones(ArrayList<Habitaciones> habDisponibles,int cantPersonas)
{
//asdasad	
}

}
