package com.softwareCelestial.gestor;
import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.multis.MultiTarea;

public class GestorTarea {
    static MultiTarea mTarea = new MultiTarea();

    public GestorTarea() {
    }

    /**
     * Metodo que recibe la informacion necesaria para inicializar un objeto de tipo tarea y lo envia al multi para ingreso en la base de datos.
     * @param codigo
     * @param descripcion
     * @param tipo
     * @param responsable
     * @param idInstalacion
     * @author Leonardo Mora
     */
    public void registrarTarea(String codigo,String descripcion,String tipo,String responsable,int idInstalacion){
        MultiTarea mTarea;
        Tarea nuevaTarea;
        mTarea = new MultiTarea();
        nuevaTarea = new Tarea(codigo,descripcion,"pendiente",tipo,responsable);
        mTarea.registrarTarea(nuevaTarea,idInstalacion);
    }

    /**
     * Metodo que modifica el estado de una tarea.
     * @param codigoTarea
     * @param nuevoEstado
     * @author Leonardo Mora
     */
    public void modificarEstadoTarea(String codigoTarea,String nuevoEstado){
        MultiTarea mTarea;
        mTarea = new MultiTarea();
        mTarea.modificarEstadoTarea(codigoTarea,nuevoEstado);
    }

    /**
     * Metodo que evalua las tareas para verificar si todas han sido completadas
     * @param idInstalacion
     * @return el resultado de la evaluacion
     * @author Leonardo Mora
     */
    public boolean validarTareasPendientes(int idInstalacion){
        return mTarea.validarTareasPendientes(idInstalacion);
    }
}
