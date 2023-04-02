package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Alojamiento implements VOAlojamiento
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del Alojamiento
	 */
	private long Id;
	
	/**
	 * Capacidad del Alojamiento
	 */
	private int capacidad;

	/**
	 * Estado del Alojamiento
	 */
	private String estado;
	
	/**
	 * direccion del Alojamiento
	 */
	private String direccion;
	
	/**
	 * tipo del Alojamiento
	 */
	private String tipo_aloja;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Alojamiento() 
    {
    	this.Id = 0;
		this.capacidad = 0;
		this.estado = "";
		this.direccion = "";
		this.tipo_aloja = "";
	}

	/**
	 */
    public Alojamiento(long Id, int capacidad, String estado, String direccion,String tipo_aloja) 
    {
    	this.Id = Id;
		this.capacidad = capacidad;
		this.estado = estado;
		this.direccion = direccion;
		this.tipo_aloja = tipo_aloja;
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
	 * @return el capacidad del Cliente
	 */
	public int getCapacidad() 
	{
		return capacidad;
	}
	/**
	 * @param nombre  nueva capacidad del Alojamiento
	 */
	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}
	
	/**
	 * @return la ciudad del bar
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
	
	
	/**
	 * @return la ciudad del bar
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	
	/**
	 * @return la ciudad del bar
	 */
	public String getTipo_Aloja() 
	{
		return tipo_aloja;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setTipo_Aloja(String tipo_aloja) 
	{
		this.tipo_aloja = tipo_aloja;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Alojamiento [Id=" + Id + ", capacidad=" + capacidad + ", "
				+ "estado=" + estado + "direccion=" + direccion + "tipo_aloja=" + tipo_aloja + "]" ;
	}
	

}