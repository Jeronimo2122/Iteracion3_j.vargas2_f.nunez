package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Operador implements VOOperador
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El id ÚNICO de los Operador
	 */
	private long Id;
	
	/**
	 * El nombre del Operador
	 */
	private String nombre;

	/**
	 * Vinculo del Operador
	 */
	private float ganancias;
	

	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Operador() 
    {
    	this.Id = 0;
    	this.nombre = "";
    	this.ganancias = 0;
	}

	
    public Operador(long Id, String nombre, float ganancias) 
    {
    	this.Id = Id;
    	this.nombre = nombre;
    	this.ganancias = ganancias;
    }


    /**
	 * @return Identificacion del Operador
	 */
	public long getId() 
	{
		return Id;
	}
	
	/**
	 * @param id - nueva Identificacion del Operador
	 */
	public void setId(long Id) 
	{
		this.Id = Id;
	}
	
	/**
	 * @return el nombre del Operador
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del Operador
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return la ciudad del Operador
	 */
	public float getGanancias() 
	{
		return ganancias;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Operador
	 */
	public void setGanancias(int ganancias) 
	{
		this.ganancias = ganancias;
	}
	

	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Operador
	 */
	public String toString() 
	{
		return "Operador [Id=" + Id + ", nombre=" + nombre + ", ganancias=" + ganancias + "]" ;
	}
	

}