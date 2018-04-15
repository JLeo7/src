package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Version;
import com.softwareCelestial.cl.VersionInicial;

import java.time.LocalDate;
import java.time.LocalTime;

public class GestorVersion {

    public GestorVersion(){

    }

    public Version crearVersion(String numeroVersion){
        LocalDate fechaCreacion = LocalDate.now();
        Version version = new VersionInicial(numeroVersion, fechaCreacion);
        return version;
    }


}
