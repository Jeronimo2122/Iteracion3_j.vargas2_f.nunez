package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Edificio_Universitario;

/**
 */
class SQLEdificio_Universitario
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
	public SQLEdificio_Universitario (PersitenciaAforoandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Edificio universitario a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del edificio universitario
	 * @param numViviendas - El numero de viviendas en el edificio universitario
	 * @param numViviendasDisponibles   - numero de viviendas disponibles en el edificio universitario
     * @param direccion - La direccion del edificio universitario
	 * @return El número de tuplas insertadas
	 */
	public long adicionarEdificio_Universitario (PersistenceManager pm, long id, int numViviendas, int numViviendasDisponibles, String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEdificio_Universitario() + "(id, numViviendas, numViviendasDisponibles, direccion) values (?, ?, ?, ?)");
        q.setParameters(id, numViviendas, numViviendasDisponibles, direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN edificio universitario de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEdificioUniversitario- El identificador del Edificio universitario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEdificio_UniversitarioPorId (PersistenceManager pm, long idEdificioUniversitario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEdificio_Universitario () + " WHERE id = ?");
        q.setParameters(idEdificioUniversitario);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Hedificio universitario de la 
	 * base de datos de alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEdificioUniversitario- El identificador del Edificio universitario
	 * @return El objeto Edificio Universitariol que tiene el identificador dado
	 */
	public Edificio_Universitario darEdificio_UniversitarioPorId (PersistenceManager pm, long idEdificioUniversitario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEdificio_Universitario() + " WHERE id = ?");
		q.setResultClass(Edificio_Universitario.class);
		q.setParameters(idEdificioUniversitario);
		return (Edificio_Universitario) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Edificio universitario de la 
	 * base de datos de alohandess, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param idEdificioUniversitario- El identificador del Edificio universitario
	 * @return Una lista de objetos Edificio universitario que tienen el nombre dado
	 */
	public List<Edificio_Universitario> darEdificio_UniversitariosconId (PersistenceManager pm, int idEdificioUniversitario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEdificio_Universitario () + " WHERE nombre = ?");
		q.setResultClass(Edificio_Universitario.class);
		q.setParameters(idEdificioUniversitario);
		return (List<Edificio_Universitario>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de HOTELES_HOSTALES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Edificio_Universitario> darEdificios_Universitarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEdificio_Universitario ());
		q.setResultClass(Edificio_Universitario.class);
		return (List<Edificio_Universitario>) q.executeList();
	}

}