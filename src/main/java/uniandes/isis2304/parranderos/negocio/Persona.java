package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto Persona del negocio de AlohAndes
 *
 * @author Felipe Nunez
 */
public class Persona implements VOPersona
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del la Persona
	 */
	private long id;
	
	/**
	 * Identificacion de la persona
	 */
	private long Identificacion;
	
	/**
	 * Vinculo de la persona con la universidad
	 */
	private String vinculo;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Persona() 
    {
    	this.id = 0;
		this.Identificacion = 0;
        this.vinculo = "";
	}

	/**
	 */
    public Persona(long id, long Identificacion, String vinculo) 
    {
    	this.id = id;
		this.Identificacion = Identificacion;
        this.vinculo = vinculo;
	}

    /**
	 * @return id de la persona
	 */ 
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - nuevo id de la persona
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return Identificacion de la persona
	 */
	public long getIdentificacion() 
	{
		return Identificacion;
	}
	
	/**
	 * @param Identificacion - nueva identificacion de la persona
	 */
	public void setIdentificacion(long Identificacion) 
	{
		this.Identificacion = Identificacion;
	}
	
    	/**
	 * @return vinculo de la persona con la universidad
	 */
	public String getVinculo() 
	{
		return vinculo;
	}
	
	/**
	 * @param vinculo - nuevo vinculo de la persona con la universidad
	 */
	public void setVinculo(String vinculo) 
	{
		this.vinculo = vinculo;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Uso
	 */
	public String toString() 
	{
		return "Persona [id=" + id + ", Identificacion=" + Identificacion + ", Vinculo=" + vinculo + "]" ;
	}
	

}

