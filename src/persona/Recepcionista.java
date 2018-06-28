package persona;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import clases.Reserva;
import clases.Usuario;
import consumos.Consumo;
import interfaces.IAdministracion;
import habitaciones.HabDoble;
import habitaciones.HabSimple;
import habitaciones.Habitacion;
import archivos.ArchivosUtility;

public class Recepcionista extends Persona implements IAdministracion{
	private int idEmpleado;
	private Usuario cuenta;
	
	public Recepcionista(String nombre, String dni, String origen, String direccion, int id, String usuario, String password){
		super(nombre, dni, origen, direccion);
		this.idEmpleado=id;
		cuenta = new Usuario(usuario, password, 1);
	}
	
	public String toString(){
		return "ID Empleado: "+idEmpleado+"\nNombre: "+nombre+"\nDNI: "+dni+"\nOrigen: "+origen+"\nDireccion: "+direccion;
	}
	public Usuario getCuenta()
 	{
 		return cuenta;
 	}
	/**
	 * Muestra las habitaciones del Hotel.
	 */
	public void mostrarHabitaciones(ArrayList<Habitacion> list)
	{
		int aux=list.size();
		for(int i=0;i<aux;i++) {
			System.out.println(list.get(i).toString());
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
	public int hacerReserva(ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas, Date fecha_inic, Date fecha_fin, int idCliente)
	{
		System.out.println("Habitaciones elegidas");
		for(int x=0;x<habitaciones.size();x++) {
			System.out.println(habitaciones.get(x).getNroHabitacion());
		}
		Reserva auxRes = new Reserva(fecha_inic, fecha_fin, habitaciones, idCliente, reservas.size()+1);
		reservas.add(auxRes);
		try{
			ArchivosUtility.escribir("reservas.dat", reservas);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return auxRes.getIdReserva();
	}
	
	/**
	 * Crea un ArrayList con las habitaciones que va a contener la nueva reserva y lo retorna. 
	 * @param habitaciones : es un array que contiene las habitaciones.
	 * @param cantPersonas : cantidad de personas que se desean alojar en el Hotel.
	 * @param reservas : array de reservas
	 * @return un ArrayList con las habitaciones que va a contener la Reserva.
	 */
	public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas, int cantPersonas)
	{
		int i=0;
		Habitacion aux;
		ArrayList<Habitacion> auxHabReservadas = new ArrayList<Habitacion>();
		Scanner scan = new Scanner(System.in);
		int disponiblidad=capacidadDisp(habitaciones);
		System.out.println("\nDisponibilidad total: "+disponiblidad);
		if(disponiblidad>=cantPersonas)
		{
			while(i<cantPersonas)
			{
				System.out.println("Habitaciones disponibles");
				int op=0;
				for(int x=0;x<habitaciones.size();x++) {
					System.out.print("Nro: "+habitaciones.get(x).getNroHabitacion());
					System.out.println(" Tipo: "+habitaciones.get(x).getClass().getSimpleName());
				}
				System.out.println("\n1. Habitacion Simple");
				System.out.println("2. Habitacion doble");
				System.out.println("\nPersonas alojadas: "+i+"  Faltan: "+(cantPersonas-i));
				System.out.print("Ingrese el tipo de Habitacion que desea: ");
				op=scan.nextInt();
				System.out.println();
				switch(op)
				{
					case 1:
							aux=haySimple(habitaciones);
							if(aux!=null) {
								auxHabReservadas.add(aux);
								habitaciones.remove(aux);
								i+=aux.getCapacidad();
							}
							else
								System.out.println("No hay habitaciones simples");
							break;
					case 2:
							aux=hayDoble(habitaciones);
							if(aux!=null) {
								auxHabReservadas.add(aux);
								habitaciones.remove(aux);
								i+=aux.getCapacidad();
							}
							else 
								System.out.println("No hay habitaciones dobles");
							break;
					default:
							System.out.println("La opcion no es valida.");
				}
	
			}
		}
		else {
			System.out.println("No hay disponibilidad suficiente para alojar "+cantPersonas+" personas.");
			auxHabReservadas=null;
		}
		scan.close();
		return auxHabReservadas;
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
		ArrayList<Habitacion> auxHab;
		auxHab = habitaciones;
		Reserva auxReserva=null;
		ArrayList<Habitacion> auxEliminados = new ArrayList<Habitacion>();
		for(i=0;i<reservas.size();i++) //recorre el array de reservas
		{
			auxReserva = reservas.get(i);
			if(!((fecha_inic.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_fin.compareTo(auxReserva.getFechaEntrada()) < 0) && (fecha_inic.compareTo(fechaActual) >= 0))){
				if(!(fecha_inic.compareTo(auxReserva.getFechaSalida()) > 0)) //si el periodo ingresado NO se encuentra despues de el de la reserva
				{
					//si llega hasta aca significa que la NUEVA reserva NO se podra realizar por que las fechas ingresadas coinciden con las existentes.
					for(int y=0;y<auxReserva.getHabitacionesReserva().size();y++) //recorre las habitaciones que contiene la reserva
					{
						//System.out.println("Habitaciones a eliminiar:");
						//System.out.println(auxReserva.getHabitacionesReserva().get(y).toString());
						if(!estaEliminado(auxEliminados, auxReserva.getHabitacionesReserva().get(y))) //si la habitacion NO ha sido agregada al array "auxEliminados" que contiene las habitaciones que no van a poder ser reservadas
						{
							auxEliminados.add(auxReserva.getHabitacionesReserva().get(y)); //primero agrega el nro de la hab al array elminados para que al querer elimnar una habitacion que posiblemente ha sido eliminada no se rompa.(podria ser una execpcion?)
							for(int c=0;c<auxHab.size();c++)
							{
								if(auxReserva.getHabitacionesReserva().get(y).getNroHabitacion()==auxHab.get(c).getNroHabitacion())
								{
								auxHab.remove(auxHab.get(c));
								}
							}
							//auxHab.remove(auxReserva.getHabitacionesReserva().get(y)); //remueve el nro de habitacion para luego retornar las disponibles para la reserva
						}
					}
				}
			}
		}
		
		return auxHab;
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

 	/**
 	 * Realiza el checkIn de una determinada reserva.
 	 * 
 	 * @param reservas : Recibe el arreglo de reservas
 	 * @param idReserva : Recibe el id. de la reserva que va a ser checkeada.
 	 */
 	public void checkIn(ArrayList<Reserva>reservas, int idReserva) throws IOException {
 		for(int i=0;i<reservas.size();i++) {
 			if(idReserva==reservas.get(i).getIdReserva()) {
 				reservas.get(i).setFechaOcupacion();
 				ArchivosUtility.escribir("reservas.dat", reservas);
 			}
 		}
 	}
 	
 	public float checkOut(ArrayList<Reserva> reservas, int idReserva)  throws IOException{
 		long milisegundos = 24 * 60 * 60 * 1000;
 		long dias;
 		float aux=0;
 		for(int i=0;i<reservas.size();i++) {
 			if(reservas.get(i).getIdReserva()==idReserva) {
 				dias = (reservas.get(i).getFechaSalida().getTime() - reservas.get(i).getFechaEntrada().getTime()) / milisegundos;
 				aux=(reservas.get(i).getCuentaHabitaciones())*dias;
 				aux+=(reservas.get(i).getCuentaConsumos());
 				Reserva auxR = reservas.get(i);
 				reservas.remove(reservas.get(i));
 				ArchivosUtility.escribir("reservas.dat", reservas);
 				ArchivosUtility.escribir("reservasArchivadas.dat", auxR);
 			}
 		}
 		return aux;
 	}
 	
 	public void cancelarReserva(int idReserva, ArrayList<Reserva> reservas) {
 		Reserva aux=null;
 		for(int i=0;i<reservas.size();i++) {
 			if(reservas.get(i).getIdReserva()==idReserva) {
 				aux=reservas.get(i);
 			}
 		}
 		reservas.remove(aux);
 	}
 	
 	public void realizarConsumo(ArrayList<Reserva> reservas, int idReserva, Consumo c) throws IOException
 	{
 		for(int i=0;i<reservas.size();i++) {
 			if(reservas.get(i).getIdReserva()==idReserva) {
 				reservas.get(i).agregarConsumo(c);
 				ArchivosUtility.escribir("reservas.dat", reservas);
 			}
 		}
 	}
 	
 	public Consumo buscarConsumo(ArrayList<Consumo> consumos, int idConsumo) {
 		Consumo aux=null;
 		for(int i=0;i<consumos.size();i++) {
 			if(consumos.get(i).getIdArticulo()==idConsumo) {
 				aux = consumos.get(i);
 			}
 		}
 		return aux;
 	}
 	
 	
 	public void cargarCliente(ArrayList<Cliente> clientes, Cliente c) throws IOException{
 		clientes.add(c);
 		ArchivosUtility.escribir("clientes", clientes);
 	}

 	
}
