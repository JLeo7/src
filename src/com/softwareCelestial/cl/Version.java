package src.com.softwareCelestial.cl;

import java.time.LocalDate;
import java.util.ArrayList;

public class Version {

    private String numero;
    private LocalDate fechaCreacion;
    private ArrayList<Caracteristica> caracteristicas;

    public Version(){

    }

    public Version(String numero, LocalDate fechaCreacion, ArrayList<Caracteristica> caracteristicas) {
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.caracteristicas = caracteristicas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "Version{" +
                "numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
