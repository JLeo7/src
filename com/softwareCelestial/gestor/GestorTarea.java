package com.softwareCelestial.gestor;

import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.multis.MultiTarea;

public class GestorTarea {

    public GestorTarea() {
    }

    public void registrarTarea(String codigo,String descripcion,String tipo,String responsable,int idInstalacion){
        MultiTarea mTarea;
        Tarea nuevaTarea;
        nuevaTarea = new Tarea();
        mTarea = new MultiTarea();
        mTarea.registrarTarea(nuevaTarea,idInstalacion);
    }
}
