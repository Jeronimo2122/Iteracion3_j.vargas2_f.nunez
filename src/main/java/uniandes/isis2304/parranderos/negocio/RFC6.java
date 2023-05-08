package uniandes.isis2304.parranderos.negocio;

public class RFC6 {

    private long identificacion;
    private int noches_contratados;
    private String tipo_aloja;
    private float dinero_pagado;

    public RFC6() {
        this.identificacion = 0;
        this.noches_contratados = 0;
        this.tipo_aloja = "";
        this.dinero_pagado = 0;
    }

    public RFC6(long identificacion, int noches_contratados, String tipo_aloja, float dinero_pagado) {
        this.identificacion = identificacion;
        this.noches_contratados = noches_contratados;
        this.tipo_aloja = tipo_aloja;
        this.dinero_pagado = dinero_pagado;
    }

    public long getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public int getNoches_contratados() {
        return noches_contratados;
    }
    public void setNoches_contratados(int noches_contratados) {
        this.noches_contratados = noches_contratados;
    }

    public String getTipo_aloja() {
        return tipo_aloja;
    }
    public void setTipo_aloja(String tipo_aloja) {
        this.tipo_aloja = tipo_aloja;
    }

    public float getDinero_pagado() {
        return dinero_pagado;
    }
    public void setDinero_pagado(float dinero_pagado) {
        this.dinero_pagado = dinero_pagado;
    }

    @Override

    public String toString() {
        return "[identificacion=" + identificacion + ", noches_contratados=" + noches_contratados + ", tipo_aloja=" + tipo_aloja + ", dinero_pagado=" + dinero_pagado + "]";
    }
    
}
