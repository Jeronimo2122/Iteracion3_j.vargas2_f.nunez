
package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Operador;
import uniandes.isis2304.parranderos.negocio.Alojamiento;
import uniandes.isis2304.parranderos.negocio.Servicio;
import uniandes.isis2304.parranderos.negocio.Reserva;
import uniandes.isis2304.parranderos.negocio.Alojamiento_Servicio;
import uniandes.isis2304.parranderos.negocio.Alojamiento_Operador;
import uniandes.isis2304.parranderos.negocio.Hotel_Hostal;
import uniandes.isis2304.parranderos.negocio.Edificio_Universitario;
import uniandes.isis2304.parranderos.negocio.Hab_Hotel;
import uniandes.isis2304.parranderos.negocio.Hab_Hostal;
import uniandes.isis2304.parranderos.negocio.Persona;

/**
 * 
 */
public class PersistenciaAlohandes{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	private SQLCliente sqlCliente;

	private SQLAlojamiento sqlAlojamiento;

	private SQLOperador sqlOperador;

	private SQLServicio sqlServicio;

	private SQLReserva sqlReserva;

	private SQLAlojamiento_Operador sqlAlojamiento_Operador;

	private SQLAlojamiento_Servicio sqlAlojamiento_Servicio;

	private SQLHotel_Hostal sqlHotel_Hostal;

	private SQLEdificio_Universitario sqlEdificio_Universitario;

	private SQLHab_Hotel sqlHab_Hotel;

	private SQLHab_Hostal sqlHab_Hostal;

	private SQLPersona sqlPersona;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");
		tablas.add ("CLIENTE");
		tablas.add ("OPERADOR");
		tablas.add ("ALOJAMIENTO");
		tablas.add ("SERVICIO");
		tablas.add ("RESERVA");
		tablas.add ("ALOJAMINETO_SERVICIO");
		tablas.add ("ALOJAMINETO_OPERADOR");
		tablas.add ("HOTEL_HOSTAL");
		tablas.add ("EDIFICIO_UNIVERSITARIO");
		tablas.add ("HAB_HOTEL");
		tablas.add ("HAB_HOSTAL");
		tablas.add ("PERSONA");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlCliente = new SQLCliente(this);
		sqlAlojamiento = new SQLAlojamiento(this);
		sqlOperador = new SQLOperador(this);
		sqlServicio = new SQLServicio(this);
		sqlReserva = new SQLReserva(this);
		sqlAlojamiento_Operador = new SQLAlojamiento_Operador (this);
		sqlAlojamiento_Servicio = new SQLAlojamiento_Servicio (this);	
		sqlHotel_Hostal = new SQLHotel_Hostal(this);
		sqlEdificio_Universitario = new SQLEdificio_Universitario(this);
		sqlHab_Hotel = new SQLHab_Hotel(this);
		sqlHab_Hostal = new SQLHab_Hostal(this);
		sqlPersona = new SQLPersona(this);
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaCliente()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaOperador ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaAlojamiento ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaServicio ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaReserva ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaAlojamiento_Servicio ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAlojamiento_Operador ()
	{
		return tablas.get (7);
	}

		/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHotel_Hostal ()
	{
		return tablas.get (8);
	}
	
			/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaEdificio_Universitario ()
	{
		return tablas.get (9);
	}
				/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHab_Hotel ()
	{
		return tablas.get (10);
	}

