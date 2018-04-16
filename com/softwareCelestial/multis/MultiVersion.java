package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;

import java.time.LocalDate;

public class MultiVersion {

    public MultiVersion(){

    }

    public void registrarVersionInicial(String numeroVersion, LocalDate fechaCreacion){
        String fecha = fechaCreacion.getYear()+"-"+fechaCreacion.getMonthValue()+"-"+fechaCreacion.getDayOfMonth();
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("INSERT INTO tversion(numero, fecha_creacion) VALUES ('"+numeroVersion+"', '"+fecha+"')");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarVersion(String numeroVersion, String idProducto, LocalDate fechaCreacion){

        String fecha = fechaCreacion.getYear()+"-"+fechaCreacion.getMonthValue()+"-"+fechaCreacion.getDayOfMonth();
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE tversion AS v INNER JOIN producto AS p ON p.id_version = v.id_version SET v.numero = '"+numeroVersion+"', v.fecha_creacion = '"+fecha+"' WHERE (p.id_producto = '"+idProducto+"') AND (p.id_version = v.id_version)");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
