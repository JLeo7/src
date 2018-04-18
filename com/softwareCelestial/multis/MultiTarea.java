package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Tarea;

import java.sql.ResultSet;
import java.util.ArrayList;
public class MultiTarea {

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

    public void asignarTareaAUnaInstalacion(int idInstalacion, String codigoTarea) {
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_asignar_tarea_a_instalacion("+idInstalacion+",'"+obtenerIdTareaPorCodigo(codigoTarea)+"')");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

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

    public void modificarEstadoTarea(String estadoModificado){
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_modificar_estado_tarea('"+estadoModificado+"')");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
}
