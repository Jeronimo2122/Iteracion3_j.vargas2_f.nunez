package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.RFC13;


public class SQL_RFC13 {

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
    public SQL_RFC13 (PersistenciaAlohandes pp)
    {
        this.pp = pp;
    }

    public List<RFC13> RFC13 (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT DISTINCT C.IDENTIFICACION, C.NOMBRE, C.VINCULO, "+
        "CASE WHEN MONTLY_RESERVAS.ID_CLIENTE IS NOT NULL THEN 'Realiza reservas mensuales' ELSE NULL END AS Calificacion_Reservas_Mensuales, "+
        "CASE WHEN COSTOSO_RESERVAS.ID_CLIENTE IS NOT NULL THEN 'Reserva alojamientos costosos' ELSE NULL END AS Calificacion_Reservas_Costosas, "+
        "CASE WHEN SUITE_RESERVAS.ID_CLIENTE IS NOT NULL THEN 'Reserva suites' ELSE NULL END AS Calificacion_Reservas_Suites "+
        "FROM CLIENTE C "+
        "LEFT JOIN ( "+
        "SELECT ID_CLIENTE "+
        " FROM ( "+
        " SELECT ID_CLIENTE, EXTRACT(MONTH FROM FECHA_LLEGADA) AS MES, COUNT(*) AS NUM_RESERVAS "+
        "FROM RESERVA "+
        "GROUP BY ID_CLIENTE, EXTRACT(MONTH FROM FECHA_LLEGADA) "+
        " ) RESERVAS_MENSUALES "+
        "WHERE NUM_RESERVAS > 0 "+
        ") MONTLY_RESERVAS ON C.IDENTIFICACION = MONTLY_RESERVAS.ID_CLIENTE "+
        "LEFT JOIN ( "+
        "SELECT ID_CLIENTE "+
        "FROM ( "+
        "SELECT ID_CLIENTE, COUNT(*) AS NUM_RESERVAS_COSTOSAS "+
        "FROM RESERVA "+
        "WHERE PRECIO > 150 "+
        "GROUP BY ID_CLIENTE "+
        ") RESERVAS_COSTOSAS  "+
        ") COSTOSO_RESERVAS ON C.IDENTIFICACION = COSTOSO_RESERVAS.ID_CLIENTE "+
        "LEFT JOIN ( "+
        " SELECT ID_CLIENTE "+
        "FROM ( "+
        "SELECT ID_CLIENTE, COUNT(*) AS NUM_RESERVAS_SUITE "+
        "FROM RESERVA R "+
        "JOIN HAB_HOTEL HH ON R.ID_ALOJAMIENTO = HH.ID_ALOJA "+
        "WHERE HH.CATEGORIA = 'SUITE' "+
        "GROUP BY ID_CLIENTE "+
        ") RESERVAS_SUITE "+
        ") SUITE_RESERVAS ON C.IDENTIFICACION = SUITE_RESERVAS.ID_CLIENTE "+
        "WHERE MONTLY_RESERVAS.ID_CLIENTE IS NOT NULL  "+
        "OR COSTOSO_RESERVAS.ID_CLIENTE IS NOT NULL "+
        "OR SUITE_RESERVAS.ID_CLIENTE IS NOT NULL ");   
        
        q.setResultClass(RFC13.class);
        q.setParameters();
        return (List<RFC13>) q.executeList();
    }
    
}
