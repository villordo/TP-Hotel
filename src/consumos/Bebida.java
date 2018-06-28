package consumos;

public class Bebida extends Consumo {
	
	public Bebida(int idArticulo, String nombre, float precio)
	{
		super(idArticulo,nombre,precio);
	}
	
	public String toString()
	{
		return "\nId Articulo: "+getIdArticulo()+"\nNombre: "+getNombre()+"\nPrecio: "+getPrecio();
	}
}
