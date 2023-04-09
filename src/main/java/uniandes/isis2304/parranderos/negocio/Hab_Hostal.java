package uniandes.isis2304.parranderos.negocio;

import java.text.SimpleDateFormat;

/**
 * Clase para modelar el concepto HabitacionHostal del negocio de AlohAndes
 *
 * @author Felipe Nunez
 */
public class Hab_Hostal implements VOHab_Hostal
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del alojamiento (hostal) asociado a la habitacion
	 */
	private long Id_Aloja;
	
	/**
	 * Hora de Apertura del hostal
	 */
	private SimpleDateFormat hora_Apertura;

    	/**
	 * Hora de cierre del hostal
	 */
	private SimpleDateFormat hora_Cierre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Hab_Hostal() 
    {
    	this.Id_Aloja = 0;
		this.hora_Apertura = new SimpleDateFormat("00:00");
        this.hora_Cierre = new SimpleDateFormat("00:00");
	}

	/**
	 */
    public Hab_Hostal(long Id_Aloja, SimpleDateFormat hora_Apertura, SimpleDateFormat hora_Cierre) 
    {
    	this.Id_Aloja = Id_Aloja;
		this.hora_Apertura = hora_Apertura;
        this.hora_Cierre = hora_Cierre;
	}

    /**
	 * @return Identificacion del Alojamiento asociado
	 */ 
	public long getIdAloja() 
	{
		return Id_Aloja;
	}
	
	/**
	 * @param Id_Aloja - nuevo id del alojamiento asociado 
	 */
	public void setIdAloja(long Id_Aloja) 
	{
		this.Id_Aloja = Id_Aloja;
	}
	
	/**
	 * @return hora de apertura de la habitacion
	 */
	public SimpleDateFormat getHoraApertura() 
	{
		return hora_Apertura;
	}
	/**
	 * @param hora_Apertura - nueva hora de apertura de la habitacion
	 */
	public void setHoraApertura(SimpleDateFormat hora_Apertura) 
	{
		this.hora_Apertura = hora_Apertura;
	}

    	/**
	 * @return hora de cierre de la habitacion
	 */
	public SimpleDateFormat getHoraCierre() 
	{
		return hora_Cierre;
	}
	/**
	 * @param hora_Cierre - nueva hora de cierre de la habitacion
	 */
	public void setHoraCierre(SimpleDateFormat hora_Cierre) 
	{
		this.hora_Cierre = hora_Cierre;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion del hostal
	 */
	public String toString() 
	{
		return "Hab_Hostal [Id Alojamiento=" + Id_Aloja+ ", Hora Apertura=" + hora_Apertura + ", "
				+ "Hora Cierre=" + hora_Cierre + "]" ;
	}
	

}
