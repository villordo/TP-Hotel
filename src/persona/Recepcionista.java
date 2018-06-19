package persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import clases.Hotel;
import clases.Reserva;
import clases.Usuario;
import interfaces.IHabitacion;
import habitaciones.HabDoble;
import habitaciones.HabSimple;
import habitaciones.Habitacion;

public class Recepcionista extends Persona implements IHabitacion{
	private int idEmpleado;
	private Usuario cuenta;
	
	public Recepcionista(String nombre, String dni, String origen, String direccion, int id/*, Usuario cuenta*/){
		super(nombre, dni, origen, direccion);
		this.idEmpleado=id;
	//	this.cuenta=cuenta;
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
	public void mostrarHabDisponibles(ArrayList<Habitacion> list,ArrayList<Integer> habDisponibles)
	{
		int aux=list.size();
		for(int i=0;i<aux;i++) {
			Habitacion auxHab=list.get(i);
			if(auxHab.getNroHabitacion()==habDisponibles.get(i))
			{
				auxHab.mostrarHab();
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
	public int hacerUnaReserva(ArrayList<Habitacion> habitaciones,ArrayList<Reserva> reservas,Date fecha_inic,Date fecha_fin,int idCliente,int cantPersonas)
	{
		ArrayList<Habitacion> habReserva= new ArrayList<Habitacion>();
		habReserva=elegirHabitaciones(habitaciones, reservas, cantPersonas, fecha_inic, fecha_fin);
		System.out.println("Habitaciones elegidas");
		for(int x=0;x<habReserva.size();x++) {
			System.out.println(habReserva.get(x).getNroHabitacion());
		}
		Reserva auxRes = new Reserva(fecha_inic,fecha_fin,habReserva,idCliente,reservas.size()+1);
		auxRes.mostrarReserva();
		reservas.add(auxRes);
		return auxRes.getIdReserva();
	}
	/**
	 * Crea un ArrayList con las habitaciones que va a contener la nueva reserva y lo retorna. 
	 * @param habitaciones : es un array que contiene las habitaciones.
	 * @param cantPersonas : cantidad de personas que se desean alojar en el Hotel.
	 * @param reservas : array de reservas
	 * @param fecha_inic : fecha en el que inicia la Reserva.
	 * @param fecha_fin : fecha en que se finaliza la Reserva.
	 * @return un ArrayList con las habitaciones que va a contener la Reserva.
	 */
	public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitacion> habitaciones,ArrayList<Reserva> reservas,int cantPersonas,Date fecha_inic,Date fecha_fin)
	{
		int i=0;
		Habitacion aux;
		ArrayList<Habitacion> auxHabreservadas=new ArrayList<Habitacion>();
		ArrayList<Habitacion> auxHab=new ArrayList<Habitacion>();
		auxHab=buscarDisponibles(reservas, habitaciones, fecha_inic, fecha_fin);
		Scanner scan = new Scanner(System.in);
		int disponiblidad=capacidadDisp(auxHab);
		System.out.println("Disponibilidad total:"+disponiblidad);
		if(disponiblidad>=cantPersonas)
		{
			while(i<cantPersonas)
			{
				System.out.println("Habitaciones disponibles");
				int op=0;
				for(int x=0;x<auxHab.size();x++) {
					System.out.print("Nro: "+auxHab.get(x).getNroHabitacion());
					System.out.println(" Tipo: "+auxHab.get(x).getClass().getSimpleName());
				}
				System.out.println("\n1. Habitacion Simple");
				System.out.println("2. Habitacion doble");
				System.out.println("\nPersonas alojadas:"+i+"  Faltan:"+(cantPersonas-i));
				System.out.print("Ingrese el tipo de Habitacion que desea: ");
				op=scan.nextInt();
				System.out.println();
				switch(op)
				{
					case 1:
							aux=haySimple(auxHab);
							if(aux!=null) {
								auxHabreservadas.add(aux);
								auxHab.remove(aux);
								i+=aux.getCapacidad();
							}
							else
								System.out.println("No hay habitaciones simples");
							break;
					case 2:
							aux=hayDoble(auxHab);
							if(aux!=null) {
								auxHabreservadas.add(aux);
								auxHab.remove(aux);
								i+=aux.getCapacidad();
							}
							else 
								System.out.println("No hay habitaciones dobles");
							break;
					default:
							System.out.println("Elegi bien pelotudo");
				}
	
			}
		}
		else System.out.println("No hay disponibilidad suficiente para alojar esa cantiadad.");
		scan.close();
		return auxHabreservadas;
	}
	/**
	 * Calcula la capacidad disponible que tendra el Hotel para las fechas ingresadas
	 * @param habitacionesDisp : array con las habitaciones disponibles.
	 * @return x : capacidad total del hotel.
	 */
	public int capacidadDisp(ArrayList<Habitacion> habitacionesDisp)
	{
		int x=0;
		for(int i=0;i<habitacionesDisp.size();i++)
		{
			Habitacion aux=habitacionesDisp.get(i);
			x+=aux.getCapacidad();
		}
		return x;
	}
	/**
	 * Retorna el Nro de las Habitaciones que podran ser reservadas para las nuevas fechas ingresadas.
	 * @param reservas : ArrayList de las Reservas. 
	 * @param habitaciones : ArrayList con las Habitaciones.
	 * @param fecha_inic : La nueva fecha de inicio de una nueva Reserva.
	 * @param fecha_fin : La nueva fecha de fin de una nueva Reserva.
	 * @return ArrayList de enteros con las habitaciones disponibles para las fechas ingresadas.
	 */
	public ArrayList<Habitacion> buscarDisponibles(ArrayList<Reserva> reservas,ArrayList<Habitacion> habitaciones,Date fecha_inic,Date fecha_fin)
	{
		int i=0;
		Date fechaActual = new Date(); //lo inicializa con la fecha actual.
		ArrayList<Habitacion> auxHab = new ArrayList<Habitacion>(habitaciones);
		Reserva auxReserva=null;
		ArrayList<Habitacion> auxEliminados = new ArrayList<Habitacion>();
	 
		for(i=0;i<reservas.size();i++) //recorre el array de reservas
		{
			auxReserva = reservas.get(i);
			if(!((fecha_inic.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_fin.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_inic.compareTo(fechaActual) >= 0))) //si el periodo ingresado NO se encuentra antes al que contiene la reserva y despues de el dia de la fecha
			{
				if(!(fecha_inic.compareTo(auxReserva.getFechaSalida()) > 0)) //si el periodo ingresado NO se encuentra despues de el de la reserva
				{
					//si llega a esta iteracion significa que la NUEVA reserva NO se podra realizar por que las fechas ingresadas coinciden con las existentes.
					for(int y=0;y<auxReserva.getHabitacionesReserva().size();y++) //recorre las habitaciones que contiene la reserva
					{
						if(!estaEliminado(auxEliminados, auxReserva.getHabitacionesReserva().get(y))) //si la habitacion NO ha sido agregada al array "auxEliminados" que contiene las habitaciones que no van a poder ser reservadas
						{
							auxEliminados.add(auxReserva.getHabitacionesReserva().get(y)); //primero agrega el nro de la hab al array elminados para que al querer elimnar una habitacion que posiblemente ha sido eliminada no se rompa.(podria ser una execpcion?)
							auxHab.remove(auxReserva.getHabitacionesReserva().get(y)); //remueve el nro de habitacion para luego retornar las disponibles para la reserva
						}
					}
				}
			}
		}
		return auxHab;
	}

	
	/**
	 * Su funcionalidad consiste en verificar si un Nro de Habitacion ha sido eliminado o no para asi si una habitacion tiene mas de una reserva no se intente eliminar dos veces y rompa el programa.
	 * @param auxEliminados : Array con las habitaciones que ya han sido elimnadas..
	 * @param nroHabitacion : numero de la Habitacion a verificar
	 * @return retorna True si la Habitacion ya ha sido eliminada del array
	 */
 	public boolean estaEliminado(ArrayList<Habitacion> auxEliminados, Habitacion h)
 	{
 		boolean rta=false;
	 	int auxHab;
	 	for(int i=0;i<auxEliminados.size();i++)
	 	{
	 		auxHab = auxEliminados.get(i).getNroHabitacion();
	 		if(auxHab == h.getNroHabitacion())
	 		{
	 			rta=true;
	 		}
	 	}
	 	return rta;
 	}
 	/**
 	 * Retorna una habitacion Simple si es que hay disponible.
 	 * @param habitaciones : habitaciones disponibles para la reserva.
 	 * @return aux : la Habitacion, o null si no hay disponible.
 	 */
 	public Habitacion haySimple(ArrayList<Habitacion> habitaciones) {
 		Habitacion aux=null;
 		for(int i=0;i<habitaciones.size();i++) {
 			if(habitaciones.get(i) instanceof HabSimple) {
 				aux=habitaciones.get(i);
 			}
 		}
 		return aux;
 	}
 	/**
 	 * Retorna una habitacion Doble si es que hay disponible.
 	 * @param habitaciones : habitaciones disponibles para la reserva.
 	 * @return aux : la Habitacion, o null si no hay disponible.
 	 */
 	public Habitacion hayDoble(ArrayList<Habitacion> habitaciones) {
 		Habitacion aux=null;
 		for(int i=0;i<habitaciones.size();i++) {
 			if(habitaciones.get(i) instanceof HabDoble) {
 				aux=habitaciones.get(i);
 			}
 		}
 		return aux;
 	}


 	public void checkIn(ArrayList<Reserva>reservas, int idReserva) {
 		for(int i=0;i<reservas.size();i++) {
 			if(idReserva==reservas.get(i).getIdReserva()) {
 				reservas.get(i).setFechaOcupacion();
 			}
 		}
 	}
 	
 	
}
