package uniandes.isis2304.parranderos.negocio;

public class RFC5 implements VORFC5{

    private String vinculo;
    private int cantidad_de_usuarios;
    
    public RFC5() {
        this.vinculo = "";
        this.cantidad_de_usuarios = 0;
    }
    
    public RFC5(String vinculo, int cantidad_de_usuarios) {
        this.vinculo = vinculo;
        this.cantidad_de_usuarios = cantidad_de_usuarios;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public int getCantidad_de_usuarios() {
        return cantidad_de_usuarios;
    }

    public void setCantidad_de_usuarios(int cantidad_de_usuarios) {
        this.cantidad_de_usuarios = cantidad_de_usuarios;
    }
    
    @Override
    public String toString() {
        return "[vinculo=" + vinculo + ", cantidad_de_usuarios=" + cantidad_de_usuarios + "]";
    }
    
}
