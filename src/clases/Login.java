package clases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login {
	
	public static boolean login(String usuario, String password) throws IOException, ClassNotFoundException{
		Usuario obj;
		int flag=0;
		FileInputStream bn = new FileInputStream("usuarios.dat");
		ObjectInputStream fobj = new ObjectInputStream (bn);
		obj = (Usuario) fobj.readObject();
		
		while(flag==0 && obj!=null) {
			if(usuario.equals(obj.getUsuario())) {
				if(password.equals(obj.getPassword())) {
					flag=1;	
					return true;
				}
			}
			obj = (Usuario) fobj.readObject();
		}
		return false;
	}

}

