package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Producto;
import com.softwareCelestial.cl.Version;
import com.softwareCelestial.multis.MultiProducto;

import java.util.ArrayList;

public class GestorProducto {

    MultiProducto multiProducto = new MultiProducto();
    public GestorProducto(){

    }

    public void crearProducto(String nombre, String logo, String descripcion, String idProducto, String versionActual){
        GestorVersion gestorVersion = new GestorVersion();
        Version versionInicial = gestorVersion.crearVersion(versionActual);
        Producto nuevoProducto = new Producto(nombre, logo, descripcion, idProducto, versionInicial);
        multiProducto.registrarProducto(nuevoProducto);
    }

    public String imprimirProducto(String idProducto){
        String producto = multiProducto.listarProducto(idProducto).toString();
        return producto;
    }

    public void modificarProducto(String nombre, String logo, String descripcion, String idProducto ){
        multiProducto.modificarProducto(nombre, logo, descripcion, idProducto);
    }

    public ArrayList<String> listarProductos(){
        ArrayList<String> productos = new ArrayList<>();
        for(Producto var:multiProducto.listarProductos()){
            productos.add(var.toString());
        }
        return productos;
    }

    public void eliminarProducto(String idProducto){
        multiProducto.eliminarProducto(idProducto);
    }

    public boolean validarProducto(String idProducto){
        return multiProducto.validarProducto(idProducto);
    }
}
