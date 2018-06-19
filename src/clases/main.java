package clases;
import habitaciones.Habitacion;
import clases.Hotel;
import habitaciones.HabSimple;
import habitaciones.HabDoble;
import persona.Recepcionista;

import java.util.ArrayList;
import java.util.Date;

import clases.Reserva;

public class main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hotel hotel1 = new Hotel("Hotelazo","Av. Siempre viva","0800222bija");
		
		HabSimple habitacion1 = new HabSimple(1,500,true,"simple");
		HabSimple habitacion2 = new HabSimple(2,500,true,"simple");
		HabDoble habitacion3 = new HabDoble(3,1000,true,"doble");
		HabDoble habitacion4 = new HabDoble(4,1000,true,"doble");
		
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
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
		
		Date f1 = new Date(118, 5, 18);
		Date f2 = new Date(118, 5, 25);
		Date f3 = new Date(118, 5, 30);
		Date f4 = new Date(118, 6, 5);
		Date f5 = new Date(118, 6, 6);
		Date f6 = new Date(118, 6, 13);
		
		Reserva r1 = new Reserva(f1, f2, habitaciones, 1, 1);
		Reserva r2 = new Reserva(f3, f4, habitaciones1, 2, 2);
		Reserva r3 = new Reserva(f5, f6, habitaciones2, 3, 3);
		
		
		hotel1.agregarReserva(r1);
		hotel1.agregarReserva(r2);
		hotel1.agregarReserva(r3);
		
		Recepcionista recepcionista1 = new Recepcionista("Jorge", "111111111", "Springfield", "Av. Siempreviva", 1);
		
		Date fE = new Date(118, 5, 26);
		Date fS = new Date(118, 6, 1);
			
		int rta=0;
		rta=recepcionista1.hacerUnaReserva(hotel1.getHabitaciones(),hotel1.getReservas(), fE, fS, 01, 8);
		
		recepcionista1.checkIn(hotel1.getReservas(),rta);		
	
	}

}
