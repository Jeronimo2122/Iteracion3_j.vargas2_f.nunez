package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLCliente 
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
	public SQLCliente (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarCliente (PersistenceManager pm, long identificacion, String nombre, String vinculo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente () + "(identificacion, nombre, vinculo) values (?, ?, ?)");
        q.setParameters(identificacion, nombre, vinculo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorNombre (PersistenceManager pm, String nombreCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE nombre = ?");
        q.setParameters(nombreCliente);
        return (long) q.executeUnique();
	}

	
	public long eliminarClientePorId (PersistenceManager pm, long identificacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE identificacion = ?");
        q.setParameters(identificacion);
        return (long) q.executeUnique();
	}

	public Cliente darClientePorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE identificacion = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id);
		return (Cliente) q.executeUnique();
	}

	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombreCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE nombre = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombreCliente);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> darClientesPorVinculo (PersistenceManager pm, String vinculo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE vinculo = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(vinculo);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> RFC10_1 (PersistenceManager pm, String fecha_llegada, String fecha_Salida, long id_alojamiento)
	{
		Query q = pm.newQuery(SQL, "SELECT CLIENTE.* "+
		"FROM CLIENTE, RESERVA "+
		"WHERE CLIENTE.IDENTIFICACION = RESERVA.ID_CLIENTE "+
		"AND RESERVA.FECHA_LLEGADA >= TO_DATE(?,'DD/MM/YYYY')  "+
		"AND RESERVA.FECHA_SALIDA <= TO_DATE(?,'DD/MM/YYYY') "+
		"AND RESERVA.ID_ALOJAMIENTO = ? "+
		"GROUP BY CLIENTE.IDENTIFICACION, CLIENTE.NOMBRE, CLIENTE.VINCULO"); 
		q.setResultClass(Cliente.class);
		q.setParameters(fecha_llegada, fecha_Salida, id_alojamiento);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> RFC10_2 (PersistenceManager pm, String fecha_llegada, String fecha_Salida, String Tipo_Aloja)
	{
		Query q = pm.newQuery(SQL, "SELECT C.* "+
		"FROM CLIENTE C, RESERVA R, ALOJAMIENTO A "+
		"WHERE C.IDENTIFICACION = R.ID_CLIENTE AND A.ID = R.ID_ALOJAMIENTO "+
		"	AND R.FECHA_LLEGADA >= TO_DATE('01/05/2023','DD/MM/YYYY')  "+
		"	AND R.FECHA_SALIDA <= TO_DATE('01/12/2023','DD/MM/YYYY') "+
		"	AND A.TIPO_ALOJA = 'HABITACION_HOSTAL' "+
		"GROUP BY C.IDENTIFICACION, C.NOMBRE, C.VINCULO"); 
		q.setResultClass(Cliente.class);
		q.setParameters(fecha_llegada, fecha_Salida, Tipo_Aloja);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> RFC11_1 (PersistenceManager pm, String fecha_llegada, String fecha_Salida, String Tipo_Aloja)
	{
		Query q = pm.newQuery(SQL, "SELECT C.IDENTIFICACION, C.NOMBRE, C.VINCULO "+
		"FROM CLIENTE C "+
		"WHERE C.IDENTIFICACION NOT IN ( "+
		"  SELECT R.ID_CLIENTE "+
		"  FROM RESERVA R, ALOJAMIENTO A "+
		"  WHERE R.ID_ALOJAMIENTO = A.ID  "+
		" AND A.TIPO_ALOJA = ? "+
		"  AND R.FECHA_LLEGADA >= TO_DATE(?,'DD/MM/YYYY') "+
		"  AND R.FECHA_SALIDA <= TO_DATE(?,'DD/MM/YYYY') "+
		") "+
		"ORDER BY C.IDENTIFICACION, C.VINCULO, C.NOMBRE"); 
		q.setResultClass(Cliente.class);
		q.setParameters(Tipo_Aloja, fecha_llegada, fecha_Salida);
		return (List<Cliente>) q.executeList();
	}

	

	
}