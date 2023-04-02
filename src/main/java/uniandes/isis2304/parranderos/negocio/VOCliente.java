package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOCliente
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Cliente
	 */
	public long getIdentificacion();
	
	/**
	 * @return el nombre del Cliente
	 */
	public String getNombre();
	
	/**
	 * @return la vinculo del Cliente
	 */
	public String getVinculo();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}