/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: ALOJANDES
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */



package uniandes.isis2304.parranderos.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.parranderos.negocio.Alohandes;
import uniandes.isis2304.parranderos.negocio.VOAlojamiento;
import uniandes.isis2304.parranderos.negocio.VOAlojamiento_Operador;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOOperador;
import uniandes.isis2304.parranderos.negocio.VOReserva;


@SuppressWarnings("serial")

public class InterfazAlohandesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazAlohandesDemo.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private Alohandes alohandes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazAlohandesDemo( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        alohandes = new Alohandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			Demos de Reserva
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Tipos de Bebida
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */

	/**
    public void demoReserva( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			long idReserva = 1;
			int idReservaInt = 1;
			boolean errorReserva = false;

			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaLlegada1Str = "2023-01-01 00:00:00";
			Date parsedDate1 = (Date) dateFormat1.parse(fechaLlegada1Str);
			java.sql.Timestamp fechaLlegada1 = new java.sql.Timestamp(parsedDate1.getTime());

			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String fechaFin1Str = "2023-01-05 00:00:00";
			Date parsedDate2 = (Date) dateFormat2.parse(fechaFin1Str);
			java.sql.Timestamp fechaFin1 = new java.sql.Timestamp(parsedDate2.getTime());

			VOReserva reserva = alohandes.adicionarReserva (fechaLlegada1, fechaFin1, 100000, 1, 1, 1, "Activa");
			if (reserva == null)
			{
				reserva = alohandes.darReservas().get(idReservaInt);
				errorReserva = true;
			}
			List <VOReserva> lista = alohandes.darVOReservas();
			long tbEliminados = alohandes.eliminarReservaPorId (reserva.getId ());
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de TipoBebida\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorReserva)
			{
				resultado += "*** Exception creando Reserva!!\n";
				resultado += "*** Es probable que ese tipo de bebida ya existiera y hay restricción de UNICIDAD sobre la Reserva\n";
				resultado += "*** Revise el log de alohandes para más detalles\n";
			}
			resultado += "Adicionada la reserva con id: " + idReserva + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarReservas (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += tbEliminados + " Tipos de bebida eliminados\n";
			resultado += "\n Demo terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	*/
	/* ****************************************************************
	 * 			REGISTRA de Cliente
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bebidas.
     * Incluye también los tipos de bebida pues el tipo de bebida es llave foránea en las bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */

	public void ReqCliente( )
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			String nombreCliente = JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
			String vinculo = JOptionPane.showInputDialog (this, "Vinculo del Cliente('ESTUDIANTE','PROFESOR','EMPLEADO','INVITADO','EGRESADO','PADRE','COLECTIVA')", "Registrar Cliente", JOptionPane.QUESTION_MESSAGE);
			vinculo = vinculo.toUpperCase();
			if (nombreCliente != null)
    		{
        		VOCliente cliente = alohandes.adicionarCliente(nombreCliente, vinculo);
				
        		if (cliente == null)
        		{
        			throw new Exception ("No se pudo crear un Cliente con nombre: " + nombreCliente);
        		}
        		String resultado = "En adicionarCliente\n\n";
        		resultado += "Cliente Registrado exitosamente: " + cliente;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
   
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			Registar Alojamiento con su Operador
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bebidas.
     * Incluye también los tipos de bebida pues el tipo de bebida es llave foránea en las bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */

	 public void ReqAlojamientoOperadorNuevos( )
	 {
		try 
		{
			 // Ejecución de la demo y recolección de los resultados
			 // ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
 
			String estado = JOptionPane.showInputDialog (this, "Estado del Alojamiento?[DISPONIBLE, OCUPADO]", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			int capacidad = Integer.parseInt(JOptionPane.showInputDialog (this, "Capacidad del Alojamiento", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE));
			String direccion = JOptionPane.showInputDialog (this, "Direccion del Alojamiento?", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			String tipo = JOptionPane.showInputDialog (this, "Tipo del Alojamiento?[VIVIENDA_U,HABITACION_HOTEL,HABITACION_HOSTAL,VIVIENDA,APARTAMENTO,HABITACION_VIVIENDA]", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			 
			String nombreOperador = JOptionPane.showInputDialog (this, "Nombre del Operador?", "Registrar Operador", JOptionPane.QUESTION_MESSAGE);
			Float ganacias = Float.parseFloat(JOptionPane.showInputDialog (this, "Ganacias del Operador", "Registrar Operador", JOptionPane.QUESTION_MESSAGE));

			if (estado != null && capacidad != 0 && direccion != null && tipo != null && nombreOperador != null && ganacias != 0)
			{
				VOAlojamiento Aloja = alohandes.adicionarAlojamiento(capacidad, estado, direccion, tipo);
				 
				if (Aloja == null)
				{
					throw new Exception ("No se pudo crear un Alojamiento de tipo: " + tipo);
				}
				String resultado = "En adicionarAlojamiento\n\n";
				resultado += "Alojamiento Registrado exitosamente: " + Aloja;
				//resultado += "\n Operación terminada";
				//panelDatos.actualizarInterfaz(resultado);

		
				VOOperador Op = alohandes.adicionarOperador(nombreOperador, ganacias);
					
				if (Op == null)
				{
					throw new Exception ("No se pudo crear un Operador de nombre: " + nombreOperador);
				}
				resultado += "\n En adicionarOperador\n\n";
				resultado += "Operador Registrado exitosamente: " + Op;

				VOAlojamiento_Operador Al_Op = alohandes.adicionarAlojamiento_Operador(Aloja.getId(), Op.getId());
				if (Al_Op == null)
				{
					throw new Exception ("No se pudo Asociar un Operador con el Alojamiento: " + nombreOperador);
				}
				resultado += "\n Asociar Operador y Alojamiento: ";
				resultado += "Operador y Alojamiento Asociados exitosamente: " + Al_Op;
				resultado += "\n Operación terminada";

				panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}

		} 
		catch (Exception e) 
		{
 //			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void ReqAlojamientoOperadorExistente( ) 
	{
		try 
		{
			 // Ejecución de la demo y recolección de los resultados
			 // ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
 
			String estado = JOptionPane.showInputDialog (this, "Estado del Alojamiento?[DISPONIBLE, OCUPADO]", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			int capacidad = Integer.parseInt(JOptionPane.showInputDialog (this, "Capacidad del Alojamiento", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE));
			String direccion = JOptionPane.showInputDialog (this, "Direccion del Alojamiento?", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			String tipo = JOptionPane.showInputDialog (this, "Tipo del Alojamiento?[VIVIENDA_U,HABITACION_HOTEL,HABITACION_HOSTAL,VIVIENDA,APARTAMENTO,HABITACION_VIVIENDA]", "Registrar Alojamiento", JOptionPane.QUESTION_MESSAGE);
			Long idOperador = Long.parseLong(JOptionPane.showInputDialog (this, "Id del Operador?", "Asociar Operador", JOptionPane.QUESTION_MESSAGE));


			if (estado != null && capacidad != 0 && direccion != null && tipo != null && idOperador != 0)
			{
				VOOperador ope = alohandes.darOperadorPorId(idOperador);
				if (ope == null)
				{
					throw new Exception ("No Existe el Operador para asociar: " + tipo);
				}
				String resultado = "En darOperadorPorId\n\n";
				resultado += "Operador: " + ope;

				VOAlojamiento Aloja = alohandes.adicionarAlojamiento(capacidad, estado, direccion, tipo); 
				if (Aloja == null)
				{
					throw new Exception ("No se pudo crear un Alojamiento de tipo: " + tipo);
				}
				resultado = "\nEn adicionarAlojamiento\n";
				resultado += "Alojamiento Registrado exitosamente: " + Aloja;
				//resultado += "\n Operación terminada";
				//panelDatos.actualizarInterfaz(resultado);

				VOAlojamiento_Operador Al_Op = alohandes.adicionarAlojamiento_Operador(Aloja.getId(), ope.getId());
				if (Al_Op == null)
				{
					throw new Exception ("No se pudo Asociar un Operador con el Alojamiento: " + ope.getNombre());
				}
				resultado += "\n Asociar Alojamiento al Operador: " + ope.getNombre();
				resultado += "\nOperador y Alojamiento Asociados exitosamente: " + Al_Op;
				resultado += "\n Operación terminada";

				panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}

		} 
		catch (Exception e) 
		{
 //			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void ReqEliminarAlojamiento( ) 
    {
    	try 
    	{
    		// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			long id_aloja = Long.parseLong(JOptionPane.showInputDialog (this, "id Alojamiento a eliminar?", "Eliminar Alojamiento", JOptionPane.QUESTION_MESSAGE));
			
			if (id_aloja != 0)
    		{
				log.info("Eliminando Alojamiento con id: " + id_aloja);
				long eliminadorreservas = alohandes.eliminarReservaPorIdAloja(id_aloja);
				long eliminadosaloja_operador = alohandes.eliminarAlojamiento_OperadorID_aloja(id_aloja);
        		long eliminadosaloja = alohandes.eliminarAlojamientoPorId(id_aloja);
				
        		if (eliminadosaloja == 0)
        		{
        			throw new Exception ("No se Elimino el alojamiento: " + eliminadosaloja);
        		}

        		String resultado = "En EliminarAlojamiento\n\n";
        		resultado += "Numero de alojamientos Eliminados " + eliminadosaloja+" AL_OP:"+eliminadosaloja_operador+" RESERVA: "+eliminadorreservas;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
   
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }


	/* ****************************************************************
	 * 			Registrar Reserva y Cancelar Reserva
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Bebidas.
     * Incluye también los tipos de bebida pues el tipo de bebida es llave foránea en las bebidas
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */

	public void ReqRegReserva()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código
			String fecha_llegada = JOptionPane.showInputDialog (this, "Fecha llegada de la reserva?FT[DD/MM/YYYY]", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			String fecha_Salida = JOptionPane.showInputDialog (this, "Fecha Salida de la reserva?FT[DD/MM/YYYY]", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			float precio = Float.parseFloat(JOptionPane.showInputDialog (this, "Precio de la Reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			long id_cliente = Long.parseLong(JOptionPane.showInputDialog (this, "id Cliente de la reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			long id_alojamiento = Long.parseLong(JOptionPane.showInputDialog (this, "id Alojamiento de la reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			String estado = JOptionPane.showInputDialog (this, "Esatdo de la Reserva?('ACTIVA', 'CANCELADA')", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);

			

			if (fecha_llegada != null && fecha_Salida != null && precio >= 0 && id_cliente !=0 && id_alojamiento !=0
			&& estado != null)
			{

				VOReserva Reserva = alohandes.adicionarReserva(fecha_llegada, fecha_Salida, precio,
				id_cliente, id_alojamiento, estado);
				 
				if (Reserva == null)
				{
					throw new Exception ("No se pudo crear La Reserva");
				}
				String resultado = "En adicionarRESERVA\n\n";
				resultado += "Reserva Registrada exitosamente: " + Reserva;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
	
		} 
		 catch (Exception e) 
		{
 //			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
 
	public void ReqCancelarReserva()
	{
		try 
		{
		
		
			long id_reserva = Long.parseLong(JOptionPane.showInputDialog (this, "id de la reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			

			if ( id_reserva >= 0 )
			{
				
				long ReservasCanceladas = alohandes.ActualizarReserva("CANCELADA", id_reserva);

				String resultado = "En CancelarReserva\n\n";
				resultado += "Reserva Canceladas exitosamente: " + ReservasCanceladas;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
	
		} 
		 catch (Exception e) 
		{
 //			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}



	}

	/* ****************************************************************
	 *                    REQ FUNCIONAL 7
	 *****************************************************************/

	public void Reqreservacolectiva () {
		try {
			
			
			int numAlojas = Integer.parseInt(JOptionPane.showInputDialog (this, "Numero de alojamientos en su reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			String tipo_Aloja = JOptionPane.showInputDialog (this, "Tipo de alojamiento de su reserva?[VIVIENDA_U,HABITACION_HOTEL,HABITACION_HOSTAL,VIVIENDA,APARTAMENTO,HABITACION_VIVIENDA]", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			String servicio = JOptionPane.showInputDialog (this, "Servicio de sus alojamiento? [WI-FI,TINA,GIMNASIO,ETC..]"," Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			
			String fecha_llegada = JOptionPane.showInputDialog (this, "Fecha llegada de la reserva?FT[DD/MM/YYYY]", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			String fecha_Salida = JOptionPane.showInputDialog (this, "Fecha Salida de la reserva?FT[DD/MM/YYYY]", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);
			float precio = Float.parseFloat(JOptionPane.showInputDialog (this, "Precio de la Reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			long id_cliente = Long.parseLong(JOptionPane.showInputDialog (this, "id Cliente de la reserva?", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE));
			String estado = JOptionPane.showInputDialog (this, "Esatdo de la Reserva?('ACTIVA', 'CANCELADA')", "Registrar Reserva", JOptionPane.QUESTION_MESSAGE);

			if (fecha_llegada != null && fecha_Salida != null && precio >= 0 && id_cliente !=0 
			&& estado != null)
			{

				long num = alohandes.reservacolectiva(servicio, tipo_Aloja, numAlojas, fecha_llegada, fecha_Salida, precio, id_cliente, estado);
				 
				if (num == 0)
				{
					throw new Exception ("No se pudo crear La Reserva");
				}
				String resultado = "En RESERVA_COLECTIVA\n\n";
				resultado += "Alojamientos Reservados exitosamente: " + num;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {

			// TODO: handle exception
		}
	}

	/* ****************************************************************
	 *                    REQ FUNCIONAL 8
	 *****************************************************************/
	public void CancelarReqreservacolectiva () {
		try {
			
			
			long id_cliente = Long.parseLong(JOptionPane.showInputDialog (this, "id Cliente de la reserva colectiva?", "Cancelar Reserva", JOptionPane.QUESTION_MESSAGE));

			if ( id_cliente !=0 )
			{

				ArrayList Costo = alohandes.CancelarReservaColectiva(id_cliente);
				
				if (Costo.size() == 2){

					if (Double.compare((Double)Costo.get(0), 0) == 0)
					{
						String resultado = "En CANCELAR RESERVA COLECTIVA\n\n";
						resultado += "Las Reservas ya fueron utilizadas ";
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}else
					{
						String resultado = "En CANCELAR RESERVA COLECTIVA\n\n";
						resultado += "Reservas Canceladas: "+ Costo.get(1);
						resultado += "\n Con un costo de cancelacion de: "+ Costo.get(0);
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					
				}
				else
				{
					String resultado = "En CANCELAR RESERVA COLECTIVA\n\n";
					resultado += "\n Error en la Operación ";
					panelDatos.actualizarInterfaz(resultado);
				}
				
				
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} catch (Exception e) {

			// TODO: handle exception
		}
	}

	/* ****************************************************************
	 *                    REQ FUNCIONAL 7
	 *****************************************************************/
	/* ****************************************************************
	 *                    REQ FUNCIONAL 7
	 *****************************************************************/





	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = alohandes.limpiarAlohandes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Alojamiento eliminados\n";
			resultado += eliminados [1] + " Alojamiento_Operador eliminados\n";
			resultado += eliminados [2] + " Alojamiento_Servicio eliminados\n";
			resultado += eliminados [3] + " Clientes eliminadas\n";
			resultado += eliminados [4] + " Operadores eliminados\n";
			resultado += eliminados [5] + " Reservas eliminados\n";
			resultado += eliminados [6] + " Servicios eliminados\n";
			resultado += eliminados [7] + " Personas eliminados\n";
			resultado += eliminados [8] + " Hotel_Hostal eliminados\n";
			resultado += eliminados [9] + " Hab_Hotel eliminados\n";
			resultado += eliminados [10] + " Hab_Hostal eliminadas\n";
			resultado += eliminados [11] + " Edificios Universitarios eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
    /**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Alohandes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Jeronimo Vargas Felipe Nuñez\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
    }
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    /**
     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los tipos de bebida
     * @return La cadena con una líea para cada tipo de bebida recibido
     */
    private String listarClientes(List<VOCliente> lista) 
    {
    	String resp = "Los tipos de bebida existentes son:\n";
    	int i = 1;
        for (VOCliente tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la lista de sirven recibida: una línea por cada sirven
     * @param lista - La lista con los sirven
     * @return La cadena con una líea para cada sirven recibido
     */
    private String listarReservas (List<VOReserva> lista) 
    {
    	String resp = "Las reservas existentes son:\n";
    	int i = 1;
        for (VOReserva serv : lista)
        {
        	resp += i++ + ". " + serv.toString() + "\n";
        }
        return resp;
	}



    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazAlohandesDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazAlohandesDemo interfaz = new InterfazAlohandesDemo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
