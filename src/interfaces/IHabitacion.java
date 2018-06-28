package interfaces;

import java.util.ArrayList;
import java.util.Date;
import clases.Reserva;
import habitaciones.Habitacion;
import persona.Cliente;

public interface IHabitacion {
	
	public void mostrarHabitaciones(ArrayList<Habitacion> list);
	
	//public void cancelarReserva(int idReserva);
	//public void checkIn(ArrayList<Reserva>reservas, int idReserva); // Setea fecha de ocupacion.
	//public float checkOut(Reserva r); // Archiva la reserva, la borra del array de reservas, y retorna la cuenta total.
	//public boolean cargarCliente(Cliente c); // Recibe el cliente a cargar en el array de clientes del hotel.
	//public int hacerReserva(int idCliente, int cantPersonas, Date fechaEntrada, Date fechaSalida);
	//public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitacion> habitacionesDisponibles); // Recibe las habitaciones disponibles del hotel.
	//public ArrayList<Habitacion> buscarDisponibles(ArrayList<Reserva> reservas,ArrayList<Habitacion> habitaciones,Date fecha_inic,Date fecha_fin);
	//public boolean estaEliminado(ArrayList<Habitacion> auxEliminados, Habitacion h);
	//public Habitacion haySimple(ArrayList<Habitacion> habitaciones);
	//public Habitacion hayDoble(ArrayList<Habitacion> habitaciones);
	//public int capacidadDisp(ArrayList<Habitacion> habitacionesDisp);
}
