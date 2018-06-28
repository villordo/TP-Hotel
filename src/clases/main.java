package clases;
import habitaciones.Habitacion;
import clases.Hotel;
import habitaciones.HabSimple;
import habitaciones.HabDoble;
import persona.Administrador;
import persona.Cliente;
import persona.Recepcionista;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Hotel hotel1 = new Hotel("Hotel Samullordo","Av. Siempre viva","0800222HELP");
		
		try {
			hotel1.setReservas(ArchivosUtility.<Reserva>volcarArchivo("reservas.dat"));
			hotel1.setHabitaciones(ArchivosUtility.<Habitacion>volcarArchivo("habitaciones.dat"));
			hotel1.setConsumos(ArchivosUtility.<Consumo>volcarArchivo("consumos.dat"));
			hotel1.setRecepcionistas(ArchivosUtility.<Recepcionista>volcarArchivo("recepcionistas.dat"));
			hotel1.setUsuarios(ArchivosUtility.<Usuario>volcarArchivo("usuarios.dat"));
			hotel1.setAdministradores(ArchivosUtility.<Administrador>volcarArchivo("administradores.dat"));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 

		Scanner scan=new Scanner(System.in);
		Usuario auxCuenta=null;
		Recepcionista auxRecep=null;
		Administrador auxAdmin=null;
		ArrayList<Habitacion> habDisponibles;
		ArrayList<Habitacion> habElegidas;
		int opcion1=menuPrincipal();
		switch(opcion1)
		{
			case 1:
				String auxUsu;
				System.out.println("Ingrese usuario: ");
				auxUsu=scan.nextLine();
				System.out.println("Ingrese pass: ");
				String auxPass=scan.nextLine();

				
				try {
					auxCuenta=Login.login(auxUsu, auxPass, hotel1.getUsuarios());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(auxCuenta.getTipoCuenta()==1)
				{
					auxRecep=hotel1.buscarRecepcionista(auxCuenta, hotel1.getRecepcionistas());
					System.out.println("Mostrando recepcionista: \n"+auxRecep.toString());
					int opcion2=menuRecepcionista();
					switch(opcion2)
					{
						case 1:
									int opcion3;
									opcion3 = menuCliente();
									switch(opcion3)
									{
										case 1:
												System.out.println("Ingrese nombre y apellido: ");
												String auxNombre = scan.nextLine();
												System.out.println("Ingrese DNI: ");
												String dni = scan.nextLine();
												System.out.println("Ingrese origen: ");
												String origen = scan.nextLine();
												System.out.println("Ingrese direccion: ");
												String direccion = scan.nextLine();
												int auxIdCliente = hotel1.getClientes().size()+1;
												Cliente c = new Cliente(auxNombre, dni, origen, direccion, auxIdCliente);
												System.out.println("Su ID de cliente es: "+c.getIdCliente());
												try {
													auxRecep.cargarCliente(hotel1.getClientes(), c);
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												break;
										case 2:
												Scanner scanid = new Scanner(System.in);
												System.out.println("Ingrese ID Cliente: ");
												int auxid=scanid.nextInt();
												System.out.println("Ingrese la cantidad de personas que desea alojar:");
												int cant_pers=scanid.nextInt();
												Date fE=null;
												Date fS=null;
												SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
												System.out.println("Ingrese la fecha de ingreso: ");
												Scanner sc = new Scanner(System.in);
												String fecha = sc.nextLine();
												try {
													fE = sdf.parse(fecha);
													System.out.println("Ingrese fecha de salida: ");
													fS = sdf.parse(sc.nextLine());
												} catch (ParseException e) {
													e.printStackTrace();
												}
												habDisponibles = auxRecep.buscarDisponibles(hotel1.getReservas(), hotel1.getHabitaciones(), fE, fS);		
												habElegidas = auxRecep.elegirHabitaciones(habDisponibles, hotel1.getReservas(), cant_pers);
												if(habElegidas!=null) {
													int rta=0;
													rta=auxRecep.hacerReserva(habElegidas, hotel1.getReservas(), fE, fS,auxid);
												}
												break;
									}
							break;
						case 2:
								Scanner scanEliminar = new Scanner(System.in);
								System.out.println("Ingrese ID de reserva a cancelar: ");
								int aux = scanEliminar.nextInt();
								auxRecep.cancelarReserva(aux, hotel1.getReservas());
								try {
									ArchivosUtility.escribir("reservas.dat", hotel1.getReservas());
								} catch (IOException e) {
									e.printStackTrace();
								}
							break;
						case 3:
							   Scanner scanCheckIn = new Scanner(System.in);
							   System.out.println("Ingrese ID de reserva para hacer check in: ");
							   int auxScanCheckIn = scanCheckIn.nextInt();
							   try {
								   auxRecep.checkIn(hotel1.getReservas(), auxScanCheckIn);
							   } catch (IOException e) {
								   e.printStackTrace();
							   }
							break;
						case 4:
							  Scanner scanCheckOut = new Scanner(System.in);
							  System.out.println("Ingrese ID de reserva para hacer check out: ");
							  int auxScanCheckOut = scanCheckOut.nextInt();
							  try {
								  float cuentaTotal = auxRecep.checkOut(hotel1.getReservas(), auxScanCheckOut);
								  System.out.println("La cuenta total es: "+cuentaTotal);
							  } catch (IOException e) {
								  e.printStackTrace();
							  }
							break;
						case 5: 
								hotel1.mostrarHabitaciones();
							break;
						case 6:
							 Scanner scanConsumo = new Scanner(System.in);
							 for(int i=0;i<hotel1.getConsumos().size();i++) {
								 System.out.println(hotel1.getConsumos().get(i).toString());
							 }
							 System.out.println("Elija una opcion(ID del articulo): ");
							 int scanConsumoAux = scanConsumo.nextInt();
							 Consumo auxC = auxRecep.buscarConsumo(hotel1.getConsumos(), scanConsumoAux);
							 System.out.println("Ingrese el ID de la reserva: ");
							 int scanReserva = scanConsumo.nextInt();
							 try {
								 auxRecep.realizarConsumo(hotel1.getReservas(), scanReserva, auxC);
							 } catch (IOException e) {
								 e.printStackTrace();
							 }
							break;
						case 7:
							 hotel1.mostrarReservas();
						case 0: 
							break;
					}
				}
				else {
					auxAdmin=hotel1.buscarAdmin(auxCuenta, hotel1.getAdministradores());
					System.out.println("Mostrando recepcionista: \n"+auxAdmin.toString());
					int opcion2=menuAdministrador();
					switch(opcion2)
					{
						case 1:
									int opcion3;
									opcion3 = menuCliente();
									switch(opcion3)
									{
										case 1:
												System.out.println("Ingrese nombre y apellido: ");
												String auxNombre = scan.nextLine();
												System.out.println("Ingrese DNI: ");
												String dni = scan.nextLine();
												System.out.println("Ingrese origen: ");
												String origen = scan.nextLine();
												System.out.println("Ingrese direccion: ");
												String direccion = scan.nextLine();
												int auxIdCliente = hotel1.getClientes().size()+1;
												Cliente c = new Cliente(auxNombre, dni, origen, direccion, auxIdCliente);
												System.out.println("Su ID de cliente es: "+c.getIdCliente());
												try {
												auxAdmin.cargarCliente(hotel1.getClientes(), c);
												} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
												}
													break;
										case 2:
												Scanner scanid = new Scanner(System.in);
												System.out.println("Ingrese ID Cliente: ");
												int auxid=scanid.nextInt();
												System.out.println("Ingrese la cantidad de personas que desea alojar:");
												int cant_pers=scanid.nextInt();
												Date fE=null;
												Date fS=null;
												SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
												System.out.println("Ingrese la fecha de ingreso: ");
												Scanner sc = new Scanner(System.in);
												String fecha = sc.nextLine();
												try {
													fE = sdf.parse(fecha);
													System.out.println("Ingrese fecha de salida: ");
													fS = sdf.parse(sc.nextLine());
												} catch (ParseException e) {
													e.printStackTrace();
												}
												habDisponibles = auxAdmin.buscarDisponibles(hotel1.getReservas(), hotel1.getHabitaciones(), fE, fS);		
												habElegidas = auxRecep.elegirHabitaciones(habDisponibles, hotel1.getReservas(), cant_pers);
												if(habElegidas!=null) {
													int rta=0;
													rta=auxRecep.hacerReserva(habElegidas, hotel1.getReservas(), fE, fS,auxid);
												}
												break;
									}
							break;
						case 2:
								Scanner scanEliminar = new Scanner(System.in);
								System.out.println("Ingrese ID de reserva a cancelar: ");
								int aux = scanEliminar.nextInt();
								auxAdmin.cancelarReserva(aux, hotel1.getReservas());
								try {
									ArchivosUtility.escribir("reservas.dat", hotel1.getReservas());
								} catch (IOException e) {
									e.printStackTrace();
								}
							break;
						case 3:
							   Scanner scanCheckIn = new Scanner(System.in);
							   System.out.println("Ingrese ID de reserva para hacer check in: ");
							   int auxScanCheckIn = scanCheckIn.nextInt();
							   try {
								   auxAdmin.checkIn(hotel1.getReservas(), auxScanCheckIn);
							   } catch (IOException e) {
								   e.printStackTrace();
							   }
							break;
						case 4:
							  Scanner scanCheckOut = new Scanner(System.in);
							  System.out.println("Ingrese ID de reserva para hacer check out: ");
							  int auxScanCheckOut = scanCheckOut.nextInt();
							  try {
								  float cuentaTotal = auxAdmin.checkOut(hotel1.getReservas(), auxScanCheckOut);
								  System.out.println("La cuenta total es: "+cuentaTotal);
							  } catch (IOException e) {
								  e.printStackTrace();
							  }
							break;
						case 5: 
								hotel1.mostrarHabitaciones();
							break;
						case 6:
							 Scanner scanConsumo = new Scanner(System.in);
							 for(int i=0;i<hotel1.getConsumos().size();i++) {
								 System.out.println(hotel1.getConsumos().get(i).toString());
							 }
							 System.out.println("Elija una opcion(ID del articulo): ");
							 int scanConsumoAux = scanConsumo.nextInt();
							 Consumo auxC = auxAdmin.buscarConsumo(hotel1.getConsumos(), scanConsumoAux);
							 System.out.println("Ingrese el ID de la reserva: ");
							 int scanReserva = scanConsumo.nextInt();
							 try {
								 auxAdmin.realizarConsumo(hotel1.getReservas(), scanReserva, auxC);
							 } catch (IOException e) {
								 e.printStackTrace();
							 }
							break;
						case 7:
							 hotel1.mostrarReservas();
						case 0: 
							break;
					}
				}
				break;
			case 2:
				System.out.println("Adios");
				break;
		
		}
	
	}
	public static int menuPrincipal()
	{
		int opcion=0;
		Scanner scan3=new Scanner(System.in);
		System.out.println("------------HOTEL SAMULLORDO-----------");
		System.out.println("1-Ingresar");
		System.out.println("2-Salir");
		System.out.println("Ingrese opcion: ");
		opcion=scan3.nextInt();
		
		return opcion;
	}
	
	public static int menuRecepcionista()
	{
		int opcion=0;
		Scanner scan2=new Scanner(System.in);
		System.out.println("---------Recepcionista---------");
		System.out.println("1-Hacer una reserva.");
		System.out.println("2-Quitar reserva.");
		System.out.println("3-CheckIn.");
		System.out.println("4-CheckOut.");
		System.out.println("5-Mostrar Habitaciones.");
		System.out.println("6-Agregar consumo a una reserva.");
		System.out.println("7-Mostrar reservas.");
		System.out.println("Ingrese una opcion: ");
		opcion=scan2.nextInt();
		
		return opcion;
	}
	public static int menuAdministrador()
	{
		int opcion=0;
		Scanner scan2=new Scanner(System.in);
		System.out.println("---------Administrador---------");
		System.out.println("1-Hacer una reserva.");
		System.out.println("2-Quitar reserva.");
		System.out.println("3-CheckIn.");
		System.out.println("4-CheckOut.");
		System.out.println("5-Mostrar Habitaciones.");
		System.out.println("6-Agregar consumo a una reserva.");
		System.out.println("7-Mostrar reservas.");
		System.out.println("8-Agregar Recepcionista.");
		System.out.println("9-Agregar Administrador.");
		System.out.println("10-Agregar Cliente.");
		System.out.println("Ingrese una opcion: ");
		opcion=scan2.nextInt();
		
		return opcion;
	}
	public static int menuCliente()
	{
		int opcion=0;
		Scanner scan1=new Scanner(System.in);
		System.out.println("1-Si");
		System.out.println("2-Ingresar ID Cliente existente.");
		System.out.println("Ingrese una opcion.");
		opcion=scan1.nextInt();
		return opcion;
	}

}


