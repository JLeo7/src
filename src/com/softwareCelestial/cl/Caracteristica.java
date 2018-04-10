package src.com.softwareCelestial.cl;

public class Caracteristica {
    private String descripcion;

    public Caracteristica() {
    }

    public Caracteristica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
