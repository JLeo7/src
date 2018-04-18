package com.softwareCelestial.cl;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class Version {

    protected String numero;
    protected LocalDate fechaCreacion;

    public Version(){

    }

    public Version(String numero, LocalDate fechaCreacion) {
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;

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

    @Override
    public String toString() {
        return "Version{" +
                "numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
