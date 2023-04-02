package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Cliente implements VOCliente
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los Clientes
	 */
	private long Identificacion;
	
	/**
	 * El nombre del Cliente
	 */
	private String nombre;

	/**
	 * Vinculo del Cliente
	 */
	private String vinculo;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Cliente() 
    {
    	this.Identificacion = 0;
		this.nombre = "";
		this.vinculo = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Cliente(long Identificacion, String nombre, String vinculo) 
    {
     	this.Identificacion = 0;
   		this.nombre = "";
   		this.vinculo = "";
    }


    /**
	 * @return Identificacion del Cliente
	 */
	public long getIdentificacion() 
	{
		return Identificacion;
	}
	
	/**
	 * @param id - nueva Identificacion del Cliente
	 */
	public void setIdentificacion(long Identificacion) 
	{
		this.Identificacion = Identificacion;
	}
	
	/**
	 * @return el nombre del Cliente
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del Cliente
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public String getVinculo() 
	{
		return vinculo;
	}
	
	/**
	 * @param ciudad - nuevo vinculo del Cliente
	 */
	public void setVinculo(String vinculo) 
	{
		this.vinculo = vinculo;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Cliente [Identificacion=" + Identificacion + ", nombre=" + nombre + ", vinculo=" + vinculo + "]" ;
	}
	

}
