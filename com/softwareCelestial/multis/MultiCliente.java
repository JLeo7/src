package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;

import java.sql.Array;
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
            BD.ejecutarSQL("INSERT INTO cliente(cedula_juridica, razon_social, latitud, longitud, direccion, logo, nombre) VALUES('" + cedJuridica + "', '" + razonSocial + "', '" + latitud + "', '" + longitud + "', '" + direccionExacta + "', '" + logo + "', '" + nombre + "')");
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);

            while (rs.next()) {
                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES(" + rs.getInt("id_cliente") + ", '" + var + "')");
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void registroTablaClientesContacto(String cedJuridica, Cliente nuevoCliente) {

        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);
            while (rs.next()) {
                String idContactoLider = nuevoCliente.getContactoLider().getId();
                String idContactoTecnico = nuevoCliente.getContactoTecnico().getId();
                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES(" + rs.getInt("id_cliente") + ", '" + idContactoLider + "')");
                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES(" + rs.getInt("id_cliente") + ", '" + idContactoTecnico + "')");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Clientes Contacto");
        }
    }

    public void modificarCliente(String pCedJuridica, String pNombre, String pRazonSocial, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo) {
        try {
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE cliente SET razon_social='" + pRazonSocial + "', latitud='" + pLatitud + "', longitud = '" + pLongitud + "', direccion='" + pDireccionExacta + "', logo='" + pLogo + "', nombre='"+pNombre+"' WHERE cedula_juridica ='" + pCedJuridica + "'");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modificar cliente");
        }
    }

    public Cliente listarCliente(String cedulaJuridica) {
        Cliente cliente = new Cliente();
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT * FROM cliente WHERE cedula_juridica ='" + cedulaJuridica + "'", true);
            while(rs.next()){
                cliente = new Cliente(rs.getString("nombre"), rs.getString("razon_social"), rs.getString("cedula_juridica"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("direccion"), rs.getString("logo"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return cliente;
    }

    public ArrayList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente();
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT * FROM cliente", true);
            while(rs.next()){
                cliente = new Cliente(rs.getString("nombre"), rs.getString("razon_social"), rs.getString("cedula_juridica"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("direccion"), rs.getString("logo"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public int obtenerIdCliente(String cedulaJuridica){
        int id = 0;
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica ='" + cedulaJuridica + "'", true);
            while(rs.next()){
                id = rs.getInt("id_cliente");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    public void actualizarTelefonos(String cedulaJuridica, ArrayList<String> telefonos){
        try{
            limpiarTelefonos(cedulaJuridica);
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedulaJuridica + "'", true);

            while (rs.next()) {
                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES(" + rs.getInt("id_cliente") + ", '" + var + "')");
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Actualizacion telefono");
        }
    }

    public void limpiarTelefonos(String cedulaJuridica){
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedulaJuridica + "'", true);

            while (rs.next()) {
                BD.ejecutarSQL("DELETE FROM telefonos_cliente WHERE id_cliente = '"+rs.getInt("id_cliente")+"'");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> imprimirTelefonos(String cedulaJuridica){
        ArrayList<String>telefonos = new ArrayList<>();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedulaJuridica + "'", true);
            while(rs.next()){
                telefonos = retornarTelefonos(rs.getString("id_cliente"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return telefonos;
    }

    public ArrayList<String> retornarTelefonos(String idCliente){
        ArrayList<String>telefonos = new ArrayList<>();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs =BD.ejecutarSQL("SELECT * FROM telefonos_cliente WHERE id_cliente ='"+idCliente+"'", true);
            while(rs.next()){
                telefonos.add(rs.getString("telefono"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return telefonos;
    }

    public Cliente obtenerClientePorIdInstalacion(int idInstalacion){
        try {
            AccesoBD aBD;
            ResultSet rs;
            Cliente clienteEncontrado;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_cliente_por_id_instalacion("+idInstalacion+")",true);
            if (rs.next()){
                return clienteEncontrado = new Cliente(rs.getString("nombre"),rs.getString("cedula_juridica"),rs.getString("razon_social"),rs.getString("latidud"),rs.getString("longitud"),rs.getString("direccion"),rs.getString("logo"));
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}