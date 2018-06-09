package clases;
import habitaciones.Habitacion;
import habitaciones.HabSimple;
import habitaciones.HabDoble;
import interfaces.IHabitacion;
import persona.Persona;
import persona.Recepcionista;

import java.util.ArrayList;
import java.util.Date;

import clases.Reserva;

public class main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HabSimple habitacion1 = new HabSimple(1,500,true,"simple");
		HabSimple habitacion2 = new HabSimple(2,500,true,"simple");
		HabDoble habitacion3 = new HabDoble(3,1000,true,"doble");
		HabDoble habitacion4 = new HabDoble(4,1000,true,"doble");
		
		ArrayList<Integer> habitaciones = new ArrayList<Integer>();
		ArrayList<Integer> habitaciones1 = new ArrayList<Integer>();
		ArrayList<Integer> habitaciones2 = new ArrayList<Integer>();
		ArrayList<Habitacion> habitacionesTotales = new ArrayList<Habitacion>();
		
		habitacionesTotales.add(habitacion1);
		habitacionesTotales.add(habitacion2);
		habitacionesTotales.add(habitacion3);
		habitacionesTotales.add(habitacion4);
		
		
		habitaciones.add(habitacion1.getNroHabitacion());
		habitaciones.add(habitacion2.getNroHabitacion());
		
		habitaciones1.add(habitacion3.getNroHabitacion());
		
		habitaciones2.add(habitacion4.getNroHabitacion());
		habitaciones2.add(habitacion2.getNroHabitacion());
		
		Date f1 = new Date(118, 5, 7);
		Date f2 = new Date(118, 5, 15);
		Date f3 = new Date(118, 5, 20);
		Date f4 = new Date(118, 5, 25);
		Date f5 = new Date(118, 5, 16);
		Date f6 = new Date(118, 5, 19);
		
		Reserva r1 = new Reserva(f1, f2, habitaciones, 1, 1);
		Reserva r2 = new Reserva(f3, f4, habitaciones1, 2, 2);
		Reserva r3 = new Reserva(f5, f6, habitaciones2, 3, 3);
		
		
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		
		reservas.add(r1);
		reservas.add(r2);
		reservas.add(r3);
		
		Recepcionista recepcionista1 = new Recepcionista("Jorge", "111111111", "Springfield", "Av. Siempreviva", 1);
		
		ArrayList<Integer> auxHab = new ArrayList<Integer>();
		
		Date fE = new Date(118, 5, 8);
		Date fS = new Date(118, 5, 15);
		
		auxHab = recepcionista1.buscarDisponibles(reservas, habitacionesTotales, fE, fS);
		
		System.out.println("Habitaciones disponibles");
		for(int i=0;i<auxHab.size();i++) {
			System.out.println(auxHab.get(i));
		}
		
	
	}

}
