package clases;
import habitaciones.Habitacion;
import clases.Hotel;
import habitaciones.HabSimple;
import habitaciones.HabDoble;
import persona.Recepcionista;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

import archivos.ArchivosUtility;
import clases.Reserva;
import consumos.Bebida;
import consumos.Comida;

public class main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hotel hotel1 = new Hotel("Hotelazo","Av. Siempre viva","0800222bija");
		
		HabSimple habitacion1 = new HabSimple(1,500,true,"simple");
		HabSimple habitacion2 = new HabSimple(2,500,true,"simple");
		HabDoble habitacion3 = new HabDoble(3,1000,true,"doble");
		HabDoble habitacion4 = new HabDoble(4,1000,true,"doble");
		HabDoble habitacion5 = new HabDoble(3,1000,true,"doble");
		HabDoble habitacion6 = new HabDoble(4,1000,true,"doble");
		HabDoble habitacion7 = new HabDoble(3,1000,true,"doble");
		HabSimple habitacion8 = new HabSimple(1,500,true,"simple");
		HabSimple habitacion9 = new HabSimple(2,500,true,"simple");
		HabSimple habitacion10 = new HabSimple(1,500,true,"simple");
		
		Comida c1 = new Comida(1, "Arroz con queso", 40);
		Comida c2 = new Comida(2, "Milanesa con papas", 70);
		Comida c3 = new Comida(3, "Pollo al horno con papas", 95);
		Bebida b1 = new Bebida(1, "Coca Cola", 50);
		Bebida b2 = new Bebida(1, "Pepsi", 40);
		Bebida b3 = new Bebida(1, "Sprite", 50);
		
		hotel1.agregarConsumo(c1);
		hotel1.agregarConsumo(c2);
		hotel1.agregarConsumo(c3);
		hotel1.agregarConsumo(b1);
		hotel1.agregarConsumo(b2);
		hotel1.agregarConsumo(b3);
		
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		ArrayList<Habitacion> habitaciones1 = new ArrayList<Habitacion>();
		ArrayList<Habitacion> habitaciones2 = new ArrayList<Habitacion>();
		
		hotel1.agregarHabitacion(habitacion1);
		hotel1.agregarHabitacion(habitacion2);
		hotel1.agregarHabitacion(habitacion3);
		hotel1.agregarHabitacion(habitacion4);
		
		
		
		
		/*hotel1.agregarReserva(r1);
		hotel1.agregarReserva(r2);
		hotel1.agregarReserva(r3);
		try {
			ArchivosUtility.escribir("reservas.dat", hotel1.getReservas());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		
		try {
			hotel1.setReservas(ArchivosUtility.<Reserva>volcarArchivo("reservas.dat"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		hotel1.mostrarReservas();
		/*try {
			ArchivosUtility.mostrarArchivo("reservas.dat");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}*/
		
		Recepcionista recepcionista1 = new Recepcionista("Jorge", "111111111", "Springfield", "Av. Siempreviva", 1);
		
		Date fE = new Date(118, 6, 15);
		Date fS = new Date(118, 6, 18);
		
		ArrayList<Habitacion> habDisponibles = recepcionista1.buscarDisponibles(hotel1.getReservas(), hotel1.getHabitaciones(), fE, fS);
		ArrayList<Habitacion> habElegidas = recepcionista1.elegirHabitaciones(habDisponibles, hotel1.getReservas(), 11);
		if(habElegidas!=null) {
			int rta=0;
			rta=recepcionista1.hacerReserva(habElegidas, hotel1.getReservas(), fE, fS, 1);
			try {
				recepcionista1.checkIn(hotel1.getReservas(), rta);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	//	hotel1.mostrarReservas();
		
	/*	try {
			float auxCuenta = recepcionista1.checkOut(hotel1.getReservas(), 2);
			System.out.println("Cuenta total: "+auxCuenta);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
	
	}
	

}


