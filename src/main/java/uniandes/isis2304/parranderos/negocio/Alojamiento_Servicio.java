package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Alojamiento_Servicio implements VOAlojamiento_Servicio
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * ID ALojamiento
	 */
	private long Id_Aloja;
	
	/**
	 * ID Servicio
	 */
	private long Id_Servicio;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Alojamiento_Servicio() 
    {
    	this.Id_Aloja = 0;
    	this.Id_Servicio = 0;
	}

	/**
	 * Constructor con valores
	 */
    public Alojamiento_Servicio(long Id_Aloja, long Id_Servicio) 
    {
     	this.Id_Aloja = Id_Aloja;
   		this.Id_Servicio = Id_Servicio;
    }

    /**
	 * @return Identificacion del Alojamiento
	 */
	public long getId_Aloja() 
	{
		return Id_Aloja;
	}
	/**
	 * @param id - El nuevo id del Alojamiento
	 */
	public void setId_Aloja(long Id_Aloja) 
	{
		this.Id_Aloja = Id_Aloja;
	}
	
	
	 /**
		 * @return Identificacion del Operador
		 */
	public long getId_Servicio() 
	{
		return Id_Servicio;
	}
	/**
	 * @param id - El nuevo id del Servicio
	 */
	public void setId_Servicio(long Id_Servicio) 
	{
		this.Id_Servicio = Id_Servicio;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Alojamiento_Servicio [Id_Aloja=" + Id_Aloja + ", Id_Operador=" + Id_Servicio + "]" ;
	}
	

}