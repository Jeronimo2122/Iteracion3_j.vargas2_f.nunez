package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

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
	public Timestamp getFecha_llegada();
	
	/**
	 * @return Fecha_Salida de la Reserva
	 */
	public Timestamp getFecha_Salida();
	
	/**
	 * @return Precio de la Reserva
	 */
	public float getPrecio();
	
	/**
	 * @return Id_cliente de la Reserva
	 */
	public long getId_cliente();
	
	/**
	 * @return Id_operador de la Reserva
	 */
	public long getId_Alojamiento();
	
	/**
	 * @return Estado de la Reserva
	 */
	public String getEstado();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public Timestamp getFecha_insercion();
	@Override
	
	public String toString();

}