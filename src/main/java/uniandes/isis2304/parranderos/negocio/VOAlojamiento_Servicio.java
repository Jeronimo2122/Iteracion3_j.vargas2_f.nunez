package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOAlojamiento_Servicio
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Servicio
	 */
	public long getId_Aloja();
	
	/**
	 * @return el nombre del Servicio
	 */
	public long getId_Servicio();
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}