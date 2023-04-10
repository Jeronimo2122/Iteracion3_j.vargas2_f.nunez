/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
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
	public SQLUtil (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqParranderos () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes (PersistenceManager pm)
	{
        Query qAlojamiento = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento());          
        Query qAlojamiento_Operador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Operador());
        Query qAlojamiento_Servicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamiento_Servicio());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
        Query qOperador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperador());
        Query qReserva = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
		Query qPersona = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaPersona());
		Query qHotel_Hostal = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaHotel_Hostal());
		Query qHab_Hotel = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaHab_Hotel());
		Query qHab_Hostal = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaHab_Hostal());
		Query qEdificio_Universitario = pm.newQuery(SQL, "DELETE FROM" + pp.darTablaEdificio_Universitario());

        long AlojamientosEliminados = (long) qAlojamiento.executeUnique ();
        long Alojamiento_OperadorsEliminados = (long) qAlojamiento_Operador.executeUnique ();
        long Alojamiento_ServiciosEliminadas = (long) qAlojamiento_Servicio.executeUnique ();
        long ClientesEliminadas = (long) qCliente.executeUnique ();
        long OperadoresEliminados = (long) qOperador.executeUnique ();
        long ReservasEliminados = (long) qReserva.executeUnique ();
        long ServiciosEliminados = (long) qServicio.executeUnique ();
		long PersonasEliminadas = (long) qPersona.executeUnique ();
		long Hoteles_HostalesEliminados = (long) qHotel_Hostal.executeUnique();
		long Habitaciones_HotelesEliminadas = (long) qHab_Hotel.executeUnique();
		long Habitaciones_HostalesEliminadas = (long) qHab_Hostal.executeUnique();
		long Edficios_UniversitariosEliminados = (long) qEdificio_Universitario.executeUnique();

        return new long[] {AlojamientosEliminados, Alojamiento_OperadorsEliminados, 
			Alojamiento_ServiciosEliminadas, ClientesEliminadas, OperadoresEliminados, 
			ReservasEliminados, ServiciosEliminados, PersonasEliminadas, Hoteles_HostalesEliminados,
			Habitaciones_HotelesEliminadas, Habitaciones_HostalesEliminadas, Edficios_UniversitariosEliminados};
	}

}
