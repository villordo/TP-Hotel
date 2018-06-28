package clases;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String usuario;
	private String password;
	private int tipoCuenta; ///0: Admin, 1: Recepcionista
	
	public Usuario(String usuario, String password, int tipoCuenta){
		this.usuario=usuario;
		this.password=password;
		this.tipoCuenta=tipoCuenta;
	}
	
	public String getUsuario(){
		return usuario;
	}
	
	public String getPassword(){
		return password;
	}
	
	public int getTipoCuenta() {
		return tipoCuenta;
	}
}
