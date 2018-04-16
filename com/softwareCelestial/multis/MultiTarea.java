package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Tarea;

import java.sql.ResultSet;
import java.util.ArrayList;
public class MultiTarea {

    public void registrarTarea(Tarea nuevaTarea){

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
}
