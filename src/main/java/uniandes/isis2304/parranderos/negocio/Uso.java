package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto Uso del negocio de AlohAndes
 *
 * @author Germán Bravo
 */
public class Uso implements VOUso
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El ID del cliente asociado al uso
	 */
	private long Identificacion;
	
	/**
	 * Numero de noches contradas del uso
	 */
	private int NochesContratadas;

	/**
	 * Numero de meses contrados del uso
	 */
	private int MesesContratados;
	
	/**
	 * Dinero pagado al Uso
	 */
	private float DineroPagado;
	
	/**
	 * Id del alojamiento asociado al uso
	 */
	private int Id_Alojamiento;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Uso() 
    {
    	this.Identificacion = 0;
		this.NochesContratadas = 0;
		this.MesesContratados = 0;
		this.DineroPagado = 0;
        this.Id_Alojamiento = 0;
	}

	/**
	 */
    public Uso(long Identificacion, int NochesContratadas, int MesesContratados, float DineroPagado, int Id_Alojamiento) 
    {
    	this.Identificacion = Identificacion;
		this.NochesContratadas = NochesContratadas;
		this.MesesContratados = MesesContratados;
		this.DineroPagado = DineroPagado;
        this.Id_Alojamiento = Id_Alojamiento;
	}

    /**
	 * @return Identificacion del Uso
	 */ 
	public long getIdentificacion() 
	{
		return Identificacion;
	}
	
	/**
	 * @param Identificacion - nueva Identificacion del Uso
	 */
	public void setIdentificacion(long Identificacion) 
	{
		this.Identificacion = Identificacion;
	}
	
	/**
	 * @return noches contratadas del uso
	 */
	public int getNochesContratadas() 
	{
		return NochesContratadas;
	}
	/**
	 * @param NochesContratadas - nuevo numero de noches contratadas del uso
	 */
	public void setNochesContratadas(int NochesContratadas) 
	{
		this.NochesContratadas = NochesContratadas;
	}
	
	/**
	 * @return meses contratados del uso
	 */
	public int getMesesContratados() 
	{
		return MesesContratados;
	}
	/**
	 * @param MesesContratados - nuevo numero de meses contratados del uso
	 */
	public void setMesesContratados(int MesesContratados) 
	{
		this.MesesContratados = MesesContratados;
	}
	
	/**
	 * @return dinero pagado en el uso
	 */
	public float getDineroPagado() 
	{
		return DineroPagado;
	}
	
	/**
	 * @param DineroPagado - nuevo dinero pagado en el uso
	 */
	public void setDineroPagado(float DineroPagado) 
	{
		this.DineroPagado = DineroPagado;
	}
	
    	/**
	 * @return id del alojamiento asociado al uso
	 */
	public int getId_Alojamiento() 
	{
		return Id_Alojamiento;
	}
	
	/**
	 * @param Id_Alojamiento - nuevo id del alojamiento asociado al uso
	 */
	public void setId_Alojamiento(int Id_Alojamiento) 
	{
		this.Id_Alojamiento = Id_Alojamiento;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Uso
	 */
	public String toString() 
	{
		return "Uso [Identificacion=" + Identificacion + ", Noches Contratadas=" + NochesContratadas + ", "
				+ "Meses Contratados=" + MesesContratados + ", Dinero Pagado=" + DineroPagado + ", Id_Alojamiento=" + Id_Alojamiento+ "]" ;
	}
	

}

