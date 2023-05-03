package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;
public class Alohandes{

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes ()
	{
		pp = PersistenciaAlohandes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Alohandes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohandes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Clientes
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Cliente
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Cliente
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente (String nombre, String vinculo)
	{
        log.info ("Adicionando Cliente: " + nombre);
        Cliente cliente = pp.adicionarCliente(nombre, vinculo);		
        log.info ("Adicionando Cliente:" + nombre);
        return cliente;
	}
	/**
	 * Elimina un Cliente por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Cliente a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarclientePorNombre (String nombre)
	{
		log.info ("Eliminando Cliente por nombre: " + nombre);
        long resp = pp.eliminarClientePorNombre(nombre);		
        log.info ("Eliminando Cliente por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	/**
	 * Elimina un Cliente por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param identificador - El id del Cliente a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarClientePorId (long identificador)
	{
		log.info ("Eliminando Cliente por id: " + identificador);
        long resp = pp.eliminarClientePorId(identificador);		
        log.info ("Eliminando Cliente por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	/**
	 * Encuentra todos los Clientes en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Cliente con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Cliente> darClientes()
	{
		log.info ("Consultando Tipos de bebida");
        List<Cliente> Clientes = pp.darClientes();	
        log.info ("Consultando Tipos de bebida: " + Clientes.size() + " existentes");
        return Clientes;
	}
	/**
	 * Encuentra todos los CClientes en Aforoandes y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VOCliente> darVOClientes ()
	{
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOCliente> voClientes = new LinkedList<VOCliente> ();
        for (Cliente Cli : pp.darClientes())
        {
        	voClientes.add(Cli);
        }
        log.info ("Generando los VO de Clientes: " + voClientes.size() + " existentes");
        return voClientes;
	}

	/**
	 */
    public List<Cliente> darClientesPornombre(String Nombre)
	{
		log.info ("Consultando Cliente por nombre");
        List<Cliente> Clientes = pp.darClientePorNombre(Nombre);	
        log.info ("Consultando Cliente por nombre: " + Clientes.size() + " existentes");
        return Clientes;
	}
    public List<Cliente> darClientesPorVinculo(String vinculo)
	{
		log.info ("Consultando Cliente por vinculo");
        List<Cliente> Clientes = pp.darClientesPorVinculo(vinculo);	
        log.info ("Consultando Cliente por vinculo: " + Clientes.size() + " existentes");
        return Clientes;
	}
	public Cliente darClientePorID(Long id)
	{
		log.info ("Consultando Cliente por ID");
		Cliente Cliente = pp.darClientesPorId(id);	
        log.info ("Consultando Cliente por ID: " + Cliente.toString() + " existentes");
        return Cliente;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Operadores
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Operador 
	 * @return El objeto Bebida adicionado. null si ocurre alguna Excepción
	 */
	public Operador adicionarOperador (String nombre, float ganancias)
	{
		log.info ("Adicionando Operador " + nombre);
		Operador operador = pp.adicionarOperador(nombre, ganancias);
        log.info ("Adicionando Operador: " + nombre);
        return operador;
	}
	
	/**
	 * Elimina una Operador por su nombre
	 */
	public long eliminarOperadorPorNombre (String nombre)
	{
        log.info ("Eliminando bebida por nombre: " + nombre);
        long resp = pp.eliminarOperadorPorNombre(nombre);
        log.info ("Eliminando bebida por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	/**
	 * Elimina un Operador por su identificador
	 */
	public long eliminarOperadorPorId (long id_operador)
	{
        log.info ("Eliminando Operador por id: " + id_operador);
        long resp = pp.eliminarOperadorPorId(id_operador);
        log.info ("Eliminando Operador por id: " + resp + " tuplas eliminadas");
        return resp;
	}

	public Operador darOperadorPorId (long id)
	{
        log.info ("Consultando Operadores");
        Operador operador = pp.darOperadorPorId(id);	
        log.info ("Consultando Operadores: " + operador + " bebidas existentes");
        return operador;
	}
	/**
	 * Encuentra todas las Operadores en Parranderos
     */
	public List<Operador> derOperadores ()
	{
        log.info ("Consultando Operadores");
        List<Operador> operadores = pp.darOperadores();	
        log.info ("Consultando Operadores: " + operadores.size() + " bebidas existentes");
        return operadores;
	}

	/**
	 * Encuentra todos los Operadores en Aforoandes y los devuelve como una lista de VOOperador
	 * Adiciona entradas al log de la aplicación
	 */
	public List<VOOperador> darVOOperadores ()
	{
		log.info ("Generando los VO de los Operadores");       
        List<VOOperador> voBebidas = new LinkedList<VOOperador> ();
        for (Operador Op : pp.darOperadores())
        {
        	voBebidas.add (Op);
        }
        log.info ("Generando los VO de los Operadores: " + voBebidas.size() + " existentes");
        return voBebidas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ALOJAMIENTOS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un bebedor 
	 * Adiciona entradas al log de la aplicación
	 */
	public Alojamiento adicionarAlojamiento (int capacidad, String estado, String direccion, String tipo_Aloja)
	{
        log.info ("Adicionando Alojamiento: " + tipo_Aloja);
        Alojamiento alojamiento = pp.adicionarAlojamiento(capacidad, estado, direccion, tipo_Aloja) ;
        log.info ("Adicionando Alojamiento: " + tipo_Aloja);
        return alojamiento ;
	}

	/**
	 * Elimina un Alojamientos por su tipo
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamientoPorTipoAloja (String tipo_aloja)
	{
        log.info ("Eliminando Alojamineto por tipo: " + tipo_aloja);
        long resp = pp.eliminarAlojamientoPorTipoAloja(tipo_aloja);
        log.info ("Eliminando Alojamineto por tipo: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Elimina un Alojamiento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamientoPorId (long id_Aloja)
	{
        log.info ("Eliminando Alojamiento por id: " + id_Aloja);
        long resp = pp.eliminarAlojamientoPorID(id_Aloja);
        log.info ("Eliminando Alojamiento por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Encuentra un bebedor y su información básica, según su identificador
	 * @return Un objeto Alojamiento que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Alojamiento con dicho identificador no existe
	 */
	public Alojamiento darAlojamientoPorId (long id_Alojamiento)
	{
		log.info ("Buscando bebedor por Id: " + id_Alojamiento) ;
        Alojamiento alojamiento = pp.darAlojamientoPorId(id_Alojamiento);
		log.info ("Dar información de un Alojamiento por id: " + alojamiento);
        return alojamiento;
	}

	/**
	 * Encuentra la información básica de los bebedores, según su nombre
	 * @param nombre - El nombre de bebedor a buscar
	 * @return Una lista de Bebedores con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen bebedores con ese nombre
	 */
	public List<Alojamiento> darAlojamientoPorNombre (String tipo)
	{
        log.info ("Dar información de bebedores por nombre: " + tipo);
        List<Alojamiento> alojamientos = pp.darAlojamientosPorTipo(tipo);
        log.info ("Dar información de Bebedores por nombre: " + alojamientos.size() + " bebedores con ese nombre existentes");
        return alojamientos;
 	}

	/**
	 * Encuentra la información básica de los bebedores, según su nombre y los devuelve como VO
	 * @param nombre - El nombre de bebedor a buscar
	 * @return Una lista de Bebedores con su información básica, donde todos tienen el nombre buscado.
	 * 	La lista vacía indica que no existen bebedores con ese nombre
	 */
	public List<VOAlojamiento> darVOPorNombre (String nombre)
	{
        log.info ("Generando VO de Alojamientos por nombre: " + nombre);
        List<VOAlojamiento> voAlojamientos = new LinkedList<VOAlojamiento> ();
       for (Alojamiento aloj : pp.darAlojamientosPorTipo(nombre))
       {
            voAlojamientos.add (aloj);
       }
       log.info ("Generando los VO de Alojamientos: " + voAlojamientos.size() + " Alojamientos existentes");
      return voAlojamientos;
 	}

	/**
	 * Encuentra un bebedor, su información básica y los bares y las bebidas 
	 * con las que está directamente relacionado, según su identificador
	 * @return Un objeto Alojamiento que corresponde con el identificador buscado y lleno con su información básica
	 * 			null, si un Alojamiento con dicho identificador no existe
	 */
	public Alojamiento darAlojamientoCompleto (long id_alojamiento)
	{
        log.info ("Dar información COMPLETA de un id_alojamiento por id: " + id_alojamiento);
        Alojamiento alojamiento = pp.darAlojamientoPorId(id_alojamiento);
        log.info ("Buscando id_alojamiento por Id: " + alojamiento.toString() != null ? alojamiento : "NO EXISTE");
        return alojamiento;
	}

	/**
	 * Encuentra todos los bebedores en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bebedor con todos las bebedores que conoce la aplicación, llenos con su información básica
	 */
	public List<Alojamiento> darAlojamientos ()
	{
        log.info ("Listando Bebedores");
        List<Alojamiento> alojamientos = pp.darAlojamientos();	
        log.info ("Listando Bebedores: " + alojamientos.size() + " bebedores existentes");
        return alojamientos;
	}
	
	/**
	 * Encuentra todos los Alojamientos por aforoandes y los devuelve como VOBebedor
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOAlojamientos con todos las bebedores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento> darVOBebedores ()
	{
        log.info ("Generando los VO de Alojamientos");
         List<VOAlojamiento> voBebedores = new LinkedList<VOAlojamiento> ();
        for (Alojamiento aloja : pp.darAlojamientos ())
        {
        	voBebedores.add (aloja);
        }
        log.info ("Generando los VO de Alojamientos: " + voBebedores.size() + " Alojamientos existentes");
       return voBebedores;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los SERVICIOS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Servicio 
	 * Adiciona entradas al log de la aplicación
	 */
	public Servicio adicionarServicio (String nombre)
	{
        log.info ("Adicionando Servicio: " + nombre);
        Servicio bar = pp.adicionarServicio(nombre);
        log.info ("Adicionando Servicio: " + bar);
        return bar;
	}
	
	/**
	 * Elimina un Servicio por su nombre
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarServicioPorNombre (String nombre)
	{
        log.info ("Eliminando bar por nombre: " + nombre);
        long resp = pp.eliminarServicioPorNombre(nombre);
        log.info ("Eliminando bar: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Elimina un Servicio por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarServicioPorId (long id_Servicio)
	{
        log.info ("Eliminando Servicio por id: " + id_Servicio);
        long resp = pp.eliminarServicioPorId(id_Servicio);
        log.info ("Eliminando Servicio: " + resp);
        return resp;
	}
	
	/**
	 * Encuentra todos los Servicios en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bar con todos las bares que conoce la aplicación, llenos con su información básica
	 */
	public List<Servicio> darServicios ()
	{
        log.info ("Listando Servicios");
        List<Servicio> bares = pp.darServicios();	
        log.info ("Listando Servicios: " + bares.size() + " bares existentes");
        return bares;
	}

	/**
	 * Encuentra todos los Servicios en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bar con todos las bares que conoce la aplicación, llenos con su información básica
	 */
	public List<VOServicio> darVOServicios ()
	{
		log.info ("Generando los VO de Bares");
		List<VOServicio> voServicios = new LinkedList<VOServicio> ();
		for (Servicio serv: pp.darServicios())
		{
			voServicios.add (serv);
		}
		log.info ("Generando los VO de Bares: " + voServicios.size () + " bares existentes");
		return voServicios;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación RESERVAS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una preferencia de una bebida por un bebedor
	 * Adiciona entradas al log de la aplicación
	 * @return Un objeto Gustan con los valores dados
	 */
	public Reserva adicionarReserva(String fecha_llegada, String fecha_salida, float precio, long Id_Cliente, long Id_Alojamiento, long Id_Operador, String estado)
	{
        log.info ("Adicionando Reserva [" + Id_Cliente + ", " + Id_Cliente + ", "+precio+ "]");
        Reserva resp = pp.adicionarReserva(fecha_llegada, fecha_salida, precio, Id_Cliente, Id_Alojamiento, Id_Operador, estado);
        log.info ("Adicionando Reserva: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/**
	 * Elimina de manera persistente una Reserva por un Cliente
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarReservaPorId(long id_Reserva)
	{
        log.info ("Eliminando Reserva");
        long resp = pp.eliminarReservaPorId(id_Reserva);
        log.info ("Eliminando Reserva: " + resp + " tuplas eliminadas");
        return resp;
	}
	public long eliminarReservaPorIdAloja(long id_aloja)
	{
        log.info ("Eliminando Reserva");
        long resp = pp.eliminarReservaPoridAloja(id_aloja);
        log.info ("Eliminando Reserva: " + resp + " tuplas eliminadas");
        return resp;
	}

	public long ActualizarReserva(String estado, long id_Reserva)
	{
		log.info ("Actualizando Reserva");
		long resp = pp.ActualizarReserva(estado, id_Reserva);
		log.info ("Actualizando Reserva: " + resp + " tuplas actualizadas");
		return resp;
	}
	
	/**
	 * Encuentra todas las Reserva en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Reserva con todos las Reserva que conoce la aplicación, llenos con su información básica
	 */
	public List<Reserva> darReservas ()
	{
        log.info ("Listando Reserva");
        List<Reserva> gustan = pp.darReservas();	
        log.info ("Listando Reserva: " + gustan.size() + " preferencias de gusto existentes");
        return gustan;
	}

	public Reserva darReservaPorId (long id_reserva)
	{
        log.info ("Dar Reserva");
        Reserva reserva = pp.darReservaPorId(id_reserva);	
        log.info ("Dar Reserva: ");
        return reserva;
	}

	/**
	 * Encuentra todos los Reserva en Aforoandes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Reserva con todos las Reserva que conoce la aplicación, llenos con su información básica
	 */
	public List<VOReserva> darVOReservas ()
	{
		log.info ("Generando los VO de Reserva");
		List<VOReserva> voReservas = new LinkedList<VOReserva> ();
		for (Reserva Res: pp.darReservas())
		{
			voReservas.add (Res);
		}
		log.info ("Generando los VO de Reserva: " + voReservas.size () + " Gustan existentes");
		return voReservas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ALOJAMIENTO_SERVICIO
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente el hecho que UN ALOJAMIENTO TIEN UN SERVICIO
	 * Adiciona entradas al log de la aplicación

	 */
	public Alojamiento_Servicio adicionarAlojamiento_Servicio (long id_Alojamiento, long id_Servicio)
	{
        log.info ("Adicionando Alojamiento_Servicio [" + id_Alojamiento + ", " + id_Servicio + "]");
        Alojamiento_Servicio resp = pp.adicionarAlojamiento_Servicio(id_Alojamiento, id_Servicio);
        log.info ("Adicionando Alojamiento_Servicio: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/**
	 * Elimina de manera persistente el hecho que UN ALOJAMIENTO TIEN UN SERVICIO
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamiento_Servicio (long id_Alojamiento, long id_Servicio)
	{
        log.info ("Eliminando Alojamiento_Servicio");
        long resp = pp.eliminarAlojamiento_Servicio(id_Alojamiento, id_Servicio);
        log.info ("Eliminando Alojamiento_Servicio: " + resp + "tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los Alojamiento_Servicio en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Alojamiento_Servicio con todos los Alojamiento_Servicio que conoce la aplicación, llenos con su información básica
	 */
	public List<Alojamiento_Servicio> darAlojamiento_ServicioS ()
	{
        log.info ("Listando Alojamiento_Servicio");
        List<Alojamiento_Servicio> Alojamiento_Servicios = pp.darAlojamiento_ServicioS();	
        log.info ("Listando Alojamiento_Servicio: " + Alojamiento_Servicios.size() + " sirven existentes");
        return Alojamiento_Servicios;
	}

	/**
	 * Encuentra todos los Alojamiento_Servicio en Aforoandes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Alojamiento_Servicio con todos los Alojamiento_Servicio que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento_Servicio> darVOAlojamiento_ServicioS ()
	{
		log.info ("Generando los VO de Alojamiento_Servicio");
		List<VOAlojamiento_Servicio> voAlojamiento_Servicios = new LinkedList<VOAlojamiento_Servicio> ();
		for (Alojamiento_Servicio alojamiento_Servicio: pp.darAlojamiento_ServicioS())
		{
			voAlojamiento_Servicios.add (alojamiento_Servicio);
		}
		log.info ("Generando los VO de Alojamiento_Servicio: " + voAlojamiento_Servicios.size () + " Sirven existentes");
		return voAlojamiento_Servicios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ALOJAMIENTO_OPERADOR
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente el hecho que UN ALOJAMIENTO TIEN UN SERVICIO
	 * Adiciona entradas al log de la aplicación

	 */
	public Alojamiento_Operador adicionarAlojamiento_Operador (long id_Alojamiento, long id_Operador)
	{
        log.info ("Adicionando Alojamiento_Operador [" + id_Alojamiento + ", " + id_Operador + "]");
        Alojamiento_Operador resp = pp.adicionarAlojamiento_Operador(id_Alojamiento, id_Operador);
        log.info ("Adicionando Alojamiento_Operador: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/**
	 * Elimina de manera persistente el hecho que UN ALOJAMIENTO TIEN UN SERVICIO
	 * Adiciona entradas al log de la aplicación
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAlojamiento_Operador (long id_Alojamiento, long id_Operador)
	{
        log.info ("Eliminando Alojamiento_Operador");
        long resp = pp.eliminarAlojamiento_Operador(id_Alojamiento, id_Operador);
        log.info ("Eliminando Alojamiento_Operador: " + resp + "tuplas eliminadas");
        return resp;
	}
	public long eliminarAlojamiento_OperadorID_aloja (long id_Alojamiento)
	{
        log.info ("Eliminando Alojamiento_Operador");
        long resp = pp.eliminarAlojamiento_OperadorPoridAlojamiento(id_Alojamiento);
        log.info ("Eliminando Alojamiento_Operador: " + resp + "tuplas eliminadas");
        return resp;
	}
	/**
	 * Encuentra todos los Alojamiento_Servicio en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Alojamiento_Servicio con todos los Alojamiento_Servicio que conoce la aplicación, llenos con su información básica
	 */
	public List<Alojamiento_Operador> darAlojamiento_Operadors ()
	{
        log.info ("Listando Alojamiento_Operador");
        List<Alojamiento_Operador> Alojamiento_Operadors = pp.darAlojamiento_Operadors();	
        log.info ("Listando Alojamiento_Operador: " + Alojamiento_Operadors.size() + " sirven existentes");
        return Alojamiento_Operadors;
	}

	/**
	 * Encuentra todos los Alojamiento_Servicio en Aforoandes y los devuelve como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Alojamiento_Servicio con todos los Alojamiento_Servicio que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento_Operador> darVOlojamiento_Operadors ()
	{
		log.info ("Generando los VO de Alojamiento_Operador");
		List<VOAlojamiento_Operador> voAlojamiento_Operadors = new LinkedList<VOAlojamiento_Operador> ();
		for (Alojamiento_Operador alojamiento_Servicio: pp.darAlojamiento_Operadors())
		{
			voAlojamiento_Operadors.add (alojamiento_Servicio);
		}
		log.info ("Generando los VO de Alojamiento_Operador: " + voAlojamiento_Operadors.size () + " Sirven existentes");
		return voAlojamiento_Operadors;
	}

		/* ****************************************************************
	 * 			Métodos para manejar las PERSONAS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente una Persona
	 * Adiciona entradas al log de la aplicación
	 */
	public Persona adicionarPersona (long id, long Identificacion, String vinculo)
	{
        log.info ("Adicionando Persona: [" + id + ", " + Identificacion + ", " + vinculo +"]");
        Persona persona = pp.adicionarPersona(id, Identificacion, vinculo);
        log.info ("Adicionando Servicio: " + persona);
        return persona;
	}
	
	/**
	 * Elimina una Persona por su nombre
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarPersonaPorId (long id)
	{
        log.info ("Eliminando persona por id: " + id);
        long resp = pp.eliminarPersonaPorId(id);
        log.info ("Eliminando bar: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las Personas en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Bar con todos las bares que conoce la aplicación, llenos con su información básica
	 */
	public List<Persona> darPersonas ()
	{
        log.info ("Listando Personas");
        List<Persona> personas = pp.darPersonas();	
        log.info ("Listando Servicios: " + personas.size() + " personas existentes");
        return personas;
	}

	/**
	 * Encuentra todos las Personas en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Persona con todos las personas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOPersona> darVOPersonas ()
	{
		log.info ("Generando los VO de Personas");
		List<VOPersona> VOPersonas = new LinkedList<VOPersona> ();
		for (Persona per: pp.darPersonas())
		{
			VOPersonas.add (per);
		}
		log.info ("Generando los VO de Personas: " + VOPersonas.size () + " personas existentes");
		return VOPersonas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las HAB_HOTEL
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente una Hab_Hotel
	 * Adiciona entradas al log de la aplicación
	 */
	public Hab_Hotel adicionarHab_Hotel (long id_Aloja, String categoria)
	{
        log.info ("Adicionando Hab_Hotel: [" + id_Aloja + ", " + categoria +"]");
        Hab_Hotel hab_Hotel = pp.adicionarHab_Hotel(id_Aloja, categoria);
        log.info ("Adicionando Servicio: " + hab_Hotel);
        return hab_Hotel;
	}
	
	/**
	 * Elimina una Hab_Hotel por su id
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarHab_HotelPorId (long id)
	{
        log.info ("Eliminando Hab_Hotel por id: " + id);
        long resp = pp.eliminarHab_HotelPorId(id);
        log.info ("Eliminando Hab_Hotel: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las Hab_Hotel en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hab_Hotel con todos las Hab_Hotel que conoce la aplicación, llenos con su información básica
	 */
	public List<Hab_Hotel> darHabs_Hoteles ()
	{
        log.info ("Listando Habitaciones de Hoteles");
        List<Hab_Hotel> Habs_Hoteles = pp.darHabs_Hoteles();	
        log.info ("Listando Habitaciones de Hoteles: " + Habs_Hoteles.size() + " Habitaciones de hoteles existentes");
        return Habs_Hoteles;
	}

	/**
	 * Encuentra todos las Hab_Hotel en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hab_Hotel con todos las Hab_Hotel que conoce la aplicación, llenos con su información básica
	 */
	public List<VOHab_Hotel> darVOHabs_Hoteles ()
	{
		log.info ("Generando los VO de Hab_Hotel");
		List<VOHab_Hotel> VOHabs_Hoteles = new LinkedList<VOHab_Hotel> ();
		for (Hab_Hotel hab_hotel: pp.darHabs_Hoteles())
		{
			VOHabs_Hoteles.add (hab_hotel);
		}
		log.info ("Generando los VO de Habs_Hoteles: " + VOHabs_Hoteles.size () + " Habitaciones de hoteles existentes");
		return VOHabs_Hoteles;
	}

		/* ****************************************************************
	 * 			Métodos para manejar las HAB_HOSTAL
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente una Hab_Hostal
	 * Adiciona entradas al log de la aplicación
	 */
	public Hab_Hostal adicionarHab_Hostal (long id_Aloja, SimpleDateFormat horaApertura, SimpleDateFormat horaCierre)
	{
        log.info ("Adicionando Hab_Hostal: [" + id_Aloja + ", " + horaApertura + ", " + horaCierre +"]");
        Hab_Hostal hab_Hostal = pp.adicionarHab_Hostal(id_Aloja, horaApertura, horaCierre);
        log.info ("Adicionando Hab_Hpstal: " + hab_Hostal);
        return hab_Hostal;
	}
	
	/**
	 * Elimina una Hab_Hostal por su id
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarHab_HostalPorId (long id)
	{
        log.info ("Eliminando Hab_Hostal por id: " + id);
        long resp = pp.eliminarHab_HostalPorId(id);
        log.info ("Eliminando Hab_Hostal: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las Hab_Hostal en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hab_Hostal con todos las Hab_Hostal que conoce la aplicación, llenos con su información básica
	 */
	public List<Hab_Hostal> darHabs_Hostales ()
	{
        log.info ("Listando Habitaciones de Hostales");
        List<Hab_Hostal> Habs_Hostales = pp.darHabs_Hostales();	
        log.info ("Listando Habitaciones de Hostales: " + Habs_Hostales.size() + " Habitaciones de hostales existentes");
        return Habs_Hostales;
	}

	/**
	 * Encuentra todos las Hab_Hostal en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hab_Hostal con todos las Hab_Hostal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOHab_Hostal> darVOHabs_Hostales ()
	{
		log.info ("Generando los VO de Hab_Hostal");
		List<VOHab_Hostal> VOHabs_Hostales = new LinkedList<VOHab_Hostal> ();
		for (Hab_Hostal hab_hostal: pp.darHabs_Hostales())
		{
			VOHabs_Hostales.add (hab_hostal);
		}
		log.info ("Generando los VO de Habs_Hostales: " + VOHabs_Hostales.size () + " Habitaciones de hostales existentes");
		return VOHabs_Hostales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOTEL_HOSTAL
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Hotel_Hostal
	 * Adiciona entradas al log de la aplicación
	 */
	public Hotel_Hostal adicionarHotel_Hostal (long id, String estadoLegal, int numeroRegistroCC, int numHabitaciones, int numHabitacionesDisponibles, String direccion)
	{
        log.info ("Adicionando Hotel_Hostal: [" + id + ", " + ", " + estadoLegal + ", " + numeroRegistroCC + ", " + numHabitacionesDisponibles + ", " + numHabitacionesDisponibles + ", " + direccion +"]");
        Hotel_Hostal hotel_hostal = pp.adicionarHotel_Hostal(id, estadoLegal, numeroRegistroCC, numHabitaciones, numHabitacionesDisponibles, direccion);
        log.info ("Adicionando Hotel_Hostal: " + hotel_hostal);
        return hotel_hostal;
	}
	
	/**
	 * Elimina una Hotel_Hostal por su id
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarHotel_HostalPorId (long id)
	{
        log.info ("Eliminando Hotel_Hostal por id: " + id);
        long resp = pp.eliminarHotel_HostalPorId(id);
        log.info ("Eliminando Hotel_Hostal: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las Hotel_Hostal en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hotel_Hostal con todos las Hotel_Hostal que conoce la aplicación, llenos con su información básica
	 */
	public List<Hotel_Hostal> darHoteles_Hostales ()
	{
        log.info ("Listando Hoteles y Hostales");
        List<Hotel_Hostal> hoteles_hostales = pp.darHoteles_Hostales();	
        log.info ("Listando Hoteles y Hostales: " + hoteles_hostales.size() + " hoteles y hostales existentes");
        return hoteles_hostales;
	}

	/**
	 * Encuentra todos las Hotel_Hostal en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Hotel_Hostal con todos las Hotel_Hostal que conoce la aplicación, llenos con su información básica
	 */
	public List<VOHotel_Hostal> darVOHoteles_Hostales ()
	{
		log.info ("Generando los VO de Hotel_Hostal");
		List<VOHotel_Hostal> VOHoteles_Hostales = new LinkedList<VOHotel_Hostal> ();
		for (Hotel_Hostal hotel_hostal: pp.darHoteles_Hostales())
		{
			VOHoteles_Hostales.add (hotel_hostal);
		}
		log.info ("Generando los VO de Habs_Hostales: " + VOHoteles_Hostales.size () + " Habitaciones de hostales existentes");
		return VOHoteles_Hostales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los EDIFICIO_UNIVERSITARIO
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un Edificio_Universitario
	 * Adiciona entradas al log de la aplicación
	 */
	public Edificio_Universitario adicionarEdificio_Universitario (long id, int numViviendas, int numViviendasDisponibles, String direccion)
	{
        log.info ("Adicionando Hotel_Hostal: [" + id + ", " + numViviendas + ", " + numViviendasDisponibles + ", " + direccion +"]");
        Edificio_Universitario edificio_universitario = pp.adicionarEdificio_Universitario(id, numViviendas, numViviendasDisponibles, direccion);
        log.info ("Adicionando Hotel_Hostal: " + edificio_universitario);
        return edificio_universitario;
	}
	
	/**
	 * Elimina una Edificio_Universitario por su id
	 * Adiciona entradas al log de la aplicación
	 */
	public long eliminarEdificio_UniversitarioPorId (long id)
	{
        log.info ("Eliminando Edificio_Universitario por id: " + id);
        long resp = pp.eliminarEdificio_UniversitarioPorId(id);
        log.info ("Eliminando Edificio_Universitario: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todas las Edificio_Universitario en Aforoandes
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Edificio_Universitario con todos las Edificio_Universitario que conoce la aplicación, llenos con su información básica
	 */
	public List<Edificio_Universitario> darEdificios_Universitarios ()
	{
        log.info ("Listando Edificios Universitarios");
        List<Edificio_Universitario> edificios_universitarios = pp.darEdificios_Universitarios();	
        log.info ("Listando Edificios Universitarios: " + edificios_universitarios.size() + " esdificios universitarios existentes");
        return edificios_universitarios;
	}

	/**
	 * Encuentra todos las Edificio_Universitario en Aoroande y los devuelce como VO
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Edificio_Universitario con todos las Edificio_Universitario que conoce la aplicación, llenos con su información básica
	 */
	public List<VOEdificio_Universitario> darVOEdificios_Universitarios ()
	{
		log.info ("Generando los VO de Edificio_Universitario");
		List<VOEdificio_Universitario> VOEdificios_Universitarios = new LinkedList<VOEdificio_Universitario> ();
		for (Edificio_Universitario edificio_universitario: pp.darEdificios_Universitarios())
		{
			VOEdificios_Universitarios.add (edificio_universitario);
		}
		log.info ("Generando los VO de Edificio_Universitario: " + VOEdificios_Universitarios.size () + " edficios universitarios existentes");
		return VOEdificios_Universitarios;
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
        log.info ("Limpiando la BD de Alohandes");
        long [] borrrados = pp.limpiarAlohandes();	
        log.info ("Limpiando la BD de Alohandes: Listo!");
        return borrrados;
	}
}
