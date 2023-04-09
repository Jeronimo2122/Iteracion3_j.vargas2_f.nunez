package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Felipe Nunez
 */
public class Edificio_Universitario implements VOEdificio_Universitario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del Edificio Universitario
	 */
	private long Id;
	
	/**
	 * Numero de viviendas en el Edificio Universitario
	 */
	private int NumViviendas;

	/**
	 * Numero de viviendas disponibles en el Edificio Universitario
	 */
	private int NumDisponible;
	
	/**
	 * direccion del Edificio Universitario
	 */
	private String direccion;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Edificio_Universitario() 
    {
    	this.Id = 0;
		this.NumViviendas = 0;
		this.NumDisponible = 0;
		this.direccion = "";
	}

	/**
	 */
    public Edificio_Universitario(long Id, int NumViviendas, int NumDisponible, String direccion) 
    {
    	this.Id = Id;
		this.NumViviendas = NumViviendas;
		this.NumDisponible = NumDisponible;
		this.direccion = direccion;
	}

    /**
	 * @return Identificacion del Edificio Universitario
	 */ 
	public long getId() 
	{
		return Id;
	}
	
	/**
	 * @param id - nueva Identificacion del Edificio Universitario
	 */
	public void setId(long Id) 
	{
		this.Id = Id;
	}
	
	/**
	 * @return capacidad del Edificio Universitario
	 */
	public int getNumViviendas() 
	{
		return NumViviendas;
	}
	/**
	 * @param numViviendas  nueva capacidad del Edificio Universitario
	 */
	public void setNumViviendas(int NumViviendas) 
	{
		this.NumViviendas = NumViviendas;
	}
	
	/**
	 * @return capacidad disponible del Edificio Universitario
	 */
	public int getNumDisponible() 
	{
		return NumDisponible;
	}
	/**
	 * @param numDisponible - nueva capacidad disponible del Edificio Universitario
	 */
	public void setNumDisponible(int NumDisponible) 
	{
		this.NumDisponible = NumDisponible;
	}
	
	/**
	 * @return la direccion del Edificio Universitario
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * @param direccion - nueva direccion del Edificio Universitario
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Edificio Universitario
	 */
	public String toString() 
	{
		return "Edificio_Universitario [Id=" + Id + ", Numero de Viviendas=" + NumViviendas + ", "
				+ "numDisponible=" + NumDisponible + ", Direccion=" + direccion + "]" ;
	}
	

}
