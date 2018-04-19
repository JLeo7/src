package com.softwareCelestial.gestor;

import CapaAccesoBD.AccesoBD;
import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;
import com.softwareCelestial.multis.MultiCliente;

import java.util.ArrayList;
/**
 * @author Esteban Sancho
 * @version 1
 * */
public class GestorCliente {

    MultiCliente multiCliente = new MultiCliente();

    public GestorCliente(){

    }
    /**
     * Metodo que inicializa las instancias de cliente y contactos y las envía al multi de cliente para ser registrado,
     * y al gestor de Contacto para que sea procesado y registrado correctamente.
     *@param pNombre nombre del cliente
     *@param pRazonSocial razon social del cliente
     *@param pCedJuridica cédula jurídica del cliente
     *@param pLatitud latitud de la ubicación del cliente
     *@param pLongitud longitud de la ubicacion del cliente
     *@param pDireccionExacta dirección exacta del cliente
     *@param pLogo URL del logo del cliente
     *@param pTelefonos arreglo de telefonos a registrar del cliente
     *@param idContacto1 id del contacto lider
     *@param nombreContacto1 nombre del contacto lider
     *@param apellidosContacto1 apellidos del contacto lider
     *@param puestoContacto1 puesto del contacto lider
     *@param correoContacto1 direccion de correo electronico del contacto lider
     *@param telefonosContacto1 arreglo de telefonos del contacto lider
     *@param idContacto2 id del contacto tecnico
     *@param nombreContacto2 nombre del contacto tecnico
     *@param apellidosContacto2 apellidos del contacto tecnico
     *@param puestoContacto2 puesto del contacto tecnico
     *@param correoContacto2 direccion de correo electronico del contacto tecnico
     *@param telefonosContacto2 arreglo de telefonos de contacto lidrer
     *@author Esteban Sancho
     *
     * */
    public void crearCliente(String pNombre, String pRazonSocial, String pCedJuridica, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo, ArrayList<String> pTelefonos, String idContacto1, String nombreContacto1, String apellidosContacto1, String puestoContacto1, String correoContacto1, ArrayList<String> telefonosContacto1, String idContacto2, String nombreContacto2, String apellidosContacto2, String puestoContacto2, String correoContacto2, ArrayList<String> telefonosContacto2){
        GestorContacto gestorContacto = new GestorContacto();
        Contacto contactoLider = gestorContacto.registrarContacto(idContacto1, nombreContacto1, apellidosContacto1, puestoContacto1, correoContacto1, telefonosContacto1);
        Contacto contactoTecnico = gestorContacto.registrarContacto(idContacto2, nombreContacto2, apellidosContacto2, puestoContacto2, correoContacto2, telefonosContacto2);
        Cliente nuevoCliente = new Cliente(pNombre, pRazonSocial, pCedJuridica, pLatitud, pLongitud, pDireccionExacta, pLogo, pTelefonos,             contactoLider, contactoTecnico);
        multiCliente.registrarCliente(nuevoCliente);
    }

    /**
     *Recibe la información general del cliente para enviar a modificar
     *
     *@param pCedJuridica cedula juridica del cliente
     *@param pNombre nombre del cliente
     *@param pRazonSocial razon social del cliente
     *@param pLatitud latitud de la ubicacion del cliente
     *@param pLongitud longitud de la ubicacion del cliente
     *@param pDireccionExacta direccion del cliente
     *@param pLogo URL de la imagen del logo
     *@author Esteban Sancho
     *
     * */
    public void modificarCliente(String pCedJuridica, String pNombre, String pRazonSocial, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo){
        multiCliente.modificarCliente(pCedJuridica, pNombre, pRazonSocial, pLatitud, pLongitud, pDireccionExacta, pLogo);
    }


    /**
     * Recibe el id del cliente y el arreglo de telefonos para modificar los telefonos
     * @param cedulaJuridica id del cliente al que pertenecen los telefonos
     * @param telefonos Arreglo con los nuevos telefonos
     * @author Esteban Sancho
     *
     * */
    public void actualizarTelefonos(String cedulaJuridica, ArrayList<String> telefonos){
        int idCliente = multiCliente.obtenerIdCliente(cedulaJuridica);
        multiCliente.actualizarTelefonos(idCliente, telefonos);
    }

    /**
     * Recibe la cedula juridica de un cliente para mostrar los telefonos registrados a la hora de modificar
     * @param cedulaJuridica cedula juridica del cliente al que se le estan modificando los telefonos
     * @return Arreglo de telefonos registrados bajo el cliente
     * @author Esteban Sancho
     * */
    public ArrayList<String> imprimirTelefonos(String cedulaJuridica){
        int idCliente = multiCliente.obtenerIdCliente(cedulaJuridica);
       return multiCliente.imprimirTelefonos(idCliente);
    }


    public ArrayList<String> listarClientes(){
        ArrayList<String> clientes = new ArrayList<>();
        for(Cliente var:multiCliente.listarClientes()){
            clientes.add(var.toString());
        }
        return clientes;
    }

    /**
     * Metodo que recibe la cedula juridica para enviar al multi Cliente a validar si está ya registrado
     * @param cedulaJuridica cedula juridica del cliente siendo registrado
     * @returns valor booleano en true si el cliente no se encuentra previamente registrado
     * @author Esteban Sancho
     * */
    public boolean validarCliente(String cedulaJuridica){
        return multiCliente.validarCliente(cedulaJuridica);

    }

    public String imprimirCliente(String cedJuridica){
        int idCliente = multiCliente.obtenerIdCliente(cedJuridica);
//        ArrayList<String> clienteImprimir = new ArrayList<>();
//        Cliente cliente = multiCliente.listarCliente(idCliente);
//        String cliente = cliente.toString();
//        String contacto;

        return multiCliente.listarCliente(idCliente).toString();
    }

//    public String toStringContacto(Contacto contacto){
//        String contactoString = "Identificación: " + contacto.getId() + "," + ;
//        return contactoString;
//    }

    public boolean modificarValidacion(String cedJuridica){
        int idCliente = multiCliente.obtenerIdCliente(cedJuridica);
        return multiCliente.validarClienteModificacion(idCliente);
    }

}
