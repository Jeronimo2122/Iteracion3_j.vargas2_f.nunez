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

package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.parranderos.negocio.Alohandes;
import uniandes.isis2304.parranderos.negocio.VOReserva;


/**
 * Clase con los métdos de prueba de funcionalidad sobre TIPOBEBIDA
 * @author Germán Bravo
 *
 */
public class ReservaTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ReservaTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private Alohandes alohandes;
	
    /* ****************************************************************
	 * 			Métodos de prueba para la tabla Reserva - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla TipoBebida
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */

	 /** 
    @Test
	public void CRDReservaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre TipoBebida");
			alohandes = new Alohandes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Tipobebida incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Tipobebida incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOReserva> lista = alohandes.darVOReservas();
			assertEquals ("No debe haber reservas creadas!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			long idReserva1 = 1;
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaLlegada1Str = "2023-01-01 00:00:00";
			Date parsedDate1 = (Date) dateFormat1.parse(fechaLlegada1Str);
			java.sql.Timestamp fechaLlegada1 = new java.sql.Timestamp(parsedDate1.getTime());

			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaFin1Str = "2023-01-05 00:00:00";
			Date parsedDate2 = (Date) dateFormat2.parse(fechaFin1Str);
			java.sql.Timestamp fechaFin1 = new java.sql.Timestamp(parsedDate2.getTime());

			VOReserva Reserva1 = alohandes.adicionarReserva( fechaLlegada1 , fechaFin1, 100000, 1, 1, 1, "Activa");
			lista = alohandes.darVOReservas();
			assertEquals ("Debe haber una reserva creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Reserva1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			long idReserva2 = 2;
			SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaLlegada2Str = "2023-02-01 00:00:00";
			Date parsedDate3 = (Date) dateFormat3.parse(fechaLlegada2Str);
			java.sql.Timestamp fechaLlegada2 = new java.sql.Timestamp(parsedDate3.getTime());

			SimpleDateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaFin2Str = "2023-01-05 00:00:00";
			Date parsedDate4 = (Date) dateFormat4.parse(fechaFin2Str);
			java.sql.Timestamp fechaFin2 = new java.sql.Timestamp(parsedDate4.getTime());


			VOReserva Reserva2 = alohandes.adicionarReserva (fechaLlegada2, fechaFin2, 200000, 2, 2, 2, "Cancelada");
			lista = alohandes.darVOReservas();
			assertEquals ("Debe haber dos tipos de bebida creados !!", 2, lista.size ());
			assertTrue ("El primer tipo de bebida adicionado debe estar en la tabla", Reserva1.equals (lista.get (0)) || Reserva1.equals (lista.get (1)));
			assertTrue ("El segundo tipo de bebida adicionado debe estar en la tabla", Reserva2.equals (lista.get (0)) || Reserva2.equals (lista.get (1)));

			// Prueba de eliminación de un tipo de bebida, dado su identificador
			long tbEliminados = alohandes.eliminarReservaPorId (Reserva1.getId ());
			assertEquals ("Debe haberse eliminado una reserva !!", 1, tbEliminados);
			lista = alohandes.darVOReservas();
			assertEquals ("Debe haber una sola reserva !!", 1, lista.size ());
			assertFalse ("La primer reserva NO debe estar en la tabla", Reserva1.equals (lista.get (0)));
			assertTrue ("La segunda reserva adicionada debe estar en la tabla", Reserva2.equals (lista.get (0)));
			
			// Prueba de eliminación de un tipo de bebida, dado su identificador
			tbEliminados = alohandes.eliminarReservaPorId (Reserva2.getId());
			assertEquals ("Debe haberse eliminado una reserva !!", 1, tbEliminados);
			lista = alohandes.darVOReservas();
			assertEquals ("La tabla debió quedar vacía !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Reserva.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Reserva");
		}
		finally
		{
			alohandes.limpiarAlohandes() ;
    		alohandes.cerrarUnidadPersistencia ();    		
		}
	}
	*/
	//no es necesario unicidad ya que su identificador es el id y este es system assign

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
