package com.softwareCelestial.multis;

import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Esteban Sancho
 * @version 1
 * */
public class MultiCliente {

    public MultiCliente() {

    }

    /**
     * Metodo que recibe una instancia de cliente y la registra en la BD, además, hace el registro en la tabla intermedia
     * contactos_cliente y envía los contactos del cliente a ser registrados en la BD por medio de sus Multis.
     *@param nuevoCliente instancia del nuevo cliente a registrar
     *@author Esteban Sancho
     *
     * */
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
            registroTablaClientesContacto(nuevoCliente);
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + cedJuridica + "'", true);

            while (rs.next()) {
                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES(" + rs.getInt("id_cliente") + ", '" + var + "')");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Metodo que registra el id del cliente y del contacto lider en la tabla intermedia contactos_cliente.
     * @param nuevoCliente instancia de cliente
     * @author Esteban Sancho
     * */
    public void registroTablaClientesContacto(Cliente nuevoCliente) {
            String idCliente = "";
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT id_cliente FROM cliente WHERE cedula_juridica = '" + nuevoCliente.getCedJuridica() + "'", true);
            while (rs.next()) {
                String idContactoLider = nuevoCliente.getContactoLider().getId();
                String idContactoTecnico = nuevoCliente.getContactoTecnico().getId();
                idCliente = rs.getString("id_cliente");
                BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES('"+idCliente+"', '" + idContactoLider + "')");
                registrarContactoTecnicoTablaClientesContacto(idContactoTecnico, idCliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Clientes Contacto");
        }
    }

    /**
     * Metodo que registra al contacto tecnico en la tabla contactos_cliente
     * @param idContacto id del contacto a registrar
     * @param idCliente id del cliente al que pertenece el contacto
     * @author Esteban Sancho
     * */
    public void registrarContactoTecnicoTablaClientesContacto(String idContacto, String idCliente){
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("INSERT INTO contactos_cliente(id_cliente, id_contacto) VALUES ('"+idCliente+"', '"+idContacto+"')");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que recibe la información general del cliente para modificar su informacion en la tabla cliente
     * @param pCedJuridica cedula juridica del cliente
     * @param pNombre nombre del cliente
     * @param pRazonSocial razon social del cliente
     * @param pLatitud latitud de la ubicacion del cliente
     * @param pLongitud longitud de la ubicacion del cliente
     * @param pDireccionExacta direccion del cliente
     * @param pLogo URL del logo del cliente
     * @author Esteban Sancho
     * */
    public void modificarCliente(String pCedJuridica, String pNombre, String pRazonSocial, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo) {
        try {
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE cliente SET razon_social='" + pRazonSocial + "', latitud='" + pLatitud + "', longitud = '" + pLongitud + "', direccion='" + pDireccionExacta + "', logo='" + pLogo + "', nombre='"+pNombre+"' WHERE cedula_juridica ='" + pCedJuridica + "'");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modificar cliente");
        }
    }

    /**
     * Metodo que toma la información de un cliente desde la base de datos, sus telefonos, contactos y telefonos de contactos,
     * inicializa una instancia de contactos, consigue los telefonos del cliente, inicializa una instancia de cliente
     * @param idCliente id del cliente a listar
     * @returns cliente instancia de cliente con toda la información correspondiente
     * @author Esteban Sancho
     *
     *
     * */
    public Cliente listarCliente(int idCliente) {
        Cliente cliente = new Cliente();
        Contacto contactoLider = new Contacto();
        Contacto contactoTecnico = new Contacto();
        ArrayList<String> telefonos;
        ArrayList<Contacto> contactos = new ArrayList<>();
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            String query = "SELECT * FROM cliente WHERE id_cliente="+idCliente+"";
            rs = BD.ejecutarSQL(query, true);
            String nombre;
            String cedulaJuridica;
            String razonSocial;
            String latitud;
            String longitud;
            String direccion;
            String logo;
            while(rs.next()){
                telefonos = obtenerTelefonosCliente(idCliente);
                contactos = obtenerContactosCliente(idCliente);
                contactoLider = contactos.get(0);
                contactoTecnico = contactos.get(1);
                cedulaJuridica = rs.getString("cedula_juridica");
                razonSocial = rs.getString("razon_social");
                latitud = rs.getString("latitud");
                longitud = rs.getString("longitud");
                direccion = rs.getString("direccion");
                logo = rs.getString("logo");
                nombre = rs.getString("nombre");
                cliente = new Cliente(nombre, razonSocial, cedulaJuridica, latitud, longitud,direccion, logo, telefonos, contactoLider, contactoTecnico);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return cliente;
    }

    /**
     * Metodo que retorna los contactos dado un id de cliente respectivo
     * @param idCliente id del cliente del que se ocupan los contactos
     * @return arreglo de tipo Contacto con los contactos respectivos del cliente
     * @author Esteban Sancho
     * */
    public ArrayList<Contacto> obtenerContactosCliente(int idCliente){
        ArrayList<Contacto> contactos = new ArrayList<>();
        int tipo = 1;
//        ArrayList<String> telefonos = new ArrayList<>();
        String idContacto = "";
        try{

            AccesoBD BD = Conector.getConector();
            String query = "SELECT c.* FROM contacto as c INNER JOIN contactos_cliente as cc ON cc.id_contacto = c.id_contacto INNER JOIN cliente as cl ON cl.id_cliente = cc.id_cliente WHERE cc.id_cliente ="+idCliente+" AND c.tipo="+tipo+"";
            ResultSet rs = BD.ejecutarSQL(query, true);
            String nombre;
            String apellidos;
            String puesto;
            String correo;
            Contacto contacto;
            while(rs.next()){
                idContacto = rs.getString("id_contacto");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                puesto = rs.getString("puesto");
                correo = rs.getString("correo");
                ArrayList<String> telefonos = obtenerTelefonosContacto(idContacto);
                contacto = new Contacto(idContacto,nombre, apellidos, puesto, correo, telefonos);
                contactos.add(contacto);
                Contacto contactoTecnico = obtenerContactoTecnico(idCliente);
                contactos.add(contactoTecnico);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return contactos;
    }

    public Contacto obtenerContactoTecnico(int idCliente){
        int tipo = 2;
        String idContacto = "";
        ArrayList<String> telefonos = new ArrayList<>();
        Contacto contactoTecnico = new Contacto();
        try{

            AccesoBD BD = Conector.getConector();
            String query = "SELECT c.* FROM contacto as c INNER JOIN contactos_cliente as cc ON cc.id_contacto = c.id_contacto INNER JOIN cliente as cl ON cl.id_cliente = cc.id_cliente WHERE cc.id_cliente ="+idCliente+" AND c.tipo="+tipo+"";
            ResultSet rs = BD.ejecutarSQL(query, true);
            String nombre;
            String apellidos;
            String puesto;
            String correo;
            Contacto contacto;
            while(rs.next()){
                idContacto = rs.getString("id_contacto");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                puesto = rs.getString("puesto");
                correo = rs.getString("correo");
                telefonos = obtenerTelefonosContacto(idContacto);
                return contacto = new Contacto(idContacto,nombre, apellidos, puesto, correo, telefonos);
            }
            return null;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Metodo que retorna los telefonos de un contacto dado
     * @param idContacto id del contacto del que se ocupan los telefonos
     * @returns arreglo con los telefonos correspondientes al contacto
     * @author Esteban Sancho
     * */
    public ArrayList<String> obtenerTelefonosContacto(String idContacto){
        ArrayList<String> telefonos = new ArrayList<>();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = BD.ejecutarSQL("SELECT * FROM telefonos_contacto WHERE id_contacto='"+idContacto+"'", true);
            while(rs.next()){
                telefonos.add(rs.getString("telefono"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return telefonos;
    }


    /**
     * Metodo que retorna los telefonos de un cliente con respecto a un id proporcionado
     * @param idCliente id del cliente del que se ocupan los telefonos
     * @return Arreglo de telefonos correspondientes al cliente brindado
     * @author Esteban Sancho
     * */
    public ArrayList<String > obtenerTelefonosCliente(int idCliente){
        ArrayList<String> telefonos = new ArrayList<>();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = BD.ejecutarSQL("SELECT * FROM telefonos_cliente WHERE id_cliente='"+idCliente+"'", true);
            while(rs.next()){
                telefonos.add(rs.getString("telefono"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return telefonos;
    }


    /**
     * Metodo que lista a todos los clientes registrados, creando las instancias de contacto, arreglos de telefonos y cliente correspondientes
     * @return Arreglo de tipo Cliente, con cada cliente registrado
     * @author Esteban Sancho
     * */
    public ArrayList<Cliente> listarClientes(){
        Cliente cliente = new Cliente();
        ArrayList<Cliente> clientes = new ArrayList<>();
        Contacto contactoLider = new Contacto();
        Contacto contactoTecnico = new Contacto();
        ArrayList<String> telefonos;
        ArrayList<Object> clienteCompleto = new ArrayList<>();
        ArrayList<Contacto> contactos = new ArrayList<>();
        try {
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT * FROM cliente", true);
            while(rs.next()){
                telefonos = obtenerTelefonosCliente(rs.getInt("id_cliente"));
                contactos = obtenerContactosCliente(rs.getInt("id_cliente"));
                contactoLider = contactos.get(0);
                contactoTecnico = contactos.get(1);
                cliente = new Cliente(rs.getString("nombre"),rs.getString("cedula_juridica"), rs.getString("razon_social"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("direccion"), rs.getString("logo"), telefonos,contactoLider, contactoTecnico);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return clientes;
    }

    /**
     * Metodo que devuelve el id del cliente de la BD basado en su cedula juridica
     * @param cedulaJuridica cedula juridica del cliente
     * @return int con el id del cliente
     * @author Esteban Sancho
     * */
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


    /**
     * Metodo que actualiza los telefonos de un cliente en la BD cuando se solicita modificar
     * @param idCliente id de la BD del cliente
     * @param telefonos arreglo de los telefonos del cliente
     * @author Esteban Sancho
     * */
    public void actualizarTelefonos(int idCliente, ArrayList<String> telefonos){
        try{
            limpiarTelefonos(idCliente);
            AccesoBD BD = Conector.getConector();

                for (String var : telefonos) {
                    BD.ejecutarSQL("INSERT INTO telefonos_cliente(id_cliente, telefono) VALUES(" + idCliente + ", '" + var + "')");
                }


        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Actualizacion telefono");
        }
    }

    /**
     * Metodo que elimina todos los telefonos del cliente cuando se solicita modificar los telefonos, esto para que no
     * haya conflicto cuando se ingresen los nuevos telefonos
     * @param idCliente id del cliente
     * @author Esteban Sancho
     * */
    public void limpiarTelefonos(int idCliente){
        try{
            AccesoBD BD = Conector.getConector();

            BD.ejecutarSQL("DELETE FROM telefonos_cliente WHERE id_cliente = '"+idCliente+"'");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que retorna todos los telefonos de un cliente basado en su id de la BD
     * @param idCliente id del cliente
     * @return arreglo de tipo String con todos los telefonos del cliente
     * @author Esteban Sancho
     * */
    public ArrayList<String> imprimirTelefonos(int idCliente){
        ArrayList<String>telefonos = new ArrayList<>();
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs =BD.ejecutarSQL("SELECT * FROM telefonos_cliente WHERE id_cliente ="+idCliente+"", true);
            while(rs.next()){
                telefonos.add(rs.getString("telefono"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return telefonos;
    }

    /**
     * Metodo que valida si el cliente se encuentra registrado previamente
     * @param cedJuridica cedula juridica del cliente
     * @return valor booleano en true si el cliente no se encuentra registrado
     * @author Esteban Sancho
     * */
    public boolean validarCliente(String cedJuridica){
        try{
            AccesoBD BD = Conector.getConector();
            ResultSet rs = null;
            rs = BD.ejecutarSQL("SELECT nombre FROM cliente WHERE cedula_juridica = '"+cedJuridica+"'", true);
            if(!rs.next()){
                return true;
            }
            return false;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que retorna una instancia del cliente por medio del id de una instalacion
     * @param idInstalacion id de la instalacion
     * @return clienteEncontrado instancia de cliente
     * @author Leonardo Jimenez
     * */
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