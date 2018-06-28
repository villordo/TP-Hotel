package consumos;

public class Comida extends Consumo {
	
	public Comida(int idArticulo, String nombre, float precio)
	{
		super(idArticulo,nombre,precio);
	}
	
	public String toString()
	{
		return "\nId Articulo: "+getIdArticulo()+"\nNombre: "+getNombre()+"\nPrecio: "+getPrecio();
	}
}
