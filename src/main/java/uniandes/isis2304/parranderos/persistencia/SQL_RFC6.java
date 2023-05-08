package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.RFC6;

public class SQL_RFC6 {

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
    public SQL_RFC6 (PersistenciaAlohandes pp)
    {
        this.pp = pp;
    }

    public List<RFC6> RFC6 (PersistenceManager pm, long idCliente)
    {
        Query q = pm.newQuery(SQL, "SELECT "+
        "c.identificacion, "+
        "TRUNC(MONTHS_BETWEEN(r.fecha_salida, r.fecha_llegada) * 30) AS noches_contratados, "+
        "a.tipo_aloja, "+
        "SUM(r.precio) AS dinero_pagado "+
        "    FROM "+
        "cliente c  "+
        "INNER JOIN reserva r ON c.identificacion = r.id_cliente  "+
        "INNER JOIN alojamiento a ON r.id_alojamiento = a.id "+
        "    WHERE  "+
        "c.identificacion = ? "+
         "GROUP BY  "+
        "c.identificacion, a.tipo_aloja, TRUNC(MONTHS_BETWEEN(r.fecha_salida, r.fecha_llegada) * 30)");  
        q.setResultClass(RFC6.class);
        q.setParameters(idCliente);
        return (List<RFC6>) q.executeList();
    }
    
}
