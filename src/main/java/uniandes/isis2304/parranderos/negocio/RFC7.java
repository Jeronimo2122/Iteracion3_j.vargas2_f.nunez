package uniandes.isis2304.parranderos.negocio;

public class RFC7 implements VORFC7 {
    
    private String Mes;
    private int alojamientos_ocupados;
    private float ingresos;
    private int ocupacion;

    public RFC7() {
        this.Mes = "";
        this.alojamientos_ocupados = 0;
        this.ingresos = 0;
        this.ocupacion = 0;
    }

    public RFC7(String mes, int alojamientos_ocupados, float ingresos, int ocupacion) {
        this.Mes = mes;
        this.alojamientos_ocupados = alojamientos_ocupados;
        this.ingresos = ingresos;
        this.ocupacion = ocupacion;
    }

    public String getMes() {
        return Mes;
    }
    public void setMes(String mes) {
        Mes = mes;
    }


    public int getAlojamientos_ocupados() {
        return alojamientos_ocupados;
    }
    public void setAlojamientos_ocupados(int alojamientos_ocupados) {
        this.alojamientos_ocupados = alojamientos_ocupados;
    }


    public float getIngresos() {
        return ingresos;
    }
    public void setIngresos(float ingresos) {
        this.ingresos = ingresos;
    }


    public int getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }


    @Override
    public String toString() {
        return "[Mes=" + Mes + "| alojamientos_ocupados=" + alojamientos_ocupados + "| ingresos=" + ingresos
                + "| ocupacion=" + ocupacion + "]";
    }



    

}
