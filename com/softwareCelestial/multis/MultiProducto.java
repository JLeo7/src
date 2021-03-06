package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Producto;
import com.softwareCelestial.cl.Version;
import com.softwareCelestial.gestor.GestorVersion;
import com.softwareCelestial.multis.MultiVersion;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class MultiProducto {

    GestorVersion gestorVersion = new GestorVersion();

    public MultiProducto(){

    }
    /**
     * Metodo que registra un producto en la BD
     * @param nuevoProducto instancia de producto a registrar
     * @author Esteban Sancho
     * */
    public void registrarProducto(Producto nuevoProducto){
        try{
            String estado = "Activo";
            AccesoBD BD = Conector.getConector();
            MultiVersion multiVersion = new MultiVersion();
            multiVersion.registrarVersionInicial(nuevoProducto.getVersionActual().getNumero(), nuevoProducto.getVersionActual().getFechaCreacion());
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT MAX(id_version) FROM tversion", true);

            while(rs.next()){
                BD.ejecutarSQL("INSERT INTO producto(id_version ,nombre, descripcion, logo, estado, codigo) VALUES ("+rs.getInt("MAX(id_version)")+", '"+nuevoProducto.getNombre()+"', '"+nuevoProducto.getDescripcion()+"', '"+nuevoProducto.getLogo()+"', '"+estado+"', '"+nuevoProducto.getIdProducto()+"')");
            }

        } catch(Exception e) {

        }
    }

    /**
     * Metodo que lista un producto basado en su codigo
     * @param codigoProducto id del producto a listar
     * @return producto instancia de producto listado
     * @author Esteban Sancho
     * */
    public Producto listarProducto(String codigoProducto){
        Producto producto = new Producto();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT  p.*, v.numero as numeroVersion FROM producto as p INNER JOIN tversion as v ON p.id_version = v.id_version WHERE p.codigo ='"+codigoProducto+"'", true);

            while(rs.next()){
                Version versionActual = gestorVersion.crearVersion(rs.getString("numeroVersion"));
                producto = new Producto(rs.getString("nombre"), rs.getString("logo"), rs.getString("descripcion") , codigoProducto, versionActual);
            }
        } catch(Exception e){

        }
        return producto;
    }

    /**
     * Metodo que modifica un producto sin su version, en la BD
     * @param nombre nombre del producto
     * @param logo URL del logo del producto
     * @param descripcion descripcion del producto
     * @param codigoProducto id del producto a modificar
     * @author Esteban Sancho
     * */
    public void modificarProducto(String nombre, String logo, String descripcion, String codigoProducto){
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE producto SET nombre='"+nombre+"', logo='"+logo+"', descripcion='"+descripcion+"' WHERE codigo ='"+codigoProducto+"'");
        } catch(Exception e){

        }
    }
    /**
     *Metodo que devuelve todos los productos
     *@return Arreglo con todos los productos
     *@author Esteban Sancho
     * */
    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        Producto cliente = new Producto();
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT * FROM producto", true);
            while(rs.next()){
                cliente = new Producto(rs.getString("nombre"), rs.getString("logo"), rs.getString("descripcion"), rs.getString("id_producto"));
                productos.add(cliente);
            }
        } catch (Exception e) {

        }
        return productos;
    }

    /**
     * Metodo que elimina un producto de la BD basado en un id
     * @param idProducto id del producto a eliminar
     * @author Esteban Sancho
     * */
    public void eliminarProducto(String idProducto){
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("DELETE FROM producto WHERE id_producto ='"+idProducto+"'");
        } catch(Exception e){

        }
    }

    /**
     * Metodo que valida si un producto no se encuentra registrado
     * @param idProducto id del producto a validar
     * @return valor booleano en true si el producto no se encuentra previamente registrado
     * @author Esteban Sancho
     * */
    public boolean validarProducto(String idProducto) {
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT * FROM producto WHERE id_producto='"+idProducto+"'", true);
            if (!rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }
    }


    public Producto obtenerProductoPorIdInstalacion (int idInstalacion) {
        try {
            AccesoBD aBD;
            Producto productoEncontrado;
            ResultSet rs;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_producto_por_id_instalacion("+idInstalacion+")",true);

            if (rs.next()) {
                return productoEncontrado = new Producto(rs.getString("nombre"),rs.getString("logo"),rs.getString("descripcion"),rs.getString("id_producto"));
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Producto obtenerProductoPorId(int idProducto){
        try {
            AccesoBD aBD;
            Producto productoEncontrado = null;
            ResultSet rs;
            MultiVersion mVersion;
            mVersion = new MultiVersion();
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_producto_por_id("+idProducto+")",true);
            String nombre = "";
            String logo = "";
            String descripcion = "";
            int idVersion = 0;
            Version version;
            while (rs.next()) {
                nombre = rs.getString("nombre");
                logo = rs.getString("logo");
                descripcion = rs.getString("descripcion");
                idVersion = rs.getInt("id_version");
            }
            version = mVersion.obtenerVersionPorId(idVersion);
            productoEncontrado = new Producto(nombre,logo,descripcion,idProducto+"",version);
            return productoEncontrado;

        } catch (Exception e) {
            return null;
        }
    }

    public int obtenerIdProductoPorCodigo(String codigoProducto){
        try{
            AccesoBD aBD;
            int idProducto = -1;
            ResultSet rs;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("select tp.id_producto from producto as tp\n" +
                    "where tp.codigo = '"+codigoProducto+"'",true);
            if (rs.next()){
                idProducto = rs.getInt("id_producto");
            }
            return idProducto;
        } catch (Exception e) {
            return -1;
        }
    }
}
