package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Operador;

/**
 */
class SQLOperador
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
	public SQLOperador (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOperador (PersistenceManager pm, long id, String nombre, float ganancias) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperador() + "(id, nombre, ganancias, direccion) values (?, ?, ?)");
        q.setParameters(id, nombre, ganancias);
        return (long) q.executeUnique();
	}

	public long eliminarOperadorPorNombre (PersistenceManager pm, String nombreOperador)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperador () + " WHERE nombre = ?");
        q.setParameters(nombreOperador);
        return (long) q.executeUnique();
	}

	public long eliminarOperadorPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperador () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Operador darOperadorPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperador() + " WHERE id = ?");
		q.setResultClass(Operador.class);
		q.setParameters(id);
		return (Operador) q.executeUnique();
	}

	public List<Operador> darOperadoresPorNombre (PersistenceManager pm, String nombreOp) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperador () + " WHERE nombre = ?");
		q.setResultClass(Operador.class);
		q.setParameters(nombreOp);
		return (List<Operador>) q.executeList();
	}

	public List<Operador> darOperadores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperador ());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}

}
