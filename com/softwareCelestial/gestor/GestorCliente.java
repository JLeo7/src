package com.softwareCelestial.gestor;

import CapaAccesoBD.AccesoBD;
import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;
import com.softwareCelestial.multis.MultiCliente;

import java.util.ArrayList;

public class GestorCliente {

    MultiCliente multiCliente = new MultiCliente();

    public GestorCliente(){

    }

    public void crearCliente(String pNombre, String pRazonSocial, String pCedJuridica, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo, ArrayList<String> pTelefonos, String idContacto1, String nombreContacto1, String apellidosContacto1, String puestoContacto1, String correoContacto1, ArrayList<String> telefonosContacto1, String idContacto2, String nombreContacto2, String apellidosContacto2, String puestoContacto2, String correoContacto2, ArrayList<String> telefonosContacto2 ){
        GestorContacto gestorContacto = new GestorContacto();
        Contacto contactoLider = gestorContacto.registrarContacto(idContacto1, nombreContacto1, apellidosContacto1, puestoContacto1, correoContacto1, telefonosContacto1);
        Contacto contactoTecnico = gestorContacto.registrarContacto(idContacto2, nombreContacto2, apellidosContacto2, puestoContacto2, correoContacto2, telefonosContacto2);
        Cliente nuevoCliente = new Cliente(pNombre, pRazonSocial, pCedJuridica, pLatitud, pLongitud, pDireccionExacta, pLogo, pTelefonos,             contactoLider, contactoTecnico);
        multiCliente.registrarCliente(nuevoCliente);
    }

    public void modificarCliente(String pCedJuridica, String pNombre, String pRazonSocial, String pLatitud, String pLongitud, String pDireccionExacta, String pLogo){
        multiCliente.modificarCliente(pCedJuridica, pNombre, pRazonSocial, pLatitud, pLatitud, pDireccionExacta, pLogo);
    }

    public String imprimirCliente(String idCliente){

        String cliente = multiCliente.listarCliente(idCliente).toString();
        return cliente;
    }

    public void actualizarTelefonos(String idCliente, ArrayList<String> telefonos){
        multiCliente.actualizarTelefonos(idCliente, telefonos);
    }

    public ArrayList<String> imprimirTelefonos(String cedulaJuridica){
       return multiCliente.imprimirTelefonos(cedulaJuridica);
    }

    public ArrayList<String> listarClientes(){
        ArrayList<String> clientes = new ArrayList<>();
        for(Cliente var:multiCliente.listarClientes()){
            clientes.add(var.toString());
        }
        return clientes;
    }

    public boolean validarCliente(String cedulaJuridica){
        return multiCliente.validarCliente(cedulaJuridica);

    }
}
