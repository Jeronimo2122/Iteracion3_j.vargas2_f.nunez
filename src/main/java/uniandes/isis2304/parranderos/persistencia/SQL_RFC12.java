package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.RFC12;

public class SQL_RFC12 {

    /*
     * ****************************************************************
     * Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las
     * sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaAlohandes.SQL;

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia general de la aplicación
     */
    private PersistenciaAlohandes pp;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor
     * 
     * @param pp - El Manejador de persistencia de la aplicación
     */
    public SQL_RFC12(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    public List<RFC12> RFC12 (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT "+
        "        O1.SEMANA, "+ 
        "        O1.ID_ALOJAMIENTO AS OFERTA_MAYOR_OCUPACION, "+
        "        O2.ID_ALOJAMIENTO AS OFERTA_MENOR_OCUPACION, "+
        "        OP1.NOMBRE AS OPERADOR_MAS_SOLICITADO, "+
        "        OP2.NOMBRE AS OPERADOR_MENOS_SOLICITADO "+
        "    FROM "+
        "        ( "+
        "            SELECT SEMANA, MAX(NUM_RESERVAS) AS MAX_OCUPACION "+
        "            FROM "+
        "                ( "+
        "                    SELECT "+
        "                        ID_ALOJAMIENTO, "+
        "                        TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') AS SEMANA, "+
        "                        COUNT(*) AS NUM_RESERVAS "+
        "                    FROM "+
        "                       RESERVA "+
        "                   WHERE "+
        "                        ESTADO = 'ACTIVA' "+
        "                    GROUP BY "+
        "                       ID_ALOJAMIENTO, "+
        "                       TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') "+
        "                ) ocupacion_semanal "+
        "            GROUP BY SEMANA "+
        "        ) MAX_OCUPACION_SEMANAL "+
        "    INNER JOIN "+
        "       (  "+
        "            SELECT  "+
        "                ID_ALOJAMIENTO, "+
        "               TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') AS SEMANA, "+
        "               COUNT(*) AS NUM_RESERVAS "+
        "           FROM "+
        "               RESERVA "+
        "          WHERE "+
        "              ESTADO = 'ACTIVA' "+
        "          GROUP BY "+
        "              ID_ALOJAMIENTO, "+
        "              TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') "+
        "       ) O1 "+
        "       ON MAX_OCUPACION_SEMANAL.SEMANA = O1.SEMANA "+
        "       AND MAX_OCUPACION_SEMANAL.MAX_OCUPACION = O1.NUM_RESERVAS "+
        "  INNER JOIN "+
        "      ( "+
        "           SELECT SEMANA, MIN(NUM_RESERVAS) AS MIN_OCUPACION "+
        "          FROM "+
        "              ( "+
        "                  SELECT "+
        "                      ID_ALOJAMIENTO, "+
        "                      TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') AS SEMANA, "+
        "                      COUNT(*) AS NUM_RESERVAS  "+
        "                  FROM "+
        "                        RESERVA "+
        "               WHERE "+
        "                     ESTADO = 'ACTIVA' "+
        "                 GROUP BY "+
        "                     ID_ALOJAMIENTO, "+
        "                     TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') "+
        "             ) ocupacion_semanal "+
        "         GROUP BY SEMANA "+
        "      ) MIN_OCUPACION_SEMANAL "+
        "      ON O1.SEMANA = MIN_OCUPACION_SEMANAL.SEMANA "+
        "  INNER JOIN "+
        "      ( "+
        "          SELECT "+
        "ID_ALOJAMIENTO, "+
        "TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') AS SEMANA, "+
        "COUNT(*) AS NUM_RESERVAS "+
        "          FROM "+
        "              RESERVA "+
        "          WHERE "+
        "              ESTADO = 'ACTIVA' "+
        "GROUP BY "+
        "             ID_ALOJAMIENTO, "+
        "              TO_CHAR(FECHA_LLEGADA, 'IYYY-IW') "+
        "       ) O2 "+
        "       ON MIN_OCUPACION_SEMANAL.SEMANA = O2.SEMANA "+
        "       AND MIN_OCUPACION_SEMANAL.MIN_OCUPACION = O2.NUM_RESERVAS "+
        "  INNER JOIN "+
        "       ( "+
        "          SELECT "+
        "              AO.ID_OPERADOR, "+
        "              COUNT(*) AS NUM_SOLICITUDES "+
        "          FROM "+
        "              ALOJAMIENTO_OPERADOR AO "+
        "              INNER JOIN RESERVA R ON AO.ID_ALOJA = R.ID_ALOJAMIENTO "+
        "          WHERE "+
        "               R.ESTADO = 'ACTIVA' "+
        "           GROUP BY "+
        "              AO.ID_OPERADOR "+
        "      ) SO1 "+
        "      ON O1.ID_ALOJAMIENTO = SO1.ID_OPERADOR "+
        "INNER JOIN "+
        "       ( "+
        "          SELECT "+
            "             AO.ID_OPERADOR, "+
            "               COUNT(*) AS NUM_SOLICITUDES "+
            "           FROM "+
            "             ALOJAMIENTO_OPERADOR AO "+
            "             INNER JOIN RESERVA R ON AO.ID_ALOJA = R.ID_ALOJAMIENTO "+
            "         WHERE "+
            "             R.ESTADO = 'ACTIVA' "+
            "        GROUP BY "+
            "            AO.ID_OPERADOR "+
            "    ) SO2 "+
            "      ON O2.ID_ALOJAMIENTO = SO2.ID_OPERADOR "+
            "  INNER JOIN OPERADOR OP1 ON SO1.ID_OPERADOR = OP1.ID "+
            "  INNER JOIN OPERADOR OP2 ON SO2.ID_OPERADOR = OP2.ID");   
            
        q.setResultClass(RFC12.class);
        q.setParameters();
        return (List<RFC12>) q.executeList();
    }

}
