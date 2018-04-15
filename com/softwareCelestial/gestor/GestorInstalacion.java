package com.softwareCelestial.gestor;
import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.multis.MultiInstalacion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorInstalacion {

    public void registrarInstalacion(String cedJuridica,int idProducto){
        LocalDate fechaCreacion;
        LocalTime horaCreacion;
        MultiInstalacion mInstalacion;
        fechaCreacion = LocalDate.now();
        horaCreacion = LocalTime.now();
//        obtenerClientePorCedJuridica(cedJuridica);
//        obtenerProductoPorId(idProducto);
        mInstalacion = new MultiInstalacion();

    }
    public void agregarTareasAInstalacion(ArrayList<Tarea> tareas){

    }
}
