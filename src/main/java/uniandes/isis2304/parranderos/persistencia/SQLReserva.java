package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Reserva;

/**
 */
class SQLReserva
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
	public SQLReserva (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	public long adicionarReserva (PersistenceManager pm, long Id, String fecha_llegada, String fecha_salida, float precio, long Id_Cliente, long Id_Alojamiento, String estado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReserva() + 
		"(Id, fecha_llegada, fecha_salida, precio, Id_Cliente, Id_Alojamiento, estado) values (?, TO_DATE(?, 'DD/MM/YYYY'), TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)");
        q.setParameters(Id, fecha_llegada, fecha_salida, precio, Id_Cliente, Id_Alojamiento, estado);
        return (long) q.executeUnique();
	}

	public long eliminarReservaPorId (PersistenceManager pm, long Id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE Id = ?");
        q.setParameters(Id);
        return (long) q.executeUnique();
	}

	public long eliminarReservaPorAlojamiento (PersistenceManager pm, long id_Alojamiento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE ID_ALOJAMIENTO = ?");
        q.setParameters(id_Alojamiento);
        return (long) q.executeUnique();
	}

	public Reserva darReservaPorId (PersistenceManager pm, long Id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva() + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(Id);
		return (Reserva) q.executeUnique();
	}


	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}

	public long ActulizarReserva (PersistenceManager pm, String estado, Long id_reserva)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaReserva () + " SET estado = ? WHERE ID = ?");
		q.setParameters(estado, id_reserva);
		return (long) q.executeUnique();
	}

	public List<Reserva> darReservasPorIdCliente (PersistenceManager pm, long id_Cliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva() + " WHERE ID_CLIENTE = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(id_Cliente);
		return (List<Reserva>) q.executeList();
	}


}
