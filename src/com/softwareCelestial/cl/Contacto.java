package src.com.softwareCelestial.cl;

import java.util.ArrayList;

public class Contacto {

    private String id;
    private String nombre;
    private String apellidos;
    private String puesto;
    private String correo;
    private ArrayList<String> telefonos;

    public Contacto(){

    }

    public Contacto(String id, String nombre, String apellidos, String puesto, String correo, ArrayList<String> telefonos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.correo = correo;
        this.telefonos = telefonos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", puesto='" + puesto + '\'' +
                ", correo='" + correo + '\'' +
                ", telefonos=" + telefonos +
                '}';
    }
}
