package com.softwareCelestial.gestor;
import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiInstalacion;
import com.softwareCelestial.multis.MultiProducto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorInstalacion {

    public void registrarInstalacion(String cedJuridica,int idProducto){
        LocalDate fechaCreacion;
        LocalTime horaCreacion;
        MultiInstalacion mInstalacion;
        MultiCliente mCliente;
        MultiProducto mProducto;
        mCliente = new MultiCliente();
        mProducto = new MultiProducto();
        fechaCreacion = LocalDate.now();
        horaCreacion = LocalTime.now();
        mProducto.obtenerProductoPorId(idProducto);
        mCliente.obtenerIdCliente(cedJuridica);
        mInstalacion = new MultiInstalacion();

    }
    public void agregarTareasAInstalacion(ArrayList<Tarea> tareas){

    }
}
