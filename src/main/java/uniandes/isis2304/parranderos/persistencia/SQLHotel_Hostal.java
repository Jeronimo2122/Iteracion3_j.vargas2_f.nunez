package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel_Hostal;

/**
 */
class SQLHotel_Hostal
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersitenciaAforoandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersitenciaAforoandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLHotel_Hostal (PersitenciaAforoandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del hotel o hostal
	 * @param estadoLegal - El estado legal del hotel o hostal
	 * @param numeroRegistroCC - El numero de registro CC hotel o hostal
	 * @param numHabitaciones - El numero de habitaciones del hotel o hostal
	 * @param numHabitacionesDisponibles - El número de habitaciones disponibles del hotel o hostal
     * @param direccion - La direccion del hotel o hostal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHotel_Hostal (PersistenceManager pm, long id, String estadoLegal, int numeroRegistroCC, int numHabitaciones, int numHabitacionesDisponibles, String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel_Hostal() + "(id, estadoLegal, numeroRegistroCC, numHabitaciones, numHabitacionesDisponibles, direccion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, estadoLegal, numeroRegistroCC, numHabitaciones, numHabitacionesDisponibles, direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Hotel o Hostal de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHotelHostal - El identificador del hotel o hostal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHotel_HostalPorId (PersistenceManager pm, long idHotelHostal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel_Hostal () + " WHERE id = ?");
        q.setParameters(idHotelHostal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Hotel o Hostal de la 
	 * base de datos de alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHotelHostal - El identificador del hotel o hostal
	 * @return El objeto Hotel o Hostal que tiene el identificador dado
	 */
	public Hotel_Hostal darHotel_HostalPorId (PersistenceManager pm, long idHotelHostal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel_Hostal() + " WHERE id = ?");
		q.setResultClass(Hotel_Hostal.class);
		q.setParameters(idHotelHostal);
		return (Hotel_Hostal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de HOTELES_HOSTALES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Hotel_Hostal> darHoteles_Hostales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel_Hostal ());
		q.setResultClass(Hotel_Hostal.class);
		return (List<Hotel_Hostal>) q.executeList();
	}

}
