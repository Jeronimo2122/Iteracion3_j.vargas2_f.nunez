package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.RFC8;

public class SQL_RFC8 {

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
    public SQL_RFC8 (PersistenciaAlohandes pp)
    {
        this.pp = pp;
    }


    public List<RFC8> RFC8 (PersistenceManager pm, long id_Alojamiento)
    {
        Query q = pm.newQuery(SQL, "SELECT C.IDENTIFICACION, COUNT(*) AS NUM_RESERVAS, SUM(TRUNC(MONTHS_BETWEEN(R.FECHA_SALIDA, R.FECHA_LLEGADA)*30)) AS NUM_NOCHES "+
        "FROM RESERVA R "+
        "INNER JOIN CLIENTE C ON R.ID_CLIENTE = C.IDENTIFICACION "+
        "WHERE R.ID_ALOJAMIENTO = ? "+
        "GROUP BY C.IDENTIFICACION "+
        "HAVING COUNT(*) >= 3 OR SUM(TRUNC(MONTHS_BETWEEN(R.FECHA_SALIDA, R.FECHA_LLEGADA)*30)) >= 15 "+
        "ORDER BY NUM_RESERVAS DESC");   
        
        q.setResultClass(RFC8.class);
        q.setParameters(id_Alojamiento);
        return (List<RFC8>) q.executeList();
    }
    
}
