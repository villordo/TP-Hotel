package clases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Login {
	
	public static Usuario login(String usuario, String password, ArrayList<Usuario> usuarios) throws IOException, ClassNotFoundException{
		Usuario auxCuenta=null;
		
		for(int i=0;i<usuarios.size();i++) {
			if(usuario.equals(usuarios.get(i).getUsuario())) {
				if(password.equals(usuarios.get(i).getPassword())) {
					auxCuenta = usuarios.get(i);
				}
			}
		}
		return auxCuenta;
	}

}

