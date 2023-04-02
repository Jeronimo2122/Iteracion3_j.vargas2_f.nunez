package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOAlojamiento
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Alojamiento
	 */
	public long getId();
	
	/**
	 * @return el Capacidad del Alojamiento
	 */
	public int getCapacidad();
	
	/**
	 * @return la Estado del Alojamiento
	 */
	public String getEstado();
	
	/**
	 * @return el Direccion del Alojamiento
	 */
	public String getDireccion();
	
	/**
	 * @return el Tipo_Aloja del Alojamiento
	 */
	public String getTipo_Aloja();

	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString();

}