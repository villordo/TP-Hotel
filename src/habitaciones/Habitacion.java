package habitaciones;

public abstract class Habitacion {
	protected int nroHabitacion;
	protected float precio;
	protected int capacidad;
	protected boolean estado;
	protected String descripcion;


	public Habitacion(int nroHabitacion, float precio, int capacidad, boolean estado, String descripcion)
	{
		this.nroHabitacion = nroHabitacion;
		this.precio = precio;
		this.capacidad = capacidad;
		this.estado = estado;
		this.descripcion = descripcion;
	}

	public float getPrecio()
	{
		return precio;
	}
	
	public boolean getEstado()
	{
		return estado;
	}
	
	public int getNroHabitacion(){
		return nroHabitacion;
	}

	public String getDescrpcion()
	{
		return descripcion;
	}

	public abstract String toString();

	public String mostrarHab()
	{
		return "Nro Habitacion:"+nroHabitacion+"\nPrecio:"+precio+"\nCapacidad:"+capacidad;
	}
}