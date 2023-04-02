package uniandes.isis2304.parranderos.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOReserva
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id de la Reserva
	 */
	public long getId();
	
	/**
	 * @return Fecha_llegada de la Reserva
	 */
	public String getFecha_llegada();
	
	/**
	 * @return Fecha_Salida de la Reserva
	 */
	public String getFecha_Salida();
	
	/**
	 * @return Precio de la Reserva
	 */
	public float getPrecio();
	
	/**
	 * @return Id_cliente de la Reserva
	 */
	public long getId_cliente();
	
	/**
	 * @return Id_alojamiento de la Reserva
	 */
	public long getId_alojamiento();
	
	/**
	 * @return Id_operador de la Reserva
	 */
	public long getId_operador();
	
	/**
	 * @return Estado de la Reserva
	 */
	public String getEstado();
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}