package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public interface VORFC7 {
    
    public Timestamp getFecha();

    public int getAlojamientos_ocupados();

    public float getIngresos_totales();

    @Override

    public String toString();
    
}
