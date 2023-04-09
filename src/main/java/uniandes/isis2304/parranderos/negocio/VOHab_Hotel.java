package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de la HabitacionHotel
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Felipe Nunez
 */
public interface VOHab_Hotel
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del ALOJAmiento asociado
	 */
	public long getIdAloja();
	
	/**
	 * @return la categoria de la Habitacion del hotel
	 */
	public String getCategoria();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la HabitacionHotel
	 */
	public String toString();

}
