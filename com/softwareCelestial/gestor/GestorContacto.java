package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Contacto;

import java.util.ArrayList;

public class GestorContacto {

    public GestorContacto(){

    }

    public Contacto registrarContacto(String id, String nombre, String apellidos, String puesto, String correo, ArrayList<String> telefonos){
            Contacto nuevoContacto = new Contacto(id, nombre, apellidos, puesto, correo, telefonos);
            return nuevoContacto;
    }

}
