package uniandes.isis2304.parranderos.negocio;

public class RFC13 {

    private long identificacion;
    private String nombre;
    private String vinculo;
    private String calificacion_Reservas_Mensuales;
    private String calificacion_Reservas_Costosas;
    private String calificacion_Reservas_Suites;
    
    public RFC13() {
        this.identificacion = 0;
        this.nombre = "";
        this.vinculo = "";
        this.calificacion_Reservas_Mensuales = "";
        this.calificacion_Reservas_Costosas = "";
        this.calificacion_Reservas_Suites = "";
    }
    
    public RFC13(long identificacion, String nombre, String vinculo, String calificacion_Reservas_Mensuales, String calificacion_Reservas_Costosas, String calificacion_Reservas_Suites) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.vinculo = vinculo;
        this.calificacion_Reservas_Mensuales = calificacion_Reservas_Mensuales;
        this.calificacion_Reservas_Costosas = calificacion_Reservas_Costosas;
        this.calificacion_Reservas_Suites = calificacion_Reservas_Suites;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }	
    
    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }	
    
    public String getCalificacion_Reservas_Mensuales() {
        return calificacion_Reservas_Mensuales;
    }

    public void setCalificacion_Reservas_Mensuales(String calificacion_Reservas_Mensuales) {
        this.calificacion_Reservas_Mensuales = calificacion_Reservas_Mensuales;
    }	
    
    public String getCalificacion_Reservas_Costosas() {
        return calificacion_Reservas_Costosas;
    }

    public void setCalificacion_Reservas_Costosas(String calificacion_Reservas_Costosas) {
        this.calificacion_Reservas_Costosas = calificacion_Reservas_Costosas;
    }	
    
    public String getCalificacion_Reservas_Suites() {
        return calificacion_Reservas_Suites;
    }

    public void setCalificacion_Reservas_Suites(String calificacion_Reservas_Suites) {
        this.calificacion_Reservas_Suites = calificacion_Reservas_Suites;
    }

    @Override

    public String toString() {
        return "RFC13 [identificacion=" + identificacion + "| nombre=" + nombre + "| vinculo=" + vinculo + "| Reservas_Mensuales=" + calificacion_Reservas_Mensuales + "| Reservas_Costosas=" + calificacion_Reservas_Costosas + "| Reservas_Suites=" + calificacion_Reservas_Suites + "]";
    }
    
}
