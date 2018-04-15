package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Cliente;
import com.softwareCelestial.cl.Contacto;
import com.softwareCelestial.multis.MultiCliente;

import java.util.ArrayList;

public class GestorCliente {

    public GestorCliente(){

    }

    public void crearCliente(String pRazonSocial, String pCedJuridica, String pLatitud, String pLongitud, String pDireccionExacta,           String pLogo, ArrayList<String> pTelefonos, Contacto pContactoLider, Contacto pContactoTecnico){
        Cliente nuevoCliente = new Cliente(pRazonSocial, pCedJuridica, pLatitud, pLongitud, pDireccionExacta, pLogo, pTelefonos,             pContactoLider, pContactoTecnico);
        MultiCliente mClienteNuevo = new MultiCliente();
        mClienteNuevo.registrarCliente(nuevoCliente);
    }

    public void modificarCliente(String pRazonSocial, String pCedJuridica, String pLatitud, String pLongitud, String pDireccionExacta,       String pLogo, ArrayList<String> pTelefonos, Contacto pContactoLider, Contacto pContactoTecnico){
        Cliente nuevoCliente = new Cliente(pRazonSocial, pCedJuridica, pLatitud, pLongitud, pDireccionExacta, pLogo, pTelefonos,             pContactoLider, pContactoTecnico);
        MultiCliente mClienteNuevo = new MultiCliente();
        mClienteNuevo.registrarCliente(nuevoCliente);
    }

}
