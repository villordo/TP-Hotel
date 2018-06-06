package persona;

import java.util.ArrayList;
import java.util.Date;

import clases.Reserva;
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
 * Crea una reserva y lo agrega al array de reservas.
 * @param reserva : un array que contiene el nro de las habitaciones a reservar.
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
public ArrayList<String> elegirHabitaciones(ArrayList<Hab> r,int cantPersonas)
{
	
}
 public ArrayList<Integer> buscarDisponibles(ArrayList<Reserva> reservas,ArrayList<Habitacion> habitaciones,Date fecha_inic,Date fecha_fin)
 {
	 int i=0;
	 Date fechaActual;
	 ArrayList<Integer> auxHab = new ArrayList<Integer>();
	 
	 for(i=0;i<habitaciones.size();i++)        //aca crea un array auxiliar con los nuemero de las habitaciones.Para luego retornar las que quedan disponibles.
	 {
		 auxHab.add(habitaciones.get(i).getNroHabitacion());
	 }
	 
	 i=0;
	 Reserva auxReserva;
	 ArrayList<Integer> auxEliminados = new ArrayList<Integer>();
	 
	 for(i=0;i<reservas.size();i++) //recorre el array de reservas
	 {
		 auxReserva = reservas.get(i);
		 
		 if((fecha_inic < auxReserva.fechaEntrada && fecha_fin < auxReserva.fechaSalida) && (fecha_inic >= fechaActual )) //si el periodo ingresado se encuentra antes al que contiene la reserva
		 {
			 //en este caso las nuevas fechas ingresadas no se pisan con las de la reserva,es decir,es posible hacer una reserva en ella.
		 }
		 else
		 {
			 if(fecha_inic > auxReserva.fechaSalida) //si el periodo ingresado se encuentra despues de el de la reserva
			 {
				 //en este caso tambien es posible hacer una reserva en dicho periodo.
			 }
			 else//este seria el caso en el que no seria posible realizar la reserva
			 {
				 for(i=0;i<auxReserva.habitaciones.size();i++) //recorre las habitaciones que contiene la reserva
				 {
					 if(!estaEliminado(auxReserva.habitaciones, auxReserva.habitaciones.get(i))) //si la habitacion ya ha sido agregada al array "auxEliminados" que contiene las habitaciones que no van a poder ser reservadas
					 {
						 auxEliminados.add(auxReserva.habitaciones.get(i)); //primero agrega el nro de la hab al array elminados para que al querer elimnar una habitacion que posiblemente ha sido eliminada no se rompa.(podria ser una execpcion?)
						 auxHab.remove((auxReserva.habitaciones.get(i))-1); //remueve el nro de habitacion para luego retornar las disponibles para la reserva
					 }
				 }
			 }
		 }
		 
	 }
	 return auxHab;
 }

public boolean estaEliminado(ArrayList<Habitacion> habitaciones,int nroHabitacion)
{
	int i=0;
	boolean rta=false;
	Habitacion auxHab;
	for(i=0;i<habitaciones.size();i++)
	{
		Habitacion auxHab = habitaciones.get(i);
		if(auxHab.nroHabitacion == nroHabitacion)
		{
			rta=true;
		}
	}
	return rta;
}

}
