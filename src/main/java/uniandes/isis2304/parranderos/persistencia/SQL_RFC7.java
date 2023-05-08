package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.RFC7;

public class SQL_RFC7 {

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
    public SQL_RFC7 (PersistenciaAlohandes pp)
    {
        this.pp = pp;
    }

    public List<RFC7> RFC7 (PersistenceManager pm, String tipo_aloja, String fecha_llegada, String fecha_salida)
    {
        Query q = pm.newQuery(SQL, "SELECT TO_CHAR(FECHA_LLEGADA, 'MM/YYYY') AS MES, "+
        "COUNT(ID_ALOJAMIENTO) AS ALOJAMIENTOS_OCUPADOS, "+
        "SUM(PRECIO) AS INGRESOS, "+
        "SUM(CAPACIDAD) AS OCUPACION "+
        "FROM RESERVA JOIN ALOJAMIENTO ON RESERVA.ID_ALOJAMIENTO = ALOJAMIENTO.ID  "+
        "WHERE FECHA_LLEGADA BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') "+
        "AND ALOJAMIENTO.TIPO_ALOJA = ? "+
        "GROUP BY TO_CHAR(FECHA_LLEGADA, 'MM/YYYY') "+
        "ORDER BY MES, ALOJAMIENTOS_OCUPADOS,INGRESOS DESC, OCUPACION DESC");   
        
        q.setResultClass(RFC7.class);
        q.setParameters(fecha_llegada, fecha_salida, tipo_aloja);
        return (List<RFC7>) q.executeList();
    }
    
}
