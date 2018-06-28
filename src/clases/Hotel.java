package clases;

import java.util.ArrayList;
import java.util.Date;

import consumos.Consumo;
import habitaciones.Habitacion;
import persona.Administrador;
import persona.Cliente;
import persona.Recepcionista;

	public class Hotel {
		String nombre;
		String direccion;
		String telefono;
		ArrayList <Cliente> clientes;
		ArrayList <Usuario> usuarios;
		ArrayList <Habitacion> habitaciones;
		ArrayList <Reserva> reservas;
		ArrayList <Consumo> consumos;
		ArrayList <Recepcionista> recepcionistas;
		ArrayList <Administrador> admins;
		Login login;
	
	
	public Hotel(String nombre, String direccion, String telefono)
	{
		this.nombre=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
		clientes = new ArrayList<Cliente>();
		usuarios = new ArrayList<Usuario>();
		habitaciones = new ArrayList<Habitacion>();
		reservas = new ArrayList<Reserva>();
		consumos = new ArrayList<Consumo>();
		recepcionistas= new ArrayList<Recepcionista>();
		admins=new ArrayList<Administrador>();
		login = null;
	}
	/**
	 * Agrega un Cliente en el arreglo.
	 * @param c Recibe el Cliente a agregar.
	 */
	public void agregarCliente(Cliente c)
	{
		clientes.add(c);
	}
	/**
	 * Agrega una Reserva al arreglo.
	 * @param r Recibe la Reserva a agregar.
	 */
	public void agregarReserva(Reserva r)
	{
		reservas.add(r);
	}
	/**
	 * Agrega una Habitacion al arreglo.
	 * @param h Recibe la Habitacion a agregar.
	 */
	public void agregarHabitacion(Habitacion h)
	{
		habitaciones.add(h);
	}
	/**
	 * Agrega un Usuario al arreglo.
	 * @param u Recibe el Usuario a agregar.
	 */
	public void agregarUsuario(Usuario u)
	{
		usuarios.add(u);
	}
	
	public void agregarConsumo(Consumo c) {
		consumos.add(c);
	}
	/**
	 * Muestra los clientes existentes.
	 */
	public void mostrarClientes()
	{
		int i=0;
		int aux=clientes.size();
		for(i=0;i<aux;i++)
		{
			clientes.get(i).toString();
		}
	}
	/**
	 * Muestra las habitaciones del hotel.
	 */
	public void mostrarHabitaciones()
	{
		int i=0;
		int aux=habitaciones.size();
		for(i=0;i<aux;i++)
		{
			System.out.println(habitaciones.get(i).toString());
		}
	}
	/**
	 * Muestra las reservas vigentes.
	 */
	public void mostrarReservas()
	{
		int i=0;
		int aux=reservas.size();
		for(i=0;i<aux;i++)
		{
			System.out.println(reservas.get(i).toString());
		}
	}
	/**
	 * Muestra los usuarios del sistema.
	 */
	public void mostrarUsuarios()
	{
		int i=0;
		int aux=usuarios.size();
		for(i=0;i<aux;i++)
		{
			usuarios.get(i).toString();
		}
	}
	public ArrayList<Reserva> getReservas(){
		return reservas;
	}
	public ArrayList<Recepcionista> getRecepcionistas()
	{
		return recepcionistas;
	}
	public void setReservas(ArrayList<Reserva> aux){
		reservas = aux;
	}
	public void setHabitaciones(ArrayList<Habitacion> aux){
		habitaciones = aux;
	}
	public ArrayList<Habitacion> getHabitaciones(){
		return habitaciones;
	}
	public ArrayList<Consumo> getConsumos(){
		return consumos;
	}
	public void setConsumos(ArrayList<Consumo> c) {
		consumos = c;
	}
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> u) {
		usuarios = u;
	}
	public ArrayList<Cliente> getClientes(){
		return clientes;
	}
	public void agregarRecepcionista(Recepcionista r) {
		recepcionistas.add(r);
	}
	public void setRecepcionistas(ArrayList<Recepcionista> recepcionistas) {
		this.recepcionistas = recepcionistas;
	}
	public ArrayList<Administrador> getAdministradores(){
		return admins;
	}
	public void agregarAdministrador(Administrador a){
		admins.add(a);
	}
	public void setAdministradores(ArrayList<Administrador> admins) {
		this.admins=admins;
	}
	
	public Recepcionista buscarRecepcionista(Usuario usuario,ArrayList <Recepcionista> recepcionistas)
	{
		Recepcionista aux=null;
		for(int i=0;i<recepcionistas.size();i++)
		{
			String aux1 = recepcionistas.get(i).getCuenta().getUsuario();
			String aux2 = usuario.getUsuario();
			if(aux1.equals(aux2))
			{
				String aux3 = recepcionistas.get(i).getCuenta().getPassword();
				String aux4 = usuario.getPassword();
				if(aux3.equals(aux4))
				{
					aux=recepcionistas.get(i);
				}
			}
		}
		return aux;
	}
	public Administrador buscarAdmin(Usuario usuario,ArrayList <Administrador> admins)
	{
		Administrador aux=null;
		for(int i=0;i<admins.size();i++)
		{
			String aux1 = admins.get(i).getCuenta().getUsuario();
			String aux2 = usuario.getUsuario();
			if(aux1.equals(aux2))
			{
				String aux3 = admins.get(i).getCuenta().getPassword();
				String aux4 = usuario.getPassword();
				if(aux3.equals(aux4))
				{
					aux=admins.get(i);
				}
			}
		}
		return aux;
	}
}