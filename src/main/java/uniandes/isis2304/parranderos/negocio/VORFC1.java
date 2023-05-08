package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VORFC1
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Servicio
	 */
	public long getId();

    public String getOperador();
	
	/**
	 * @return el nombre del Servicio
	 */
	public float getDinero_recibido_corrido();

    public float getDinero_recibido_anio_actual();

    
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}