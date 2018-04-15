package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MultiCliente {

    public MultiCliente() {

    }

    public void registrarCliente(Cliente nuevoCliente) {
        try {
            String nombre = nuevoCliente.getNombre();
            String razonSocial = nuevoCliente.getRazonSocial();
            String cedJuridica = nuevoCliente.getCedJuridica();
            String latitud = nuevoCliente.getLatitud();
            String longitud = nuevoCliente.getLongitud();
            String direccionExacta = nuevoCliente.getDireccionExacta();
            String logo = nuevoCliente.getLogo();
            ArrayList<String> telefonos = nuevoCliente.getTelefonos();
            Contacto contactoLider = nuevoCliente.getContactoLider();
            Contacto contactoTecnico = nuevoCliente.getContactoTecnico();
            MultiContacto mContacto = new MultiContacto();
            mContacto.registrarContacto(contactoLider, 1);
            mContacto.registrarContacto(contactoTecnico, 2);
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("INSERT INTO cliente(cedula_juridica, razon_social, latitud, longitud, direccion, logo, nombre) VALUES('"+cedJuridica+"', '" + razonSocial + "', '"+latitud+"', '"+longitud+"', '"+direccionExacta+"', '"+logo+"', '"+nombre+"')");
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);

            while(rs.next()){
                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES("+rs.getInt("id_cliente")+", '"+var+"')");
                }
            }
            rs.close();
            registroTablaClientesContacto(cedJuridica, nuevoCliente);
//            String idContactoLider = nuevoCliente.getContactoLider().getId();
//            String idContactoTecnico = nuevoCliente.getContactoTecnico().getId();
//            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);
//            while(rs.next()){
//                idCliente = rs.getInt("id_cliente");
//                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES("+idCliente+", '"+idContactoLider+"')");
//            }
//
//            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);
//            while(rs.next()){
//                idCliente = rs.getInt("id_cliente");
//                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES("+idCliente+", '"+idContactoTecnico+"')");
//            }

        }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }


     public void registroTablaClientesContacto(String cedJuridica, Cliente nuevoCliente){

        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);
            while(rs.next()){
                String idContactoLider = nuevoCliente.getContactoLider().getId();
                String idContactoTecnico = nuevoCliente.getContactoTecnico().getId();
                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES("+rs.getInt("id_cliente")+", '"+idContactoLider+"')");
                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES("+rs.getInt("id_cliente")+", '"+idContactoTecnico+"')");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Clientes Contacto");
        }
     }

    public void modificarCliente(Cliente nuevoCliente) {
        try {
            String nombre = nuevoCliente.getNombre();
            String razonSocial = nuevoCliente.getRazonSocial();
            String cedJuridica = nuevoCliente.getCedJuridica();
            String latitud = nuevoCliente.getLatitud();
            String longitud = nuevoCliente.getLongitud();
            String direccionExacta = nuevoCliente.getDireccionExacta();
            String logo = nuevoCliente.getLogo();
            ArrayList<String> telefonos = nuevoCliente.getTelefonos();
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE TABLE cliente SET razon_social = '"+razonSocial+"', latitud = '"+latitud+"', longitud = '"+longitud+"', direccion = '"+direccionExacta+"', logo = '"+logo+"', nombre = '"+nombre+"' WHERE cedula_juridica = '"+cedJuridica+"'");
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);
            BD.ejecutarSQL("DELETE FROM telefonos_cliente WHERE id_cliente = '"+rs+"'");
            while(rs.next()){
                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES("+rs.getInt("id_cliente")+", '"+var+"')");
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Modificar cliente");
        }
    }

}
