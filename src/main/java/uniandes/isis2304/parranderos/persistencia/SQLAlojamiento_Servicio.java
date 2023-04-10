package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Alojamiento_Servicio;
import uniandes.isis2304.parranderos.negocio.Cliente;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLAlojamiento_Servicio
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
	public SQLAlojamiento_Servicio (PersitenciaAforoandes pp)
	{
		this.pp = pp;
	}
	

	public long adicionarAlojamiento_Servicio (PersistenceManager pm, long id_Aloja, long id_Servicio) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlojamiento_Servicio () + "(id_Aloja, id_Servicio) values (?, ?)");
        q.setParameters(id_Aloja, id_Servicio);
        return (long) q.executeUnique();
    }


	public long eliminarAlojamiento_ServiciorPorId_Aloja (PersistenceManager pm, long id_Aloja)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Servicio () + " WHERE id_Aloja = ?");
        q.setParameters(id_Aloja);
        return (long) q.executeUnique();
    }


	public long eliminarAlojamiento_ServicioPorId_Servicio (PersistenceManager pm, long id_Servicio)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Servicio () + " WHERE id_Servicio = ?");
        q.setParameters(id_Servicio);
        return (long) q.executeUnique();
    }

	public long eliminarAlojamiento_Servicio (PersistenceManager pm, long id_Alojamiento, long id_Servicio)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Servicio () + " WHERE id_Alojamiento = ? AND id_Servicio = ?");
        q.setParameters(id_Alojamiento, id_Servicio);
        return (long) q.executeUnique();
    }

	public List<Alojamiento_Servicio> darAlojamiento_ServicioPorId_Servicio(PersistenceManager pm, long id_Servicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Servicio() + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id_Servicio);
		return (List<Alojamiento_Servicio>) q.executeUnique();
	}

    public List<Alojamiento_Servicio> darAlojamiento_ServicioPorid_Aloja(PersistenceManager pm, long id_Servicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Servicio() + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id_Servicio);
		return (List<Alojamiento_Servicio>) q.executeUnique();
	}

	public List<Alojamiento_Servicio> darAlojamiento_ServicioS (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Servicio ());
		q.setResultClass(Alojamiento_Servicio.class);
		return (List<Alojamiento_Servicio>) q.executeList();
	}

	public Alojamiento_Servicio darAlojamiento_Servicio (PersistenceManager pm, long id_aloja, long id_Servicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Servicio () + " WHERE id_aloja = ? AND id_operador = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id_aloja, id_Servicio);
		return (Alojamiento_Servicio) q.executeList();
	}

}