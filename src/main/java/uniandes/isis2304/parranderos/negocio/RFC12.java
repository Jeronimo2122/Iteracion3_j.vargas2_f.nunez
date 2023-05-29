package uniandes.isis2304.parranderos.negocio;

public class RFC12 {

    private String SEMANA;
    private long OFERTA_MAYOR_OCUPACION;
    private long OFERTA_MENOR_OCUPACION;
    private String OPERADOR_MAS_SOLICITADO;
    private String OPERADOR_MENOS_SOLICITADO;

    public RFC12() {
        this.SEMANA = "";
        this.OFERTA_MAYOR_OCUPACION = 0;
        this.OFERTA_MENOR_OCUPACION = 0;
        this.OPERADOR_MAS_SOLICITADO = "";
        this.OPERADOR_MENOS_SOLICITADO = "";
    }

    public RFC12(String semana, long OFERTA_MAYOR_OCUPACION, long OFERTA_MENOR_OCUPACION, String OPERADOR_MAS_SOLICITADO, String OPERADOR_MENOS_SOLICITADO) {
        this.SEMANA = semana;
        this.OFERTA_MAYOR_OCUPACION = OFERTA_MAYOR_OCUPACION;
        this.OFERTA_MENOR_OCUPACION = OFERTA_MENOR_OCUPACION;
        this.OPERADOR_MAS_SOLICITADO = OPERADOR_MAS_SOLICITADO;
        this.OPERADOR_MENOS_SOLICITADO = OPERADOR_MENOS_SOLICITADO;
    }
    
    public String getSEMANA() {
        return SEMANA;
    }

    public void setSEMANA(String SEMANA) {
        this.SEMANA = SEMANA;
    }


    public long getOFERTA_MAYOR_OCUPACION() {
        return OFERTA_MAYOR_OCUPACION;
    }

    public void setOFERTA_MAYOR_OCUPACION(long OFERTA_MAYOR_OCUPACION) {
        this.OFERTA_MAYOR_OCUPACION = OFERTA_MAYOR_OCUPACION;
    }


    public long getOFERTA_MENOR_OCUPACION() {
        return OFERTA_MENOR_OCUPACION;
    }

    public void setOFERTA_MENOR_OCUPACION(long OFERTA_MENOR_OCUPACION) {
        this.OFERTA_MENOR_OCUPACION = OFERTA_MENOR_OCUPACION;
    }


    public String getOPERADOR_MAS_SOLICITADO() {
        return OPERADOR_MAS_SOLICITADO;
    }

    public void setOPERADOR_MAS_SOLICITADO(String OPERADOR_MAS_SOLICITADO) {
        this.OPERADOR_MAS_SOLICITADO = OPERADOR_MAS_SOLICITADO;
    }


    public String getOPERADOR_MENOS_SOLICITADO() {
        return OPERADOR_MENOS_SOLICITADO;
    }

    public void setOPERADOR_MENOS_SOLICITADO(String OPERADOR_MENOS_SOLICITADO) {
        this.OPERADOR_MENOS_SOLICITADO = OPERADOR_MENOS_SOLICITADO;
    }

    @Override

    public String toString() {
        String resp = "RFC12 [semana=" + SEMANA + "| OFERTA_MAYOR_OCUPACION=" + OFERTA_MAYOR_OCUPACION + "| OFERTA_MENOR_OCUPACION=" + OFERTA_MENOR_OCUPACION + "| OPERADOR_MAS_SOLICITADO=" + OPERADOR_MAS_SOLICITADO + "| OPERADOR_MENOS_SOLICITADO=" + OPERADOR_MENOS_SOLICITADO + "]";
        return resp;
    }







}
