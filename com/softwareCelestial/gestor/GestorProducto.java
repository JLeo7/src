package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Producto;
import com.softwareCelestial.cl.Version;
import com.softwareCelestial.multis.MultiProducto;

public class GestorProducto {


    public GestorProducto(){

    }

    public void crearProducto(String nombre, String logo, String descripcion, String idProducto, String versionActual){
        GestorVersion gestorVersion = new GestorVersion();
        Version versionInicial = gestorVersion.crearVersion(versionActual);
        Producto nuevoProducto = new Producto(nombre, logo, descripcion, idProducto, versionInicial);
        MultiProducto multiProducto = new MultiProducto();
        multiProducto.registrarProducto(nuevoProducto);
    }

}
