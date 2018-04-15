package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Producto;

import java.sql.ResultSet;

public class MultiProducto {

    public MultiProducto(){

    }

    public void registrarProducto(Producto nuevoProducto){
        try{
            String estado = "Activo";
            AccesoBD BD = Conector.getConector();
            MultiVersion multiVersion = new MultiVersion();
            multiVersion.registrarVersionInicial(nuevoProducto.getVersionActual().getNumero(), nuevoProducto.getVersionActual().getFechaCreacion());
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT MAX(id_version) FROM tversion", true);

            while(rs.next()){
                BD.ejecutarSQL("INSERT INTO producto(id_version ,nombre, descripcion, logo, estado) VALUES ("+rs.getInt("MAX(id_version)")+", '"+nuevoProducto.getNombre()+"', '"+nuevoProducto.getDescripcion()+"', '"+nuevoProducto.getLogo()+"', '"+estado+"')");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
