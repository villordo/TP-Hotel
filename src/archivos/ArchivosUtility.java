package archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.Reserva;

public class ArchivosUtility {
	
	public static void escribir(String nombreArchivo, Object obj) throws IOException 
	{
		FileOutputStream bn = new FileOutputStream(nombreArchivo);
		ObjectOutputStream fobj = new ObjectOutputStream(bn);
		fobj.writeObject(obj);
		fobj.close();
	}
	
	public static Object leer(String nombreArchivo) throws IOException, ClassNotFoundException 
	{
		FileInputStream bn = new FileInputStream(nombreArchivo);
		ObjectInputStream fobj = new ObjectInputStream (bn);
		Object obj = fobj.readObject();	
		fobj.close();
		return obj;
	}
	
	public static <T> ArrayList<T> volcarArchivo(String nombreArchivo) throws IOException, ClassNotFoundException
	{
		FileInputStream bn = new FileInputStream(nombreArchivo);
		ObjectInputStream fobj = new ObjectInputStream(bn);
		ArrayList<T> obj =(ArrayList<T>) fobj.readObject();
		fobj.close();
		return obj;
	}
	
	public static void mostrarArchivo(String nombreArchivo) throws IOException, ClassNotFoundException{
		Object aux;
		FileInputStream bn = new FileInputStream(nombreArchivo);
		ObjectInputStream fobj = new ObjectInputStream (bn);
		aux = fobj.readObject();
		while(aux!=null) {
			System.out.print(aux.toString());
			aux = fobj.readObject();
		}
	}
}
