package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
o
 */
public class Reserva implements VOReserva
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los Clientes
	 */
	private long Id;
	
	/**
	 * Fecha_llegada del Reserva
	 */
	private String Fecha_llegada;

	/**
	 * Fecha_Salida del Reserva
	 */
	private String Fecha_Salida;
	
	/**
	 * precio del Reserva
	 */
	private float precio;
	
	/**
	 * Id_Cliente del Reserva
	 */
	private long Id_Cliente;
	
	/**
	 * Id_Alojamiento del Reserva
	 */
	private long Id_Alojamiento;
	
	/**
	 * Id_Operador del Reserva
	 */
	private long Id_Operador;
	
	/**
	 * Estado de Reserva
	 */
	private String estado;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Reserva() 
    {
    	this.Id = 0;
		this.Fecha_llegada = "";
		this.Fecha_Salida = "";
		this.precio = 0;
		this.Id_Cliente = 0;
		this.Id_Alojamiento = 0;
		this.Id_Operador = 0;
		this.estado = "";
	}

	/**
	 */
    public Reserva(long Identificacion, String nombre, String vinculo) 
    {
    	this.Id = 0;
		this.Fecha_llegada = "";
		this.Fecha_Salida = "";
		this.precio = 0;
		this.Id_Cliente = 0;
		this.Id_Alojamiento = 0;
		this.Id_Operador = 0;
		this.estado = "";
	}


    /**
	 * @return Identificacion del Cliente
	 */
	public long getId() 
	{
		return Id;
	}
	
	/**
	 * @param id - nueva Identificacion del Cliente
	 */
	public void setId(long Id) 
	{
		this.Id = Id;
	}
	
	
	/**
	 * @return el nombre del Cliente
	 */
	public String getFecha_llegada() 
	{
		return Fecha_llegada;
	}
	
	/**
	 * @param nombre El nuevo nombre del Cliente
	 */
	public void setFecha_llegada(String Fecha_llegada) 
	{
		this.Fecha_llegada = Fecha_llegada;
	}
	
	
	/**
	 * @return la ciudad del bar
	 */
	public String getFecha_Salida() 
	{
		return Fecha_Salida;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setFecha_Salida(String Fecha_Salida) 
	{
		this.Fecha_Salida = Fecha_Salida;
	}
	
	
	
	/**
	 * @return la ciudad del bar
	 */
	public float getPrecio() 
	{
		return precio;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setPrecio(float precio) 
	{
		this.precio = precio;
	}
	
	
	
	/**
	 * @return la ciudad del bar
	 */
	public long getId_cliente() 
	{
		return Id_Cliente;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setId_cliente(long Id_Cliente) 
	{
		this.Id_Cliente = Id_Cliente;
	}
	
	
	/**
	 * @return la ciudad del bar
	 */
	public long getId_alojamiento() 
	{
		return Id_Alojamiento;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setId_alojamiento(long Id_Alojamiento) 
	{
		this.Id_Alojamiento = Id_Alojamiento;
	}
	
	
	/**
	 * 
	 */
	public long getId_operador() 
	{
		return Id_Operador;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setId_operador(long Id_Operador) 
	{
		this.Id_Operador = Id_Operador;
	}
	
	
	
	/**
	 * 
	 */
	public String getEstado() 
	{
		return estado;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Cliente [Id=" + Id + ", Fecha_llegada=" + Fecha_llegada + ", Fecha_Salida=" + Fecha_Salida + 
				", precio=" + precio + ", Id_Cliente=" + Id_Alojamiento + ", Id_Operador=" + Id_Operador 
				+ ", estado=" + estado + "]" ;
	}

	

}