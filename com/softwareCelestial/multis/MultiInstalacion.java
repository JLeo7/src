package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.softwareCelestial.cl.*;
import com.softwareCelestial.multis.MultiProducto;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiVersion;
import com.softwareCelestial.multis.MultiTarea;
public class MultiInstalacion {

    public void registrarInstalacion(Instalacion nuevaInstalacion){
        try{
            AccesoBD accesoBD;
            String fecha;
            String hora;
            MultiCliente mCliente;
            fecha = nuevaInstalacion.getFecha().getYear()+"-"+nuevaInstalacion.getFecha().getMonthValue()+"-"+nuevaInstalacion.getFecha().getDayOfMonth();
            hora = nuevaInstalacion.getHora().getHour()+":"+nuevaInstalacion.getHora().getMinute()+":"+nuevaInstalacion.getHora().getSecond();
            accesoBD = Conector.getConector();
            mCliente = new MultiCliente();
//            accesoBD.ejecutarSQL("insert into instalacion (fecha,hora,estado,id_producto,id_cliente,id_version) values ('"+fecha+"'," +
//                    "'"+hora+"','"+nuevaInstalacion.getEstado()+"',"+nuevaInstalacion.getProductoInstalado().getIdProducto()+"," +
//                    ""+mCliente.obtenerIdPorCedJuridica()+","+nuevaInstalacion.getProductoInstalado().getVersionActual().getNumero()+")");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//    public ArrayList<Instalacion> listarInstalaciones(){
//        try{
//            ArrayList<Instalacion> instalaciones;
//            ArrayList<Tarea> tareas;
//            Producto productoInstalado;
//            Cliente solicitante;
//            Version versionInstalada;
//            ResultSet rs;
//            AccesoBD accesoBD;
//            MultiTarea mTarea;
//            MultiCliente mCliente;
//            MultiProducto mProducto;
//            MultiVersion mVersion;
//            Instalacion instalacionObtenida;
//            instalaciones = new ArrayList<>();
//            accesoBD = Conector.getConector();
//            mTarea = new MultiTarea();
//            mProducto = new MultiProducto();
//            mCliente = new MultiCliente();
//            mVersion = new MultiVersion();
//            rs = accesoBD.ejecutarSQL("CALL pa_obtener_instalaciones()",true);
//
//            while (rs.next()) {
//                tareas = mTarea.obtenerTareasPorIdInstalacion(rs.getInt("id_instalacion"));
//                productoInstalado = mProducto.obtenerProductoPorIdInstalacion(rs.getInt("id_instalacion"));
//                solicitante = mCliente.obtenerClientePorIdInstalacion(rs.getInt("id_instalacion"));
//                versionInstalada = mVersion.obtenerVersionPorIdInstalacion(rs.getInt("id_instalacion"));
//                instalacionObtenida = new Instalacion(rs.getInt("id_instalacion"),convertirStringAFecha(rs.getString("fecha")),convertirStringAHora(rs.getString("hora")),rs.getString("estado"),tareas,solicitante,productoInstalado,versionInstalada);
//                instalaciones.add(instalacionObtenida);
//            }
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

    public LocalDate convertirStringAFecha(String stringFecha){
        String partes[];
        partes = stringFecha.split("-");
        return LocalDate.of(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Integer.parseInt(partes[3]));
    }

    public LocalTime convertirStringAHora (String stringHora) {
        String partes[];
        partes = stringHora.split(":");
        return LocalTime.of(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Integer.parseInt(partes[3]));
    }
}
