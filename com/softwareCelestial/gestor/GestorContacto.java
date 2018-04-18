package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Contacto;
import com.softwareCelestial.multis.MultiContacto;

import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class GestorContacto {

    MultiContacto multiContacto = new MultiContacto();
    public GestorContacto(){

    }

    /**
     * Metodo que inicializa una instancia de contacto
     * @param id id del contacto
     * @param nombre nombre del contacto
     * @param apellidos apellidos del contacto
     * @param puesto puesto del contacto
     * @param correo direccion de correo electronico del contacto
     * @param telefonos arreglo de telefonos del contacto
     * @return nuevoContacto instancia del contacto creada
     * @author Esteban Sancho
     * */
    public Contacto registrarContacto(String id, String nombre, String apellidos, String puesto, String correo, ArrayList<String> telefonos){
            Contacto nuevoContacto = new Contacto(id, nombre, apellidos, puesto, correo, telefonos);
            return nuevoContacto;
    }

    /**
     * Metodo que recibe el id del contacto y envía al multi de contacto para verificar que no se encuentre registrado
     * @param idContacto id del contacto siendo registrado
     * @returns valor booleano en true si el contacto no se encuentra previamente registrado
     * @author Esteban Sancho
     * */
    public boolean validarContacto(String idContacto){
        return multiContacto.validarContacto(idContacto);
    }

}
