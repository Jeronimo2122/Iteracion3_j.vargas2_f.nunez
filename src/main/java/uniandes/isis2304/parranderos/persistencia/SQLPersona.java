package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Persona;

/**
 */
class SQLPersona
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
	public SQLPersona (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Persona a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Persona
	 * @param Identificacion - Identificacion de la Persona (si tiene)
     * @param vinculo - Vinculo de la persona con la universidad
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPersona (PersistenceManager pm, long id, long identificacion, String vinculo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersona() + "(id, identificacion, vinculo) values (?, ?, ?)");
        q.setParameters(id, identificacion, vinculo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNa Persona de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id- El identificador de la persona
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPersonaPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersona () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Persona de la 
	 * base de datos de alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la persona
	 * @return El objeto Persona que tiene el identificador dado
	 */
	public Persona darPersonaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersona() + " WHERE id = ?");
		q.setResultClass(Persona.class);
		q.setParameters(id);
		return (Persona) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Persona de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Persona
	 */
	public List<Persona> darPersonas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersona ());
		q.setResultClass(Persona.class);
		return (List<Persona>) q.executeList();
	}

}
