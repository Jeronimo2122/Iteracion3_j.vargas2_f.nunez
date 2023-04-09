package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de Uso
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOUso
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Uso
	 */
	public long getIdentificacion();
	
	/**
	 * @return el Numero de noches contratadas en el uso
	 */
	public int getNochesContratadas();
	
	/**
	 * @return el numero de meses contratados en el uso
	 */
	public int getMesesContratados();
	
	/**
	 * @return el dinero pagado al uso
	 */
	public float getDineroPagado();

    	/**
	 * @return el id del alojamiento asociado al uso
	 */
	public int getId_Alojamiento();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Uso
	 */
	public String toString();

}

