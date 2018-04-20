package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Contacto;

import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class MultiContacto {

    public MultiContacto(){

    }

    /**
     * Metodo que registra un contacto en la BD
     * @param pContacto instancia del contacto a registrar
     * @param tipo el tipo de contacto, lider o tecnico
     * @author Esteban Sancho
     * */
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

        }

    }

    /**
     * Metodo que valida si el contacto no se encuentra previamente registrado
     *@param idContacto id del contact
     *@returns valor booleano en true si el contacto no se encuentra registrado previamente
     *@author Esteban Sancho
     * */
    public boolean validarContacto(String idContacto) {
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT nombre FROM contacto WHERE id_contacto = '" + idContacto + "'", true);
            if (!rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {

            return false;
        }
    }

}
