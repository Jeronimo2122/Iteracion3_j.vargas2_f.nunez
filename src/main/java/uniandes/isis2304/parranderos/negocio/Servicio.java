package uniandes.isis2304.parranderos.negocio;


public class Servicio implements VOServicio
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * ID Servicio
	 */
	private long Id;
	
	/**
	 * Nombre Servicio
	 */
	private String nombre;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Servicio() 
    {
    	this.Id = 0;
    	this.nombre ="";
	}

	/**
	 * Constructor con valores
	 */
    public Servicio(long Id, String nombre) 
    {
     	this.Id = Id;
   		this.nombre = nombre;
    }

    /**
	 * @return Identificacion del Alojamiento
	 */
	public long getId() 
	{
		return Id;
	}
	
	 /**
		 * @return Identificacion del Operador
		 */
	public String getNombre() 
	{
		return nombre;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Servicio [Id=" + Id + ", Nombre=" + nombre + "]" ;
	}
	

}