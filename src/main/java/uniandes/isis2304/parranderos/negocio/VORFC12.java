package uniandes.isis2304.parranderos.negocio;

public interface VORFC12 {

    public String getSemana();

    public long getOFERTA_MAYOR_OCUPACION();

    public long getOFERTA_MENOR_OCUPACION();

    public String getOPERADOR_MAS_SOLICITADO();

    public String getOPERADOR_MENOS_SOLICITADO();

    @Override

    public String toString();
    
}
