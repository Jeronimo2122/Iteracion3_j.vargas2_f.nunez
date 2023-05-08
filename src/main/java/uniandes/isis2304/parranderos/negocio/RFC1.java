package uniandes.isis2304.parranderos.negocio;

public class RFC1 implements VORFC1 {

    private long id;
    private String operador;
    private float dinero_recibido_corrido;
    private float dinero_recibido_anio_actual;

    public RFC1() {
        this.id = 0;
        this.operador = "";
        this.dinero_recibido_corrido = 0;
        this.dinero_recibido_anio_actual = 0;
    }

    public RFC1(long id, String operador, float dinero_recibido_corrido, float dinero_recibido_anio_actual) {
        this.id = id;
        this.operador = operador;
        this.dinero_recibido_corrido = dinero_recibido_corrido;
        this.dinero_recibido_anio_actual = dinero_recibido_anio_actual;
    }

    public long getId (){
        return id;
    }
    public void setId (long id){
        this.id = id;
    }

    public String getOperador (){
        return operador;
    }
    public void setOperador (String operador){
        this.operador = operador;
    }

    public float getDinero_recibido_corrido (){
        return dinero_recibido_corrido;
    }
    public void setDinero_recibido_corrido (float dinero_recibido_corrido){
        this.dinero_recibido_corrido = dinero_recibido_corrido;
    }

    public float getDinero_recibido_anio_actual (){
        return dinero_recibido_anio_actual;
    }
    public void setDinero_recibido_anio_actual (float dinero_recibido_anio_actual){
        this.dinero_recibido_anio_actual = dinero_recibido_anio_actual;
    }

    @Override
    public String toString() {
        return "OPERADOR [id=" + id + ", operador=" + operador + ", dinero_recibido_corrido=" + dinero_recibido_corrido + ", dinero_recibido_anio_actual=" + dinero_recibido_anio_actual + "]";
    }


    
}
