package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.RFC5;

public class SQL_RFC5 {

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
	public SQL_RFC5 (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

    public List<RFC5> RFC5 (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT VINCULO, COUNT(*) AS CANTIDAD_DE_USUARIOS "+
        "FROM ( "+
        "   SELECT VINCULO FROM PERSONA "+
        "   UNION ALL "+
        "   SELECT VINCULO FROM CLIENTE "+
        ") "+
        "GROUP BY VINCULO");  
		q.setResultClass(RFC5.class);
		q.setParameters();
		return (List<RFC5>) q.executeList();
	}
    
}
