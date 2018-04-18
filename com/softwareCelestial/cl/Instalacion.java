package com.softwareCelestial.cl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Instalacion {
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private ArrayList<Tarea> tareas;
    private Cliente solicitante;
    private Producto productoInstalado;
    private Version versionProducto;


    public Instalacion(){

    }

    public Instalacion(LocalDate fecha, LocalTime hora, String estado, ArrayList<Tarea> tareas, Cliente solicitante,Producto productoInstalado,Version versionProducto) {
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.tareas = tareas;
        this.solicitante = solicitante;
        this.productoInstalado = productoInstalado;
        this.versionProducto = versionProducto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void addTareas(Tarea nuevaTarea) {
        tareas.add(nuevaTarea);
    }

    public Cliente getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Cliente solicitante) {
        this.solicitante = solicitante;
    }

    public Producto getProductoInstalado() {
        return productoInstalado;
    }

    public void setProductoInstalado(Producto productoInstalado) {
        this.productoInstalado = productoInstalado;
    }

    public Version getVersionProducto() {
        return versionProducto;
    }

    public void setVersionProducto(Version versionProducto) {
        this.versionProducto = versionProducto;
    }

    @Override
    public String toString() {
        return "Instalacion{" +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado='" + estado + '\'' +
                ", tareas=" + tareas +
                ", solicitante=" + solicitante.toString() +
                ", productoInstalado=" + productoInstalado.toString() +
                ", versionProducto=" + versionProducto.toString() +
                '}';
    }
}
