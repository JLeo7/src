package com.softwareCelestial.cl;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class VersionInicial extends Version{

    public VersionInicial(){

    }

    public VersionInicial(String numero, LocalDate fechaCreacion) {
        super(numero, fechaCreacion);
    }

    @Override
    public String toString() {
        return "VersionInicial{" +
                "numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
