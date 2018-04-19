package com.softwareCelestial.gestor;
import com.softwareCelestial.cl.Instalacion;
import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiInstalacion;
import com.softwareCelestial.multis.MultiProducto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorInstalacion {

    static MultiInstalacion mInstalacion = new MultiInstalacion();
    static MultiCliente mCliente  = new MultiCliente();
    static MultiProducto mProducto = new MultiProducto();
    /**
     * Metodo que recibe la infrormacion necesaria para crear una instalcion y la envia al multi para ingreso en la base de datos.
     * @param cedJuridica
     * @param idProducto
     * @author Leonardo Mora
     */
    public void registrarInstalacion(String cedJuridica,int idProducto){
        LocalDate fechaCreacion;
        LocalTime horaCreacion;
        Instalacion nuevaInstalacion;
        fechaCreacion = LocalDate.now();
        horaCreacion = LocalTime.now();

        nuevaInstalacion = new Instalacion(fechaCreacion,horaCreacion,"pendiente",null,mCliente.listarCliente(mCliente.obtenerIdCliente(cedJuridica)),mProducto.obtenerProductoPorId(idProducto),mProducto.obtenerProductoPorId(idProducto).getVersionActual());
        System.out.println(nuevaInstalacion.toString());
        mInstalacion.registrarInstalacion(nuevaInstalacion);
    }

    public void modificarEstadoInstalacion(int idInstalacion){
        mInstalacion.modificarEstadoInstalacion(idInstalacion,"ejecutada");
    }

    public ArrayList<String> listarInstalaciones(){
        ArrayList<String> lista;
        lista = new ArrayList<>();
        for (Instalacion inst:mInstalacion.listarInstalaciones()){
            lista.add(inst.toString());
        }
        return lista;
    }
}
