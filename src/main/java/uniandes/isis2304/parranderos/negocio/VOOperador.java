package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOOperador
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del bar
	 */
	public long getId();
	
	/**
	 * @return el nombre del bar
	 */
	public String getNombre();
	
	/**
	 * @return la ciudad del bar
	 */
	public int getGanancias();
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getDireccion();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}