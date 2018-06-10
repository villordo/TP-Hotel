package persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import clases.Reserva;
import clases.Usuario;
import interfaces.IHabitacion;
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
	public int hacerUnaReserva(ArrayList<Habitacion> habitaciones,ArrayList<Reserva> resrvas,Date fecha_inic,Date fecha_fin,int idCliente)
	{
		return 2;
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
	public ArrayList<Integer> elegirHabitaciones(ArrayList<Habitacion> habitaciones,ArrayList<Reserva> reservas,int cantPersonas,Date fecha_inic,Date fecha_fin)
	{
		int i=0;
		ArrayList<Integer> auxHabreservadas=new ArrayList<Integer>();
		ArrayList<Integer> auxHab=new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		while(i<=cantPersonas)
		{
			
			int capacidad;
			auxHab=buscarDisponibles(reservas, habitaciones, fecha_inic, fecha_fin);
			mostrarHabDisponibles(habitaciones,auxHab); //no deberia mostrar las habitaciones disponibles si la seleccion es ramdom
			System.out.println("Ingrese el tipo de Habitacion que desea");
			System.out.println("1-Habitacion Simple");
			System.out.println("2-Habitacion doble");
			capacidad=scan.nextInt();
			if(capacidad==1)
			{
				capacidad=2;
			}
			else
			{
				capacidad=4;
			}
			for(int x=0;i<habitaciones.size();x++) { //recorre las habitaciones para agregar la primer habitacion simple o doble que encuentre segun la opcion del usuario
				Habitacion aux = habitaciones.get(x);
				for(int y=0;i<auxHab.size();y++)
				{	
					if(aux.getNroHabitacion()==auxHab.get(y)) //si coincide el numero de la habitacion con uno de los que se encuentra en el auxiliar (que contiene los nros de las hab que estan disponibles para la fecha ingresada)
					{
						if(capacidad==aux.getCapacidad()) // si la capacidad coincide,es decir, simple o doble(segun la opcion elegida)
						{
							auxHabreservadas.add(auxHab.get(y));//agrega las habitaciones seleccionadas para la reserva.
						}
					}
				}
			}
			i++;
		}
		scan.close();
		return auxHabreservadas;
	}
	
	/**
	 * Retorna el Nro de las Habitaciones que podran ser reservadas para las nuevas fechas ingresadas.
	 * @param reservas : ArrayList de las Reservas. 
	 * @param habitaciones : ArrayList con las Habitaciones.
	 * @param fecha_inic : La nueva fecha de inicio de una nueva Reserva.
	 * @param fecha_fin : La nueva fecha de fin de una nueva Reserva.
	 * @return ArrayList de enteros con las habitaciones disponibles para las fechas ingresadas.
	 */
	public ArrayList<Integer> buscarDisponibles(ArrayList<Reserva> reservas,ArrayList<Habitacion> habitaciones,Date fecha_inic,Date fecha_fin)
	{
		int i=0;
		Date fechaActual = new Date(); //lo inicializa con la fecha actual.
		ArrayList<Integer> auxHab = new ArrayList<Integer>();
		auxHab=crearArrayDeNrosHab(auxHab, habitaciones);  //aca crea un array(de enteros) auxiliar con los nuemero de las habitaciones.Para luego retornar las que quedan disponibles.
		Reserva auxReserva=null;
		ArrayList<Integer> auxEliminados = new ArrayList<Integer>();
	 
		for(i=0;i<reservas.size();i++) //recorre el array de reservas
		{
			auxReserva = reservas.get(i);
			if(!((fecha_inic.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_fin.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_inic.compareTo(fechaActual) >= 0))) //si el periodo ingresado NO se encuentra antes al que contiene la reserva y despues de el dia de la fecha
			{
				System.out.println("primer if");
				if(!(fecha_inic.compareTo(auxReserva.getFechaSalida()) > 0)) //si el periodo ingresado NO se encuentra despues de el de la reserva
				{
					System.out.println("2do if");
					//si llega a esta iteracion significa que la NUEVA reserva NO se podra realizar por que las fechas ingresadas coinciden con las existentes.
					for(int y=0;y<auxReserva.getHabitacionesReserva().size();y++) //recorre las habitaciones que contiene la reserva
					{
						if(!estaEliminado(auxEliminados, auxReserva.getHabitacionesReserva().get(y))) //si la habitacion NO ha sido agregada al array "auxEliminados" que contiene las habitaciones que no van a poder ser reservadas
						{
							System.out.println("Reserva:"+i);
							System.out.println("Habitacion a borrar: "+auxReserva.getHabitacionesReserva().get(y));
							for(int c=0;c<auxHab.size();c++) {
								System.out.println(auxHab.get(c));
							}
							auxEliminados.add(auxReserva.getHabitacionesReserva().get(y)); //primero agrega el nro de la hab al array elminados para que al querer elimnar una habitacion que posiblemente ha sido eliminada no se rompa.(podria ser una execpcion?)
							System.out.println("en la posicion: "+((auxReserva.getHabitacionesReserva().get(y))-1));
							auxHab.remove(auxReserva.getHabitacionesReserva().get(y)); //remueve el nro de habitacion para luego retornar las disponibles para la reserva
						}
					}
				}
			}
		}
		return auxHab;
	}
	/**
	 * Crea un Array de enteros con los Nros de Habitaciones existentes en el Hotel.
	 * @param auxHab : Auxiliar donde se van a agregas los nros de las habitaciones.
	 * @param habitaciones : Array que contiene el total de habitaciones de el Hotel.
	 * @return ArrayList de enteros con TODOS los numeros de las habitaciones existentes.
	 */
	public ArrayList<Integer> crearArrayDeNrosHab(ArrayList<Integer> auxHab,ArrayList<Habitacion> habitaciones)
	{
		for(int i=0;i<habitaciones.size();i++)       
		{
			auxHab.add(habitaciones.get(i).getNroHabitacion());
		}
		return auxHab;
	}
	
	/**
	 * Su funcionalidad consiste en verificar si un Nro de Habitacion ha sido eliminado o no para asi si una habitacion tiene mas de una reserva no se intente eliminar dos veces y rompa el programa.
	 * @param auxEliminados : Array con las habitaciones que ya han sido elimnadas..
	 * @param nroHabitacion : numero de la Habitacion a verificar
	 * @return retorna True si la Habitacion ya ha sido eliminada del array
	 */
 	public boolean estaEliminado(ArrayList<Integer> auxEliminados,int nroHabitacion)
 	{
 		boolean rta=false;
	 	int auxHab;
	 	for(int i=0;i<auxEliminados.size();i++)
	 	{
	 		auxHab = auxEliminados.get(i);
	 		if(auxHab == nroHabitacion)
	 		{
	 			rta=true;
	 		}
	 	}
	 	return rta;
 	}

}
