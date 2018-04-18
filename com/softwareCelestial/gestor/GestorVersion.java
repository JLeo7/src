package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Version;
import com.softwareCelestial.cl.VersionInicial;
import com.softwareCelestial.multis.MultiVersion;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * @author Esteban Sancho
 * @version 1
 *
 * */
public class GestorVersion {

    MultiVersion multiVersion = new MultiVersion();
    public GestorVersion(){

    }

    /**
     * Metodo que crea una instancia de Version inicial, sin caracteristicas
     * @param numeroVersion numero de version a crear
     * @return instancia de version
     * @author Esteban Sancho
     * */
    public Version crearVersion(String numeroVersion){
        LocalDate fechaCreacion = LocalDate.now();
        Version version = new VersionInicial(numeroVersion, fechaCreacion);
        return version;
    }

    /**
     * Metodo que recibe la informacion de version para enviarla a modificarse en la BD
     * @param numeroVersion numero de version
     * @
     * */
    public void modificarVersion(String numeroVersion, String idProducto){
        LocalDate fechaCreacion = LocalDate.now();
        multiVersion.modificarVersion(numeroVersion, idProducto, fechaCreacion);
    }
}
