package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Version;
import com.softwareCelestial.cl.VersionInicial;
import com.softwareCelestial.multis.MultiVersion;

import java.time.LocalDate;
import java.time.LocalTime;

public class GestorVersion {

    MultiVersion multiVersion = new MultiVersion();
    public GestorVersion(){

    }

    public Version crearVersion(String numeroVersion){
        LocalDate fechaCreacion = LocalDate.now();
        Version version = new VersionInicial(numeroVersion, fechaCreacion);
        return version;
    }

    public void modificarVersion(String numeroVersion, String idProducto){
        LocalDate fechaCreacion = LocalDate.now();
        multiVersion.modificarVersion(numeroVersion, idProducto, fechaCreacion);
    }
}
