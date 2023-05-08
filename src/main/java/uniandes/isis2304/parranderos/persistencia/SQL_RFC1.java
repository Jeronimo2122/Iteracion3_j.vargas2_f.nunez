package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.RFC1;


public class SQL_RFC1 {

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
	public SQL_RFC1 (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	public List<RFC1> RFC1 (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * "+
		"FROM (SELECT operador.id, OPERADOR.NOMBRE AS OPERADOR,SUM(PRECIO) AS DINERO_RECIBIDO_CORRIDO "+
		"FROM RESERVA, OPERADOR, ALOJAMIENTO_OPERADOR "+
		"WHERE ALOJAMIENTO_OPERADOR.ID_OPERADOR = OPERADOR.ID AND RESERVA.ID_ALOJAMIENTO = ALOJAMIENTO_OPERADOR.ID_ALOJA "+
		"GROUP BY operador.id, OPERADOR.NOMBRE) NATURAL JOIN "+
		"(SELECT operador.id, OPERADOR.NOMBRE AS OPERADOR,SUM(PRECIO) AS DINERO_RECIBIDO_ANIO_ACTUAL "+
		"FROM RESERVA, OPERADOR, ALOJAMIENTO_OPERADOR "+
		"WHERE ALOJAMIENTO_OPERADOR.ID_OPERADOR = OPERADOR.ID AND RESERVA.ID_ALOJAMIENTO = ALOJAMIENTO_OPERADOR.ID_ALOJA AND "+
		"RESERVA.fecha_llegada BETWEEN TO_DATE('01/01/2023','DD/MM/YYYY') AND TO_DATE('31/12/2023','DD/MM/YYYY') "+
		"AND RESERVA.fecha_salida  BETWEEN TO_DATE('01/01/2023','DD/MM/YYYY') AND TO_DATE('31/12/2023','DD/MM/YYYY') "+
		"GROUP BY operador.id, OPERADOR.NOMBRE)");  
		q.setResultClass(RFC1.class);
		q.setParameters();
		return (List<RFC1>) q.executeList();
	}
   
    
}
