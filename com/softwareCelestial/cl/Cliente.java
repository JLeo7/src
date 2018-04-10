package com.softwareCelestial.cl;
import java.awt.*;
import java.util.ArrayList;

public class Cliente {
    private String razonSocial;
    private String cedJuridica;
    private String latitud;
    private String longitud;
    private String direccionExacta;
    private Image logo;
    private ArrayList<String> telefonos;
    private Contacto contactoLider;
    private Contacto contactoTecnico;

    public Cliente() {
    }

    public Cliente(String razonSocial, String cedJuridica, String latitud, String longitud, String direccionExacta, Image logo, ArrayList<String> telefonos, Contacto contactoLider, Contacto contactoTecnico) {
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
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
                "razonSocial='" + razonSocial + '\'' +
                ", cedJuridica='" + cedJuridica + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", direccionExacta='" + direccionExacta + '\'' +
                ", logo=" + logo +
                ", telefonos=" + telefonos +
                ", contactoLider=" + contactoLider +
                ", contactoTecnico=" + contactoTecnico +
                '}';
    }
}
