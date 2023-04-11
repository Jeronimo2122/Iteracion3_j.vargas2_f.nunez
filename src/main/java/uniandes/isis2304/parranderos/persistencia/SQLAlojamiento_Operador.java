package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Alojamiento_Operador;
import uniandes.isis2304.parranderos.negocio.Cliente;

class SQLAlojamiento_Operador
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
	public SQLAlojamiento_Operador (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	

	public long adicionarAlojamiento_Operador (PersistenceManager pm, long idAlojamiento, long idOperador) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlojamiento_Operador() + "(id_Aloja, id_Operador) values (?, ?)");
        q.setParameters(idAlojamiento, idOperador);
        return (long) q.executeUnique();
    }


	public long eliminarAlojamiento_OperadorPorId_Aloja (PersistenceManager pm, long id_Aloja)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Operador () + " WHERE id_Aloja = ?");
        q.setParameters(id_Aloja);
        return (long) q.executeUnique();
    }


	public long eliminarAlojamiento_OperadorPorId_Operador (PersistenceManager pm, long Id_Operador)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Operador () + " WHERE Id_Operador = ?");
        q.setParameters(Id_Operador);
        return (long) q.executeUnique();
    }

	public long eliminarAlojamiento_Operador (PersistenceManager pm, long id_Alojamiento, long id_Servicio)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Operador () + " WHERE id_Alojamiento = ? AND id_Servicio = ?");
        q.setParameters(id_Alojamiento, id_Servicio);
        return (long) q.executeUnique();
    }

	public List<Alojamiento_Operador> darAlojamiento_OperadorPorId_Operador(PersistenceManager pm, long Id_Operador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Operador() + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(Id_Operador);
		return (List<Alojamiento_Operador>) q.executeUnique();
	}

    public List<Alojamiento_Operador> darAlojamiento_OperadorPorid_Aloja(PersistenceManager pm, long id_Servicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Operador() + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id_Servicio);
		return (List<Alojamiento_Operador>) q.executeUnique();
	}

	
	public List<Alojamiento_Operador> darAlojamiento_Operadors (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Operador ());
		q.setResultClass(Alojamiento_Operador.class);
		return (List<Alojamiento_Operador>) q.executeList();
	}

	
	public Alojamiento_Operador darAlojamiento_Operador (PersistenceManager pm, long id_aloja, long id_operador) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento_Operador () + " WHERE id_aloja = ? AND id_operador = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id_aloja, id_operador);
		return (Alojamiento_Operador) q.executeList();
	}

	
}