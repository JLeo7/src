package com.softwareCelestial.cl;
import java.awt.*;
import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String razonSocial;
    private String cedJuridica;
    private String latitud;
    private String longitud;
    private String direccionExacta;
    private String logo;
    private ArrayList<String> telefonos;
    private Contacto contactoLider;
    private Contacto contactoTecnico;

    public Cliente() {
    }

    public Cliente(String nombre, String razonSocial, String cedJuridica, String latitud, String longitud, String direccionExacta, String logo, ArrayList<String> telefonos, Contacto contactoLider, Contacto contactoTecnico) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cedJuridica = cedJuridica;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccionExacta = direccionExacta;
        this.logo = logo;
        this.telefonos = telefonos;
        this.contactoLider = contactoLider;
        this.contactoTecnico = contactoTecnico;
    }

    public Cliente(String nombre, String razonSocial, String cedJuridica, String latitud, String longitud, String direccionExacta, String logo){
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.cedJuridica = cedJuridica;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccionExacta = direccionExacta;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCedJuridica() {
        return cedJuridica;
    }

    public void setCedJuridica(String cedJuridica) {
        this.cedJuridica = cedJuridica;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccionExacta() {
        return direccionExacta;
    }

    public void setDireccionExacta(String direccionExacta) {
        this.direccionExacta = direccionExacta;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Contacto getContactoLider() {
        return contactoLider;
    }

    public void setContactoLider(Contacto contactoLider) {
        this.contactoLider = contactoLider;
    }

    public Contacto getContactoTecnico() {
        return contactoTecnico;
    }

    public void setContactoTecnico(Contacto contactoTecnico) {
        this.contactoTecnico = contactoTecnico;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", cedJuridica='" + cedJuridica + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", direccionExacta='" + direccionExacta + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
