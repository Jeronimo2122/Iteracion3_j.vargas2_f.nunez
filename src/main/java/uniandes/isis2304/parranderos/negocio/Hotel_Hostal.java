package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto Hotel_Hostal del negocio de AlohAndes
 *
 * @author Felipe Nunez
 */
public class Hotel_Hostal implements VOHotel_Hostal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del operador asociado al hotel/hostal
	 */
	private long id;
	
	/**
	 * Estado legal del Hotel_Hostal
	 */
	private String estadoLegal;
	
	/**
	 * Numero de registro de cedula del Hotel/Hostal
	 */
	private int numeroRegistroCC;

    	/**
	 * Numero de habitaciones en el hotel/hostal
	 */
	private int numHabitaciones;

    	/**
	 * Numero de habitaciones disponibles para uso en el hotel/hostal
	 */
	private int numHabitacionesDisponibles;

    	/**
	 * Direccion del hotel/Hostal
	 */
	private String direccion;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Hotel_Hostal() 
    {
    	this.id = 0;
		this.estadoLegal = "";
        this.numeroRegistroCC = 0;
        this.numHabitaciones = 0;
        this.numHabitacionesDisponibles = 0;
        this.direccion = "";
	}

	/**
	 */
    public Hotel_Hostal(long id, String estadoLegal, int numeroRegistroCC, int numHabitaciones, int numHabitacionesDisponibles, String direccion) 
    {
    	this.id = id;
		this.estadoLegal = estadoLegal;
        this.numeroRegistroCC = numeroRegistroCC;
        this.numHabitaciones = numHabitaciones;
        this.numHabitacionesDisponibles = numHabitacionesDisponibles;
        this.direccion = direccion;
	}

    /**
	 * @return Identificacion del Hotel/Hostal
	 */ 
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - nueva Identificacion del Hotel/Hostal
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return estado legal del Hotel/Hostal
	 */
	public String getEstadoLegal() 
	{
		return estadoLegal;
	}
	
	/**
	 * @param estadoLegal - nuevo estado legal del Hotel/Hostal
	 */
	public void setEstadoLegal(String estadoLegal) 
	{
		this.estadoLegal = estadoLegal;
	}
	
    	/**
	 * @return numero de registro CC del Hotel/Hostal
	 */
	public int getNumeroRegistroCC() 
	{
		return numeroRegistroCC;
	}
	
	/**
	 * @param numeroRegistroCC - nuevo numero de registro CC del Hotel/Hostal
	 */
	public void setNumeroRegistroCC(int numeroRegistroCC) 
	{
		this.numeroRegistroCC = numeroRegistroCC;
	}

        	/**
	 * @return numero de habitaciones del Hotel/Hostal
	 */
	public int getNumHabitaciones() 
	{
		return numHabitaciones;
	}
	
	/**
	 * @param numHabitaciones - nuevo numero de habitaciones del Hotel/Hostal
	 */
	public void setNumHabitaciones(int numHabitaciones) 
	{
		this.numHabitaciones = numHabitaciones;
	}
    
        	/**
	 * @return numero de habitaciones disponibles del Hotel/Hostal
	 */
	public int getNumHabitacionesDisponibles() 
	{
		return numHabitacionesDisponibles;
	}
	
	/**
	 * @param numHabitacionesDisponibles - nuevo numero de habitaciones disponibles del Hotel/Hostal
	 */
	public void setNumHabitacionesDisponibles(int numHabitacionesDisponibles) 
	{
		this.numHabitacionesDisponibles = numHabitacionesDisponibles;
	}

        	/**
	 * @return direccion del Hotel/Hostal
	 */
	public String getDireccion() 
	{
		return direccion;
	}
	
	/**
	 * @param direccion - nueva direccion del Hotel/Hostal
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hotel/Hostal
	 */
	public String toString() 
	{
		return "Hotel/Hostal [id=" + id + ", Estado Legal=" + estadoLegal + ", Numero Registro CC=" + numeroRegistroCC
                + ", Numero de Habitaciones=" + numHabitaciones + ", Numero de Habitaciones Disponibles=" + numHabitacionesDisponibles
                + "Direccion=" + direccion + "]" ;
	}
	

}

