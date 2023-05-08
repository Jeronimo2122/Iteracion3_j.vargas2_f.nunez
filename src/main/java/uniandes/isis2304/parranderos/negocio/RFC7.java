package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class RFC7 implements VORFC7 {
    
    private Timestamp fecha;
    private int alojamientos_ocupados;
    private float ingresos_totales;

    public RFC7() {
        this.fecha = new Timestamp(0);
        this.alojamientos_ocupados = 0;
        this.ingresos_totales = 0;
    }

    public RFC7(Timestamp fecha, int alojamientos_ocupados, float ingresos_totales) {
        this.fecha = fecha;
        this.alojamientos_ocupados = alojamientos_ocupados;
        this.ingresos_totales = ingresos_totales;
    }

    public Timestamp getFecha() {
        return fecha;
    }
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }


    public int getAlojamientos_ocupados() {
        return alojamientos_ocupados;
    }
    public void setAlojamientos_ocupados(int alojamientos_ocupados) {
        this.alojamientos_ocupados = alojamientos_ocupados;
    }


    public float getIngresos_totales() {
        return ingresos_totales;
    }
    public void setIngresos_totales(float ingresos_totales) {
        this.ingresos_totales = ingresos_totales;
    }

    @Override
    public String toString() {
        return "[fecha=" + fecha + ", alojamientos_ocupados=" + alojamientos_ocupados + ", ingresos_totales=" + ingresos_totales + "]";
    }

}
