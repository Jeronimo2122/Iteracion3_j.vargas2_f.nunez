package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

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
	private Timestamp Fecha_llegada;

	/**
	 * Fecha_Salida del Reserva
	 */
	private Timestamp Fecha_Salida;
	
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
		this.Fecha_llegada = new Timestamp(0);
		this.Fecha_Salida = new Timestamp(0);
		this.precio = 0;
		this.Id_Cliente = 0;
		this.Id_Alojamiento = 0;
		this.Id_Operador = 0;
		this.estado = "";
	}

	/**
	 */
    public Reserva(long Id, Timestamp Fecha_llegada, Timestamp Fecha_Salida, float precio, long Id_Cliente, long Id_Alojamiento, long Id_Operador, String estado ) 
    {
    	this.Id = Id;
		this.Fecha_llegada = Fecha_llegada;
		this.Fecha_Salida = Fecha_Salida;
		this.precio = precio;
		this.Id_Cliente = Id_Cliente;
		this.Id_Alojamiento = Id_Alojamiento;
		this.Id_Operador = Id_Operador;
		this.estado = estado;
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
	public Timestamp getFecha_llegada() 
	{
		return Fecha_llegada;
	}
	
	/**
	 * @param nombre El nuevo nombre del Cliente
	 */
	public void setFecha_llegada(Timestamp Fecha_llegada) 
	{
		this.Fecha_llegada = Fecha_llegada;
	}
	
	
	
	public Timestamp getFecha_Salida() 
	{
		return Fecha_Salida;
	}
	

	public void setFecha_Salida(Timestamp Fecha_Salida) 
	{
		this.Fecha_Salida = Fecha_Salida;
	}
	
	
	
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
	
	
	public long getId_cliente() 
	{
		return Id_Cliente;
	}
	public void setId_cliente(long Id_Cliente) 
	{
		this.Id_Cliente = Id_Cliente;
	}

	public long getId_alojamiento() 
	{
		return Id_Alojamiento;
	}
	public void setId_alojamiento(long Id_Alojamiento) 
	{
		this.Id_Alojamiento = Id_Alojamiento;
	}
	
	public long getId_operador() 
	{
		return Id_Operador;
	}
	public void setId_operador(long Id_Operador) 
	{
		this.Id_Operador = Id_Operador;
	}
	
	

	public String getEstado() 
	{
		return estado;
	}
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
		return "Reserva [Id=" + Id + ", Fecha_llegada=" + Fecha_llegada + ", Fecha_Salida=" + Fecha_Salida + 
				", precio=" + precio + ", Id_Cliente=" + Id_Alojamiento + ", Id_Operador=" + Id_Operador 
				+ ", estado=" + estado + "]" ;
	}

	

}