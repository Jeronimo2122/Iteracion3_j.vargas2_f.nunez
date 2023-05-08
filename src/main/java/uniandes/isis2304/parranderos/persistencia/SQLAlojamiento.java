package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Alojamiento;

/**
 */
class SQLAlojamiento 
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
	public SQLAlojamiento (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarAlojamiento (PersistenceManager pm, long id, int capacidad, String estado, String direccion, String tipo_aloja) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlojamiento() + 
		"(id, capacidad, estado, direccion, tipo_aloja) values (?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad, estado, direccion, tipo_aloja);
        return (long) q.executeUnique();
	}


	public long eliminarAlojamientoPorId(PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	public long eliminarAlojamientoPorTipoAlojamiento (PersistenceManager pm, String Tipo_Aloja)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento () + " WHERE Tipo_Aloja = ?");
        q.setParameters(Tipo_Aloja);
        return (long) q.executeUnique();
	}


	public Alojamiento darAlojamientoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento() + " WHERE ID = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(id);
		return (Alojamiento) q.executeUnique();
	}

	public List<Alojamiento> darAlojamientosPorTipo (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento ()  + " WHERE TIPO_ALOJA = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(tipo);
		return (List<Alojamiento>) q.executeList();
	}

	public List<Alojamiento> darAlojamientos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamiento ());
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}

	public List<Alojamiento> darAlojamientosDisponiblesPorTipo (PersistenceManager pm, String fecha_llegada, String fecha_Salida,
		String servicio, String tipo_Aloja )
	{
		Query q = pm.newQuery(SQL, "SELECT a.id,a.capacidad,a.estado,a.direccion,a.tipo_aloja "+
		"FROM ALOJAMIENTO_SERVICIO ASER,SERVICIO S, (SELECT a.id,a.capacidad,a.estado,a.direccion,a.tipo_aloja "+
		"	FROM ALOJAMIENTO A LEFT JOIN RESERVA R "+
		"	ON a.ID = R.ID_ALOJAMIENTO "+
		"	WHERE R.fecha_llegada NOT BETWEEN TO_DATE( ?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') "+
		"	AND R.fecha_salida NOT BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') "+
		"	OR R.fecha_salida IS NULL OR R.ESTADO = 'CANCELADA') A "+
		"WHERE aser.id_aloja = a.id AND aser.id_servicio = s.id "+
		"AND a.estado = 'DISPONIBLE' AND S.NOMBRE like ? AND a.tipo_aloja = ?  "+   
		"GROUP BY a.id,a.capacidad,a.estado,a.direccion,a.tipo_aloja");
		q.setResultClass(Alojamiento.class);
		q.setParameters(fecha_llegada, fecha_Salida, fecha_llegada, fecha_Salida, servicio, tipo_Aloja);
		return (List<Alojamiento>) q.executeList();
	}

	public long DeshabilitarAlojamiento (PersistenceManager pm, String estado, Long Id_aloja)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamiento() + " SET estado = ? WHERE ID = ?");
		q.setParameters(estado, Id_aloja);
		return (long) q.executeUnique();
	}
	
	public List<Alojamiento> darAlojamientosRelocalizables (PersistenceManager pm, Timestamp fecha_llegada, Timestamp fecha_Salida, Integer capacidad)
	{
		Query q = pm.newQuery(SQL, "SELECT * "+ 
		" FROM (SELECT a.id,a.capacidad,a.estado,a.direccion,a.tipo_aloja "+ 
		"	FROM ALOJAMIENTO A LEFT JOIN RESERVA R "+ 
		"	ON a.ID = R.ID_ALOJAMIENTO "+ 
		"	WHERE R.fecha_llegada NOT BETWEEN ? AND ? "+ 
		"	AND R.fecha_salida NOT BETWEEN ? AND ?  "+ 
		"	OR R.fecha_salida IS NULL OR R.ESTADO = 'CANCELADA') N "+ 
		" WHERE N.estado = 'DISPONIBLE' AND N.CAPACIDAD >= ?");  

		q.setResultClass(Alojamiento.class);
		q.setParameters(fecha_llegada, fecha_Salida, fecha_llegada, fecha_Salida, capacidad);
		return (List<Alojamiento>) q.executeList();
	}

	public List<Alojamiento> RFC4 (PersistenceManager pm, String fecha_llegada, String fecha_Salida, String servicio)
	{
		Query q = pm.newQuery(SQL, "SELECT a.id, a.capacidad, a.estado, a.direccion, a.tipo_aloja "+
		"FROM (SELECT a.id, a.capacidad, a.estado, a.direccion, a.tipo_aloja "+
		"	FROM ALOJAMIENTO A LEFT JOIN RESERVA R "+
		"	ON a.ID = R.ID_ALOJAMIENTO "+
		"	WHERE R.fecha_llegada NOT BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') "+
		"	AND R.fecha_salida NOT BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')  "+
		"	OR R.fecha_salida IS NULL OR R.ESTADO = 'CANCELADA' ) A, ALOJAMIENTO_SERVICIO ASER, SERVICIO S "+
		"WHERE A.ID = ASER.ID_aLOJA AND ASER.ID_SERVICIO = S.ID "+
		"AND S.NOMBRE like ? "+
		"GROUP BY a.id, a.capacidad, a.estado, a.direccion, a.tipo_aloja "+
		"HAVING COUNT(*) = 1  order by a.id ");  

		q.setResultClass(Alojamiento.class);
		q.setParameters(fecha_llegada, fecha_Salida, fecha_llegada, fecha_Salida, servicio);
		return (List<Alojamiento>) q.executeList();
	}

	public List<Alojamiento> RFC9(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT a.id, a.capacidad, a.estado, a.direccion, a.tipo_aloja "+
		"FROM ALOJAMIENTO A "+
		"WHERE NOT EXISTS ( "+
		"	SELECT * "+
		"	FROM RESERVA R  "+
		"	WHERE R.ID_ALOJAMIENTO = A.ID "+
		"	AND R.FECHA_LLEGADA >= (SELECT MAX(FECHA_LLEGADA) FROM RESERVA WHERE ID_ALOJAMIENTO = A.ID) - INTERVAL '1' MONTH)");
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}

}
