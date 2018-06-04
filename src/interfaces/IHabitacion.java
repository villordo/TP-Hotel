package interfaces;

import java.util.ArrayList;
import java.util.Date;
import clases.Reserva;
import persona.Cliente;
import habitaciones.Habitacion;

public interface IHabitacion {
	
	public void mostrarHabitaciones();
	public void mostrarHabDisponibles();
	public int hacerReserva(int idCliente, int cantPersonas, Date fechaEntrada, Date fechaSalida);
	public void cancelarReserva(int idReserva);
	public int checkIn(Reserva r); // Setea fecha de ocupacion.
	public float checkOut(Reserva r); // Archiva la reserva, la borra del array de reservas, y retorna la cuenta total.
	public boolean cargarCliente(Cliente c); // Recibe el cliente a cargar en el array de clientes del hotel.
	public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitacion> habitacionesDisponibles); // Recibe las habitaciones disponibles del hotel.
	

}
