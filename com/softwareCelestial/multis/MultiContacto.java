package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Contacto;

import java.util.ArrayList;

public class MultiContacto {

    public MultiContacto(){

    }

    public void registrarContacto(Contacto pContacto, int tipo){
        try{
            String id = pContacto.getId();
            String nombre = pContacto.getNombre();
            String apellidos = pContacto.getApellidos();
            String puesto = pContacto.getPuesto();
            String correo = pContacto.getCorreo();
            ArrayList<String> telefonos = pContacto.getTelefonos();

            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("INSERT INTO contacto(id_contacto, nombre, apellidos, puesto, correo, tipo) VALUES('"+id+"', '"+nombre+"', '"+apellidos+"', '"+puesto+"', '"+correo+"', '"+tipo+"')");

            for(String var: telefonos){
                BD.ejecutarSQL("INSERT INTO telefonos_contacto(telefono, id_contacto) VALUES('"+var+"', '"+id+"')");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
