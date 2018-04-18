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
    /**
     * Inicializa la instancia de version(la cual es atributo de producto) y la instancia de producto para registrarlos
     * en la base de datos.
     *@param nombre nombre del producto
     *@param logo URL del logo del producto
     *@param descripcion descripcion del producto
     *@param idProducto  idProducto codigo de identificacion del producto
     *@param versionActual numero de versión del producto
     *@author Esteban Sancho
     * */
    public void crearProducto(String nombre, String logo, String descripcion, String idProducto, String versionActual){
        GestorVersion gestorVersion = new GestorVersion();
        Version versionInicial = gestorVersion.crearVersion(versionActual);
        Producto nuevoProducto = new Producto(nombre, logo, descripcion, idProducto, versionInicial);
        multiProducto.registrarProducto(nuevoProducto);
    }

    /**
     * Se comunica con el multi de producto para mostrar un producto para que el usuario lo visualice antes de modificarlo
     *@param codigoProducto el id del producto a mostrar
     *@return producto devuelve la instancia del producto para ser mostrada al usuario
     *@author Esteban Sancho
     * */
    public String imprimirProducto(String codigoProducto){
        String producto = multiProducto.listarProducto(codigoProducto).toString();
        return producto;
    }

    /**
     * Se comunica con el multi de producto para modificar un producto seleccionado
     * @param nombre nombre del producto
     * @param logo URL del logo del producto
     * @param descripcion descripción del producto
     * @param codigoProducto codigo de identificación del producto
     * @author Esteban Sancho
     * */
    public void modificarProducto(String nombre, String logo, String descripcion, String codigoProducto ){
        multiProducto.modificarProducto(nombre, logo, descripcion, codigoProducto);
    }

    /**
     * Lista todos los productos registrados
     * @return productos Es un arreglo de toString de cada producto retornado de la BD
     * @author Esteban Sancho
     * */
    public ArrayList<String> listarProductos(){
        ArrayList<String> productos = new ArrayList<>();
        for(Producto var:multiProducto.listarProductos()){
            productos.add(var.toString());
        }
        return productos;
    }

    /**
     * Recibe el id del producto para comunicarse con el multiProducto para eliminarlo.
     * @param idProducto id del producto a eliminar
     * @author Esteban Sancho
     * */
    public void eliminarProducto(String idProducto){
        multiProducto.eliminarProducto(idProducto);
    }

    /**
     * Envía el id del producto al multi de producto para validar que no se encuentre ya registrado
     *@param idProducto id del producto
     *@return devuelve un valor booleano en true si el producto no se encuentra previamente registrado
     * */
    public boolean validarProducto(String idProducto){
        return multiProducto.validarProducto(idProducto);
    }
}
