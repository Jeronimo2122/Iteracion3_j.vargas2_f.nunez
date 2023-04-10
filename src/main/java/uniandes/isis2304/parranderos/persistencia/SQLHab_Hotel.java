package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hab_Hotel;

/**
 */
class SQLHab_Hotel
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
	public SQLHab_Hotel (PersitenciaAforoandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Habitacion de Hotel a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja - El identificador del Alojamiento(Hotel)
	 * @param categoria - Categoria de la habitacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHab_Hotel (PersistenceManager pm, long id, String categoria) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHab_Hotel() + "(id, categoria) values (?, ?)");
        q.setParameters(id, categoria);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNa habitacion de hotel de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja- El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHab_HotelId (PersistenceManager pm, long idHab_Hotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHab_Hotel () + " WHERE id = ?");
        q.setParameters(idHab_Hotel);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA hbitacion de hotel de la 
	 * base de datos de alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja- El identificador del alojamiento
	 * @return El objeto Hab_Hotel que tiene el identificador dado
	 */
	public Hab_Hotel darHab_HotelPorId (PersistenceManager pm, long idHab_Hotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHab_Hotel() + " WHERE id = ?");
		q.setResultClass(Hab_Hotel.class);
		q.setParameters(idHab_Hotel);
		return (Hab_Hotel) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS haitaciones de hotel de la 
	 * base de datos de alohandess, por su id
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja- El identificador del alojamiento
	 * @return Una lista de objetos Hab_Hotel que tienen el nombre dado
	 */
	public List<Hab_Hotel> darHab_HotelconId (PersistenceManager pm, int idHab_Hotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHab_Hotel () + " WHERE nombre = ?");
		q.setResultClass(Hab_Hotel.class);
		q.setParameters(idHab_Hotel);
		return (List<Hab_Hotel>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Hab_Hotel de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Hab_Hotel
	 */
	public List<Hab_Hotel> darHab_Hotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHab_Hotel ());
		q.setResultClass(Hab_Hotel.class);
		return (List<Hab_Hotel>) q.executeList();
	}

}
