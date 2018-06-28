package interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import clases.Reserva;
import consumos.Consumo;
import habitaciones.Habitacion;
import persona.Cliente;

public interface IAdministracion {

	public void cargarCliente(ArrayList<Cliente> clientes, Cliente c)throws IOException;
	public Consumo buscarConsumo(ArrayList<Consumo> consumos, int idConsumo);
	public void realizarConsumo(ArrayList<Reserva> reservas, int idReserva, Consumo c)throws IOException;
	public void cancelarReserva(int idReserva, ArrayList<Reserva> reservas);
	public float checkOut(ArrayList<Reserva> reservas, int idReserva)throws IOException;
	public void checkIn(ArrayList<Reserva>reservas, int idReserva)throws IOException;
	public Habitacion hayDoble(ArrayList<Habitacion> habitaciones);
	public Habitacion haySimple(ArrayList<Habitacion> habitaciones);
	public boolean estaEliminado(ArrayList<Habitacion> auxEliminados, Habitacion h);
	public int capacidadDisp(ArrayList<Habitacion> habitacionesDisp);
	public ArrayList<Habitacion> buscarDisponibles(ArrayList<Reserva> reservas,ArrayList<Habitacion> habitaciones,Date fecha_inic,Date fecha_fin);
	public ArrayList<Habitacion> elegirHabitaciones(ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas, int cantPersonas);
	public int hacerReserva(ArrayList<Habitacion> habitaciones, ArrayList<Reserva> reservas, Date fecha_inic, Date fecha_fin, int idCliente);
	public void mostrarHabitaciones(ArrayList<Habitacion> list);
}
