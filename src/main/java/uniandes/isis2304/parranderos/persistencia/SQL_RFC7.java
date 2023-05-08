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
        Query q = pm.newQuery(SQL, "(SELECT fecha_llegada AS fecha, "+
        "COUNT(*) AS alojamientos_ocupados, "+
        "       SUM(PRECIO) AS ingresos_totales  "+
        "FROM reserva  "+
        "JOIN alojamiento ON reserva.id_alojamiento = alojamiento.id "+
        "WHERE alojamiento.tipo_aloja = 'tipo_de_alojamiento'  "+
        "  AND fecha_llegada BETWEEN 'fecha_inicio' AND 'fecha_fin'  "+
        "GROUP BY fecha_llegada  "+
        "ORDER BY alojamientos_ocupados DESC, ingresos_totales DESC  "+
        "FETCH FIRST 1 ROWS ONLY) "+
 
        "UNION  "+
 
        "(SELECT fecha_llegada AS fecha,  "+
        "        COUNT(*) AS alojamientos_ocupados,  "+
        "        SUM(PRECIO) AS ingresos_totales  "+
        " FROM reserva  "+
        "JOIN alojamiento ON reserva.id_alojamiento = alojamiento.id "+
        " WHERE alojamiento.tipo_aloja = 'tipo_de_alojamiento'  "+ 
        "   AND fecha_llegada BETWEEN 'fecha_inicio' AND 'fecha_fin'  "+ 
        " GROUP BY fecha_llegada  "+ 
        " ORDER BY alojamientos_ocupados ASC, ingresos_totales ASC  "+
        " FETCH FIRST 1 ROWS ONLY) "+
 
        "UNION  "+
 
        "(SELECT fecha_llegada AS fecha,  "+
        "       COUNT(*) AS alojamientos_ocupados,  "+
        "       SUM(PRECIO) AS ingresos_totales  "+
        "FROM reserva  "+
        " JOIN alojamiento ON reserva.id_alojamiento = alojamiento.id  "+
        " WHERE alojamiento.tipo_aloja = 'tipo_de_alojamiento'  "+
        "   AND fecha_llegada BETWEEN 'fecha_inicio' AND 'fecha_fin'  "+
        "GROUP BY fecha_llegada  "+
        " ORDER BY ingresos_totales DESC  "+
        " FETCH FIRST 1 ROWS ONLY)");   
        
        q.setResultClass(RFC7.class);
        q.setParameters(tipo_aloja, fecha_llegada, fecha_salida);
        return (List<RFC7>) q.executeList();
    }
    
}
