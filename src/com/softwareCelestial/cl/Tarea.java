package src.com.softwareCelestial.cl;

public class Tarea {

    private String codigo;
    private String descripcion;
    private String estado;
    private String tipo;
    private String responsable;

    public Tarea(){

    }

    public Tarea(String codigo, String descripcion, String estado, String tipo, String responsable) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tipo = tipo;
        this.responsable = responsable;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", responsable='" + responsable + '\'' +
                '}';
    }
}