					/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHab_Hostal ()
	{
		return tablas.get (11);
	}

	
					/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPersona ()
	{
		return tablas.get (12);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Clientes
	 *****************************************************************/
	/**
	 * Método que adeiciona, de manera transaccional, una tupla en la tabla Cliente.
	 */
	public Cliente adicionarCliente(String nombre, String vinculo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id, nombre, vinculo);
            tx.commit();
            
            log.trace ("Inserción de Cliente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente (id, nombre, vinculo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Cliente, dado el id Cliente
	 */
	public long eliminarClientePorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del Cliente
	 */
	public long eliminarClientePorId (long identificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorId(pm, identificacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Cliente
	 * @return La lista de objetos cLIENTE, construidos con base en las tuplas de la tabla CLIENTE
	 */
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
	/**
	 * Método que consulta todas las tuplas en la tabla Cliente
	 * @return La lista de objetos cLIENTE, construidos con base en las tuplas de la tabla CLIENTE
	 */
	public List<Cliente> darClientesPorVinculo (String vinculo)
	{
		return sqlCliente.darClientesPorVinculo(pmf.getPersistenceManager(),vinculo);
	}
	/**
	 * Método que consulta todas las tuplas en la tabla Cliente que tienen el nombre dado
	 * @return La lista de objetos Cliente, construidos con base en las tuplas de la tabla CLIENTE
	 */
	public List<Cliente> darClientePorNombre (String nombre)
	{
		return sqlCliente.darClientesPorNombre(pmf.getPersistenceManager(), nombre);
	}
	/**
	 * Método que consulta todas las tuplas en la tabla Cliente con un identificador dado
	*/
	public Cliente darClientesPorId (long identificacion)
	{
		return sqlCliente.darClientePorId(pmf.getPersistenceManager(), identificacion);
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar los Operadores
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Operador
	 */
	public Operador adicionarOperador (String nombre, float ganancias)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlOperador.adicionarOperador(pm, id, nombre, ganancias);
			tx.commit();
			
			log.trace ("Inserción de Operador: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			
			return new Operador (id, nombre, ganancias);
		}
		catch (Exception e)
		 {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Operador, dado el nombre del Operdor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOperadorPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOperador.eliminarOperadorPorNombre (pm, nombre);
			tx.commit();

			return resp;
		}
		catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Operador, dado el identificador de la Operador
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOperadorPorId (long idOperador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOperador.eliminarOperadorPorId(pm, idOperador);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Operador que tienen el nombre dado
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public List<Operador> darOperadorPorNombre (String nombreOperador)
	{
		return sqlOperador.darOperadoresPorNombre(pmf.getPersistenceManager(), nombreOperador);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Operador
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public List<Operador> darOperadores ()
	{
		return sqlOperador.darOperadores(pmf.getPersistenceManager());
	}

	public Operador darOperadorPorId (long id)
	{
		return sqlOperador.darOperadorPorId(pmf.getPersistenceManager(),id);
	}
 

	/* ****************************************************************
	 * 			Métodos para manejar los ALOJAMIENTOS
	 *****************************************************************/

	public Alojamiento adicionarAlojamiento(int capacidad, String estado, String direccion, String tipo_Aloja) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pmf.getPersistenceManager(), id, capacidad, estado, direccion, tipo_Aloja);
            tx.commit();

            log.trace ("Inserción de Alojamiento: " + tipo_Aloja + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Alojamiento(id, capacidad, estado, direccion, tipo_Aloja);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Alojamiento, dado el nombre del bebedor
	 */
	public long eliminarAlojamientoPorTipoAloja(String tipo_Aloja) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamiento.eliminarAlojamientoPorTipoAlojamiento(pm, tipo_Aloja);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BEBEDOR, dado el identificador del bebedor
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlojamientoPorID (long idAloja) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamiento.eliminarAlojamientoPorId(pm, idAloja);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el nombre dado
	 * @param nombreBebedor - El nombre del bebedor
	 * @return La lista de objetos BEBEDOR, construidos con base en las tuplas de la tabla BEBEDOR
	 */
	public List<Alojamiento> darAlojamientosPorTipo(String tipoAloja) 
	{
		return sqlAlojamiento.darAlojamientosPorTipo(pmf.getPersistenceManager(), tipoAloja);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public Alojamiento darAlojamientoPorId (long idAloja) 
	{
		return (Alojamiento) sqlAlojamiento.darAlojamientoPorId(pmf.getPersistenceManager(), idAloja);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR
	 * @return La lista de objetos BEBEDOR, construidos con base en las tuplas de la tabla BEBEDOR
	 */
	public List<Alojamiento> darAlojamientos ()
	{
		return sqlAlojamiento.darAlojamientos(pmf.getPersistenceManager());
	}
 
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Servicios
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla BAR
	 */
	public Servicio adicionarServicio(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id, nombre);
            tx.commit();

            log.trace ("Inserción de Bar: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Servicio (id, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioPorNombre (String nombreServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorNombre(pm, nombreServicio);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el identificador del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorId(pm, id);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Servicios
	 * @return La lista de objetos Servicios, construidos con base en las tuplas de la tabla Servicos
	 */
	public List<Servicio> darServicios ()
	{
		return sqlServicio.darServicios (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Servicios que tienen el nombre dad
	 */
	public Servicio darServicioPorId (long idBar)
	{
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idBar);
	}
 	
	/* ****************************************************************
	 * 			Métodos para manejar la relación RESERVA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla RESERVA
	 */
	public Reserva adicionarReserva(Timestamp fecha_llegada, Timestamp fecha_salida, int precio, long Id_Cliente, long Id_Alojamiento, long Id_Operador, String estado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long Id = nextval ();
			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, Id, fecha_llegada, fecha_salida, precio, Id_Cliente, Id_Alojamiento, Id_Operador, estado);
			tx.commit();
			log.trace ("Inserción de reserva: [" + Id + ", " + Id_Alojamiento + "]. " + tuplasInsertadas + " tuplas insertadas");

			return new Reserva(Id, fecha_llegada, fecha_salida, precio, Id_Cliente, Id_Alojamiento, Id_Operador, estado);
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificador de la reserva
	 */
	public long eliminarReservaPorId(long idReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReserva.eliminarReservaPorId(pm, idReserva);           
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificador de la reserva
	 */
	public long eliminarReservaPoridAloja(long id_Alojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReserva.eliminarReservaPorAlojamiento(pm, id_Alojamiento);           
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	/**
	 * Método que consulta todas las tuplas en la tabla RESREVA
	 * @return La lista de objetos Reserva, construidos con base en las tuplas de la tabla RESERAV
	 */
	public List<Reserva> darReservas ()
	{
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}

	public Reserva darReservaPorId (long idReserva)
	{
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(),idReserva);
	}
 
 
	/* ****************************************************************
	 * 			Métodos para manejar la relación Alojamiento Servicio
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Alojamiento-Servicio
	 */
	public Alojamiento_Servicio adicionarAlojamiento_Servicio (long id_Alojamiento, long id_Servicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlAlojamiento_Servicio.adicionarAlojamiento_Servicio(pmf.getPersistenceManager(),
			 id_Alojamiento, id_Servicio);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id_Alojamiento + ", " + id_Servicio + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Alojamiento_Servicio(id_Alojamiento, id_Servicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_ServicioPorIdServicio (long idservicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Servicio.eliminarAlojamiento_ServicioPorId_Servicio(pm, idservicio) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_ServicioPoridAlojamiento (long id_Alojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Servicio.eliminarAlojamiento_ServiciorPorId_Aloja(pm, id_Alojamiento) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_Servicio (long id_Alojamiento, long id_Servicio ) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Servicio.eliminarAlojamiento_Servicio(pm, id_Alojamiento, id_Servicio) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Servicio> darAlojamiento_ServicioS()
	{
		return sqlAlojamiento_Servicio.darAlojamiento_ServicioS(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Servicio> darAlojamiento_ServicioPorIdAloja (long id_Alojamiento)
	{
		return sqlAlojamiento_Servicio.darAlojamiento_ServicioPorid_Aloja(pmf.getPersistenceManager(), id_Alojamiento);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Servicio> darAlojamiento_ServicioPorIdServicio (long id_Servicio)
	{
		return sqlAlojamiento_Servicio.darAlojamiento_ServicioPorId_Servicio(pmf.getPersistenceManager(), id_Servicio);
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public Alojamiento_Servicio darAlojamiento_Servicio (long id_Aloja, long id_Servicio)
	{
		return sqlAlojamiento_Servicio.darAlojamiento_Servicio(pmf.getPersistenceManager(), id_Aloja, id_Servicio);
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar la relación Alojamiento_Operador
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Alojamiento-Servicio
	 */
	public Alojamiento_Operador adicionarAlojamiento_Operador (long id_Alojamiento, long id_Operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlAlojamiento_Operador.adicionarAlojamiento_Operador(pmf.getPersistenceManager(),
			 id_Alojamiento, id_Operador);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id_Alojamiento + ", " + id_Operador + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Alojamiento_Operador(id_Alojamiento, id_Operador);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_OperadorPorIdPerador (long id_operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Operador.eliminarAlojamiento_OperadorPorId_Operador(pm, id_operador) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_OperadorPoridAlojamiento (long id_Alojamiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Operador.eliminarAlojamiento_OperadorPorId_Aloja(pm, id_Alojamiento) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVA, dados los identificadores de ALOJA y SERVICIO
	 */
	public long eliminarAlojamiento_Operador (long id_Alojamiento, long id_Operador ) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlAlojamiento_Operador.eliminarAlojamiento_Operador(pm, id_Alojamiento, id_Operador) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Operador> darAlojamiento_Operadors ()
	{
		return sqlAlojamiento_Operador.darAlojamiento_Operadors(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Operador> darAlojamiento_OperadorPorIdAloja (long id_Alojamiento)
	{
		return sqlAlojamiento_Operador.darAlojamiento_OperadorPorId_Operador(pmf.getPersistenceManager(), id_Alojamiento);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public List<Alojamiento_Operador> darAlojamiento_OperadorPorIdServicio (long id_Operador)
	{
		return sqlAlojamiento_Operador.darAlojamiento_OperadorPorId_Operador(pmf.getPersistenceManager(), id_Operador);
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public Alojamiento_Operador darAlojamiento_Operador (long id_Aloja, long id_Operador)
	{
		return sqlAlojamiento_Operador.darAlojamiento_Operador(pmf.getPersistenceManager(), id_Aloja, id_Operador);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Hotel_Hostal
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Hotel_Hostal
	 */
	public Hotel_Hostal adicionarHotel_Hostal (long id, String estadoLegal, int numeroRegistroCC, int numHabitaciones, int numHabitacionesDisponibles, String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHotel_Hostal.adicionarHotel_Hostal(pmf.getPersistenceManager(),
			 id, estadoLegal, numeroRegistroCC, numHabitaciones, numHabitacionesDisponibles, direccion);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id + ", " + estadoLegal + ", " + numeroRegistroCC + ", " + numHabitaciones + ", " + numHabitacionesDisponibles +
			", " + direccion + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Hotel_Hostal(id, estadoLegal, numeroRegistroCC, numHabitaciones, numHabitacionesDisponibles, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Hotel_Hostal, dado el id de Hotel_Hostal
	 */
	public long eliminarHotel_HostalPorId (long idHotel_Hostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlHotel_Hostal.eliminarHotel_HostalPorId(pm, idHotel_Hostal) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla Hotel_Hostal
	 * @return La lista de objetos Hotel_Hostal, construidos con base en las tuplas de la tabla Hotel_Hostal
	 */
	public List<Hotel_Hostal> darHoteles_Hostales ()
	{
		return sqlHotel_Hostal.darHoteles_Hostales(pmf.getPersistenceManager());
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Alojamiento-Servicio
	 * @return La lista de objetos Alojamiento_Servicio, construidos con base en las tuplas de la tabla Alojamiento-Servicio
	 */
	public Hotel_Hostal darHotel_Hostal (long idHotel_Hostal)
	{
		return sqlHotel_Hostal.darHotel_HostalPorId(pmf.getPersistenceManager(), idHotel_Hostal);
	}

		/* ****************************************************************
	 * 			Métodos para manejar la relación Hab_Hotel
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Hab_Hotel
	 */
	public Hab_Hotel adicionarHab_Hotel (long id_Aloja, String categoria) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHab_Hotel.adicionarHab_Hotel(pmf.getPersistenceManager(),
			 id_Aloja, categoria);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id_Aloja + ", " + categoria + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Hab_Hotel(id_Aloja, categoria);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Hab_Hotel, dado el id de Hab_Hotel
	 */
	public long eliminarHab_HotelPorId (long idHab_Hotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlHab_Hotel.eliminarHab_HotelPorId(pm, idHab_Hotel) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla Hab_Hotel
	 * @return La lista de objetos Hotel_Hostal, construidos con base en las tuplas de la tabla Hab_Hotel
	 */
	public List<Hab_Hotel> darHabs_Hoteles ()
	{
		return sqlHab_Hotel.darHabs_Hoteles(pmf.getPersistenceManager());
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Hab_Hotel
	 * @return La lista de objetos Hab_Hotel, construidos con base en las tuplas de la tabla Hab_Hotel
	 */
	public Hab_Hotel darHab_HotelPorId (long idHab_Hotel)
	{
		return sqlHab_Hotel.darHab_HotelPorId(pmf.getPersistenceManager(), idHab_Hotel);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Hab_Hostal
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Hab_Hostal
	 */
	public Hab_Hostal adicionarHab_Hostal (long id_Aloja, SimpleDateFormat horaApertura, SimpleDateFormat horaCierre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHab_Hostal.adicionarHab_Hostal(pmf.getPersistenceManager(),
			 id_Aloja, horaApertura, horaCierre);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id_Aloja + ", " + horaApertura + ", " + horaCierre + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Hab_Hostal(id_Aloja, horaApertura, horaCierre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Hab_Hostal, dado el id de Hab_Hostal
	 */
	public long eliminarHab_HostalPorId (long idHab_Hostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlHab_Hostal.eliminarHab_HostalPorId(pm, idHab_Hostal) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla Hab_Hostal
	 * @return La lista de objetos Hab_Hostal, construidos con base en las tuplas de la tabla Hab_Hostal
	 */
	public List<Hab_Hostal> darHabs_Hostales ()
	{
		return sqlHab_Hostal.darHabs_Hostales(pmf.getPersistenceManager());
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Hab_Hostal
	 * @return La lista de objetos Hab_Hostal, construidos con base en las tuplas de la tabla Hab_Hostal
	 */
	public Hab_Hostal darHab_HostalPorId (long idHab_Hostal)
	{
		return sqlHab_Hostal.darHab_HostalPorId(pmf.getPersistenceManager(), idHab_Hostal);
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Edificio_Universitario
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Edificio_Universitario
	 */
	public Edificio_Universitario adicionarEdificio_Universitario (long id, int numViviendas, int numViviendasDisponibles, String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlEdificio_Universitario.adicionarEdificio_Universitario(pmf.getPersistenceManager(),
			 id, numViviendas, numViviendasDisponibles, direccion);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id + ", " + numViviendas + ", " + numViviendasDisponibles + ", " + direccion + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Edificio_Universitario(id, numViviendas, numViviendasDisponibles, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Edificio_Universitario, dado el id de Edificio_Universitario
	 */
	public long eliminarEdificio_UniversitarioPorId (long idEdificio_Universitario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlEdificio_Universitario.eliminarEdificio_UniversitarioPorId(pm, idEdificio_Universitario) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla Edificio_Universitario
	 * @return La lista de objetos Edificio_Universitario, construidos con base en las tuplas de la tabla Edificio_Universitario
	 */
	public List<Edificio_Universitario> darEdificios_Universitarios ()
	{
		return sqlEdificio_Universitario.darEdificios_Universitarios(pmf.getPersistenceManager());
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Edificio_Universitario
	 * @return La lista de objetos Edificio_Universitario, construidos con base en las tuplas de la tabla Edificio_Universitario
	 */
	public Edificio_Universitario darEdificio_UniversitarioPorId (long idEdificio_Universitario)
	{
		return sqlEdificio_Universitario.darEdificio_UniversitarioPorId(pmf.getPersistenceManager(), idEdificio_Universitario);
	}

		/* ****************************************************************
	 * 			Métodos para manejar la relación Persona
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Persona
	 */
	public Persona adicionarPersona (long id, long Identificacion, String vinculo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPersona.adicionarPersona(pmf.getPersistenceManager(),
			 id, Identificacion, vinculo);
    		tx.commit();

            log.trace ("Inserción de gustan: [" + id + ", " + Identificacion + ", " + vinculo + ", " + "]. " + tuplasInsertadas + " tuplas insertadas");
            return new Persona(id, Identificacion, vinculo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Persona, dado el id de Persona
	 */
	public long eliminarPersonaPorId (long idPersona) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlPersona.eliminarPersonaPorId(pm, idPersona) ;	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 

	/**
	 * Método que consulta todas las tuplas en la tabla Persona
	 * @return La lista de objetos Persona, construidos con base en las tuplas de la tabla Persona
	 */
	public List<Persona> darPersonas ()
	{
		return sqlPersona.darPersonas(pmf.getPersistenceManager());
	}

		/**
	 * Método que consulta todas las tuplas en la tabla Persona
	 * @return La lista de objetos Persona, construidos con base en las tuplas de la tabla Persona
	 */
	public Persona darPersonaPorId (long idPersona)
	{
		return sqlPersona.darPersonaPorId(pmf.getPersistenceManager(), idPersona);
	}

	public long [] limpiarAlohandes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarAlohandes(pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	

 }
