package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Tarea;

import java.sql.ResultSet;
import java.util.ArrayList;
public class MultiTarea {

    /**
     * Metodo que registra una nueva tarea en la base de datos.
     * @param nuevaTarea
     * @param idInstalacion
     * @author Leonardo Mora
     */
    public void registrarTarea(Tarea nuevaTarea,int idInstalacion){
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_registrar_tarea('"+nuevaTarea.getCodigo()+"','"+nuevaTarea.getDescripcion()+"','"+nuevaTarea.getEstado()+"','"+nuevaTarea.getTipo()+"','"+nuevaTarea.getResponsable()+"')");
            asignarTareaAUnaInstalacion(idInstalacion,nuevaTarea.getCodigo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que asigna una tarea registrada a una instalacion registrada.
     * @param idInstalacion
     * @param codigoTarea
     * @author Leonardo Mora
     */
    public void asignarTareaAUnaInstalacion(int idInstalacion, String codigoTarea) {
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_asignar_tarea_a_instalacion("+idInstalacion+",'"+obtenerIdTareaPorCodigo(codigoTarea)+"')");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que obtiene las tareas asignadas a una instalacion.
     * @param idInstalacion
     * @return Una coleccion de tareas.
     * @author Leonardo Mora
     */
    public ArrayList<Tarea> obtenerTareasPorIdInstalacion(int idInstalacion){
        try {
            AccesoBD accesoBD;
            ResultSet rs;
            ArrayList<Tarea> lista;
            Tarea nuevaTarea;
            accesoBD = Conector.getConector();
            rs = accesoBD.ejecutarSQL("CALL pa_obtener_tareas_por_id_instalacion("+idInstalacion+")",true);
            lista = new ArrayList<>();

            while (rs.next()) {
                nuevaTarea = new Tarea(rs.getString("codigo"),rs.getString("descripcion"),rs.getString("estado"),rs.getString("tipo"),rs.getString("responsable"));
                lista.add(nuevaTarea);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Metodo que obtiene el id que tiene una tarea en la base de datos dado el codigo de la tarea.
     * @param codigo
     * @return id de la tarea en al base de datos.
     * @author Leonardo Mora
     */
    public int obtenerIdTareaPorCodigo(String codigo){
        try {
            AccesoBD aBD;
            ResultSet rs;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_id_tarea_por_codigo('"+codigo+"')",true);

            if (rs.next()) {
                return rs.getInt("id_tarea");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Metodo que modifica el estado de una tarea
     * @param codigoTarea
     * @param estadoModificado
     * @author Leonardo Mora
     */
    public void modificarEstadoTarea(String codigoTarea,String estadoModificado){
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_modificar_estado_tarea('"+codigoTarea+"','"+estadoModificado+"')");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que valida si hay tareas pendientes.
     * @param idInstalacion
     * @return un boolean con el resultado de la busqueda.
     * @author Leonardo Mora
     */
    public boolean validarTareasPendientes (int idInstalacion) {
        try {
            AccesoBD aBD;
            ResultSet rs;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_tareas_pendientes("+idInstalacion+")",true);
            if(rs.next()){
                return true;
            } else {
                return false;
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    public boolean validarTareasExistentes (int idInstalacion) {
        try {
            AccesoBD aBD;
            ResultSet rs;
            boolean result;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_tareas_por_id_instalacion()",true);
            if (rs.next()){
                result = true;
            } else {
                result = false;
            }
            return result;
        } catch (Exception e) {
            return false;
        }
    }
}
