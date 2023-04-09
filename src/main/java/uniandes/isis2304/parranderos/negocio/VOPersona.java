package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de Hotel/Hostal
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Felipe Nunez
 */
public interface VOPersona
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Hotel/Hostal
	 */
	public long getId();
	
	/**
	 * @return el estado legal del Hotel/Hostal
	 */
	public long getIdentificacion();

    	/**
	 * @return el numero de registro CC del Hotel/Hostal
	 */
	public String getVinculo();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hotel/Hostal
	 */
	public String toString();

}
