package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto HabitacionHotel del negocio de AlohAndes
 *
 * @author Felipe Nunez
 */
public class Hab_Hotel implements VOHab_Hotel
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del alojamiento (hotel) asociado a la habitacion
	 */
	private long Id_Aloja;
	
	/**
	 * Categoria del hotel
	 */
	private String categoria;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Hab_Hotel() 
    {
    	this.Id_Aloja = 0;
		this.categoria = "";
	}

	/**
	 */
    public Hab_Hotel(long Id_Aloja, String categoria) 
    {
    	this.Id_Aloja = Id_Aloja;
		this.categoria = categoria;
	}

    /**
	 * @return Identificacion del Alojamiento asociado
	 */ 
	public long getIdAloja() 
	{
		return Id_Aloja;
	}
	
	/**
	 * @param Id_Aloja - nuevo id del alojamiento asociado 
	 */
	public void setIdAloja(long Id_Aloja) 
	{
		this.Id_Aloja = Id_Aloja;
	}
	
	/**
	 * @return categoria de la habitacion
	 */
	public String getCategoria() 
	{
		return categoria;
	}
	/**
	 * @param categoria - nueva categoria de la habitacion
	 */
	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion del hotel
	 */
	public String toString() 
	{
		return "Hab_Hotel [Id Alojamiento=" + Id_Aloja+ ", Categoria=" + categoria + "]" ;
	}
	

}
