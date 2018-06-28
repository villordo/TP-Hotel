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
import java.util.Scanner;

import archivos.ArchivosUtility;
import clases.Reserva;
import consumos.Bebida;
import consumos.Comida;
import consumos.Consumo;

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
		Bebida b1 = new Bebida(4, "Coca Cola", 50);
		Bebida b2 = new Bebida(5, "Pepsi", 40);
		Bebida b3 = new Bebida(6, "Sprite", 50);
		
		/*hotel1.agregarConsumo(c1);
		hotel1.agregarConsumo(c2);
		hotel1.agregarConsumo(c3);
		hotel1.agregarConsumo(b1);
		hotel1.agregarConsumo(b2);
		hotel1.agregarConsumo(b3);
		
		try {
			ArchivosUtility.escribir("consumos.dat", hotel1.getConsumos());
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}*/
		
		boolean aux=false;
		try {
			aux=Login.login("admin","admn");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.getMessage();
		} finally {
			if(aux==true) {
				System.out.println("Usted se ha logeado correctamente");
			} else
				System.out.println("Usuario o contraseña incorrectos");
		}
		
		/*ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		ArrayList<Habitacion> habitaciones1 = new ArrayList<Habitacion>();
		ArrayList<Habitacion> habitaciones2 = new ArrayList<Habitacion>();
		
		hotel1.agregarHabitacion(habitacion1);
		hotel1.agregarHabitacion(habitacion2);
		hotel1.agregarHabitacion(habitacion3);
		hotel1.agregarHabitacion(habitacion4);
		
		
		habitaciones.add(habitacion1);
		habitaciones.add(habitacion2);
		
		habitaciones1.add(habitacion3);
		
		habitaciones2.add(habitacion4);
		habitaciones2.add(habitacion2);
		
		Date f1 = new Date(118, 5, 29);
		Date f2 = new Date(118, 6, 6);
		Date f3 = new Date(118, 6, 7);
		Date f4 = new Date(118, 6, 13);
		Date f5 = new Date(118, 6, 14);
		Date f6 = new Date(118, 6, 21);
		
		Reserva r1 = new Reserva(f1, f2, habitaciones, 1, 1);
		Reserva r2 = new Reserva(f3, f4, habitaciones1, 2, 2);
		Reserva r3 = new Reserva(f5, f6, habitaciones2, 3, 3);
		
		hotel1.agregarReserva(r1);
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
			hotel1.setHabitaciones(ArchivosUtility.<Habitacion>volcarArchivo("habitaciones.dat"));
			hotel1.setConsumos(ArchivosUtility.<Consumo>volcarArchivo("consumos.dat"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		hotel1.mostrarReservas();
		for(int i=0;i<hotel1.getConsumos().size();i++) {
			System.out.println(hotel1.getConsumos().get(i).toString());
		}
		/*try {
			ArchivosUtility.mostrarArchivo("reservas.dat");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}*/
		
		Recepcionista recepcionista1 = new Recepcionista("Jorge", "111111111", "Springfield", "Av. Siempreviva", 1, "Jorgito", "Jorgito");
		
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


