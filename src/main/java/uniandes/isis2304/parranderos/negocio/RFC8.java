package uniandes.isis2304.parranderos.negocio;

public class RFC8 {

    private long identificacion;
    private int num_reservas;
    private int num_noches;

    public RFC8() {
        this.identificacion = 0;
        this.num_reservas = 0;
        this.num_noches = 0;
    }

    public RFC8(long identificacion, int num_reservas, int num_noches) {
        this.identificacion = identificacion;
        this.num_reservas = num_reservas;
        this.num_noches = num_noches;
    }

    public long getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }


    public int getNum_reservas() {
        return num_reservas;
    }
    public void setNum_reservas(int num_reservas) {
        this.num_reservas = num_reservas;
    }


    public int getNum_noches() {
        return num_noches;
    }
    public void setNum_noches(int num_noches) {
        this.num_noches = num_noches;
    }

    @Override
    public String toString() {
        return "[identificacion=" + identificacion + ", num_reservas=" + num_reservas + ", num_noches=" + num_noches + "]";
    }

    
}
