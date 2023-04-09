package uniandes.isis2304.parranderos.negocio;

import java.text.SimpleDateFormat;

/**
 * Interfaz para los métodos get de la HabitacionHostal
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Felipe Nunez
 */
public interface VOHab_Hostal
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del ALOJAmiento asociado
	 */
	public long getIdAloja();
	
	/**
	 * @return la hora de apertura de la Habitacion del hostal
	 */
	public SimpleDateFormat getHoraApertura();
	
	/**
	 * @return la hora de cierre de la Habitacion del hostal
	 */
	public SimpleDateFormat getHoraCierre();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la HabitacionHotel
	 */
	public String toString();

}