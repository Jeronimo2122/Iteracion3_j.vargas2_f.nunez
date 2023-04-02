package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Alojamiento_Operador implements VOAlojamiento_Operador
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
	private long Id_Operador;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Alojamiento_Operador() 
    {
    	this.Id_Aloja = 0;
    	this.Id_Operador = 0;
	}

	/**
	 * Constructor con valores
	 */
    public Alojamiento_Operador(long Id_Aloja, long Id_Operador) 
    {
     	this.Id_Aloja = Id_Aloja;
   		this.Id_Operador = Id_Operador;
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
	public long getId_Operador() 
	{
		return Id_Operador;
	}
	
	/**
	 * @param id - El nuevo id del Operador
	 */
	public void setId_Operador(long Id_Operador) 
	{
		this.Id_Operador = Id_Operador;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Alojamiento_Operador [Id_Aloja=" + Id_Aloja + ", Id_Operador=" + Id_Operador + "]" ;
	}
	

}