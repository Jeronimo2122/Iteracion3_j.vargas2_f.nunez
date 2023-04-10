package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de EdificioUniversitario.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Felipe Nunez
 */
public interface VOEdificio_Universitario
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del EdificioUniversitario
	 */
	public long getId();
	
	/**
	 * @return el NumViviendas del EdificioUniversitario
	 */
	public int getNumViviendas();
	
	/**
	 * @return el NumDIsponible de habitaciones del EdificioUniversitario
	 */
	public int getNumDisponible();
	
	/**
	 * @return el Direccion del EdificioUniversitario
	 */
	public String getDireccion();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del EdificioUniversitario
	 */
	public String toString();

}
