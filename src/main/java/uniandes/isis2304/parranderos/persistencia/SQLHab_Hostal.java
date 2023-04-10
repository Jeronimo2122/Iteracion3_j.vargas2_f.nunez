package uniandes.isis2304.parranderos.persistencia;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hab_Hostal;

/**
 */
class SQLHab_Hostal
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLHab_Hostal (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Habitacion de Hostal a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja - El identificador del Alojamiento(Hotel)
	 * @param horaApertura - Hora de apertura de la Hab_Hostal
     * @param horaCierre - Hora de cierre de la Hab_Hostal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHab_Hostal (PersistenceManager pm, long id, SimpleDateFormat horaApertura, SimpleDateFormat horaCierre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHab_Hostal() + "(id, horaApertura, horaCierre) values (?, ?, ?)");
        q.setParameters(id, horaApertura, horaCierre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNa habitacion de hostal de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja- El identificador del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHab_HostalPorId (PersistenceManager pm, long idHab_Hostal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHab_Hostal () + " WHERE id = ?");
        q.setParameters(idHab_Hostal);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA hbitacion de hostal de la 
	 * base de datos de alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id_Aloja- El identificador del alojamiento
	 * @return El objeto Hab_Hostal que tiene el identificador dado
	 */
	public Hab_Hostal darHab_HostalPorId (PersistenceManager pm, long idHab_Hostal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHab_Hostal() + " WHERE id = ?");
		q.setResultClass(Hab_Hostal.class);
		q.setParameters(idHab_Hostal);
		return (Hab_Hostal) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Hab_Hostal de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Hab_Hostal
	 */
	public List<Hab_Hostal> darHabs_Hostales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHab_Hostal ());
		q.setResultClass(Hab_Hostal.class);
		return (List<Hab_Hostal>) q.executeList();
	}

}

