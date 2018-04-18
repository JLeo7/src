package com.softwareCelestial.cl;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class VersionActualizada extends Version {

    private ArrayList<Caracteristica> caracteristicas;

    public VersionActualizada(){

    }

    public VersionActualizada(ArrayList<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public VersionActualizada(String numero, LocalDate fechaCreacion, ArrayList<Caracteristica> caracteristicas) {
        super(numero, fechaCreacion);
        this.caracteristicas = caracteristicas;
    }

    public ArrayList<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "VersionActualizada{" +
                "caracteristicas=" + caracteristicas +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
