package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import consumos.Consumo;
import habitaciones.Habitacion;


public class Reserva implements Serializable{
	private Date fechaEntrada;
	private Date fechaOcupacion;
	private Date fechaSalida;
	private ArrayList<Habitacion> habitaciones;
	private ArrayList<Consumo> consumos;
	private int idCliente;
	private int idReserva;
	private float cuenta;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, ArrayList<Habitacion> habitaciones, int idCliente, int idReserva) {
		this.fechaEntrada=fechaEntrada;
		this.fechaOcupacion=null;
		this.fechaSalida=fechaSalida;
		this.habitaciones = new ArrayList<Habitacion>(habitaciones);
		consumos = new ArrayList<Consumo>();
		this.idCliente=idCliente;
		this.idReserva=idReserva;
	    cuenta=0;
	}
	
	
	/**
	 * Suma los precios de los consumos de la reserva.
	 * @return Total de los consumos de la reserva.
	 */
	public float getCuentaConsumos() {
		float aux=0;
		for(int i=0;i<consumos.size();i++) {
			aux+=consumos.get(i).getPrecio();
		}
		return aux;
	}
	
	/**
	 * Suma los precios de las habitaciones de la reserva.
	 * @return Total de las habitaciones de la reserva.
	 */
	
	public float getCuentaHabitaciones() {
		float aux=0;
		for(int i=0;i<habitaciones.size();i++)
		{
			aux+=habitaciones.get(i).getPrecio();
		}
		return aux;
	}
	
	/**
	 * Quita una habitacion del arreglo de habitaciones de la reserva
	 * @param nroHabitacion recibe el numero de la habitacion a quitar.
	 */
	public void quitarHabitacion(int nroHabitacion){ // controlada por bucle try/catch
		int aux=habitaciones.size(), i=0;
		Habitacion auxHab=null;
		for(i=0;i<aux;i++){
			auxHab = habitaciones.get(i);
			if(nroHabitacion==auxHab.getNroHabitacion()) habitaciones.remove(i);
		}
	}
	
	/**
	 * Suma los precios de los todos los consumos y todas las habitaciones de la reserva.
	 * @param reserva  Recibe la reserva del cliente correspondiente.
	 * @return Total de los consumos y las habitaciones de la reserva.
	 */
	
	public float getCuentaTotal(Reserva reserva) {
		cuenta=reserva.getCuentaConsumos()+reserva.getCuentaHabitaciones();
		return cuenta;	
	}
	
	
	/**
	 * Agrega un consumo al arreglo de consumos de la reserva.
	 * @param c Recibe el consumo a agregar.
	 */
	public void agregarConsumo(Consumo c) {
		consumos.add(c);
	}
	
	/**
	 * Agrega una habitacion al arreglo de habitaciones de la reserva.
	 * @param h Recibe la habitacion a agregar.
	 */
	public void agregarHabitacion(Habitacion h) {
		habitaciones.add(h);
	}
	
	/**
	 * Muestra las habitaciones de la reserva.
	 */
	public void mostrarHabitaciones(){
		int aux=habitaciones.size();
		for(int i=0;i<aux;i++) {
			habitaciones.get(i).toString();
		}
	}
	
	/**
	 * Muestra los consumos de la reserva
	 */
	public void mostrarConsumos(){
		int aux=consumos.size();
		 for(int i=0;i<aux;i++) {
			 consumos.get(i).toString();
		 }
	}
	
	/**
	 * Quita un consumo del arreglo de consumos de la reserva.
	 * @param idArticulo recibe el id del articulo a quitar.
	 */
	public void quitarConsumo(int idArticulo){
		int aux=consumos.size(), i=0, flag=0;
		Consumo auxConsumo=null;
		while(flag==0 && i<aux){
			auxConsumo=consumos.get(i);
			if(idArticulo==auxConsumo.getIdArticulo()){
				consumos.remove(i);
				flag=1;
			}
			i++;
		}
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public ArrayList<Habitacion> getHabitacionesReserva(){
		return habitaciones;
	}
	
	public int getIdReserva()
	{
		return idReserva;
	}
	
	public String toString()
	{
		System.out.println("idCliente: "+idCliente+"\nidReserva: "+idReserva+"\nFecha de inicio: "+fechaEntrada+"\nFecha salida: "+fechaSalida+"\nFecha Ocupacion: "+fechaOcupacion);
		for(int x=0;x<habitaciones.size();x++) {
			System.out.print("Nro: "+habitaciones.get(x).getNroHabitacion());
			System.out.println(" Tipo: "+habitaciones.get(x).getClass().getSimpleName());
		}
		System.out.println("\n");
		return "";
	}
	
	public void setFechaOcupacion() {
		fechaOcupacion = new Date();
	}
	
}
