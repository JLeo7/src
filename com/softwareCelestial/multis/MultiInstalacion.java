package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;

import java.sql.Array;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import com.softwareCelestial.convertidor.Convertidor;
import com.softwareCelestial.cl.*;
import com.softwareCelestial.multis.MultiProducto;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiVersion;
import com.softwareCelestial.multis.MultiTarea;
import javafx.beans.binding.IntegerExpression;

public class MultiInstalacion {

    /**
     * Metodo que recibe una instancia de instalacion y annade una nueva instalacion en la base de datos.
     * @param nuevaInstalacion
     * @author Leonardo Mora
     */
    public void registrarInstalacion(Instalacion nuevaInstalacion){
        try{
            AccesoBD aBD;
            MultiProducto mProducto;
            MultiCliente mCliente;
            MultiVersion mVersion;
            Convertidor convertidorActual;
            String fecha;
            String hora;
            String estado;
            int idProducto;
            int idVersion;
            int idCliente;
            mProducto = new MultiProducto();
            mVersion = new MultiVersion();
            mCliente = new MultiCliente();
            convertidorActual = new Convertidor();
            fecha = convertidorActual.convertirFechaAString(nuevaInstalacion.getFecha());
            hora = convertidorActual.convertirHoraAString(nuevaInstalacion.getHora());
            estado = nuevaInstalacion.getEstado();
            idProducto = mProducto.obtenerIdProductoPorCodigo(nuevaInstalacion.getProductoInstalado().getIdProducto());
            idCliente = mCliente.obtenerIdCliente(nuevaInstalacion.getSolicitante().getCedJuridica());
            idVersion = mVersion.obtenerIdVersionPorNumero(nuevaInstalacion.getProductoInstalado().getVersionActual().getNumero());
            aBD = Conector.getConector();
            aBD.ejecutarSQL("insert into instalacion (fecha,hora,estado,id_producto,id_cliente,id_version) values ('"+fecha+"','"+hora+"','"+estado+"',"+idProducto+","+idCliente+","+idVersion+")");
        } catch (Exception e){
        }
    }

    /**
     * Metodo que retorna la lista de todas las instalaciones registradas en la base de datos.
     * @return el metodo retorna una lista de instalaciones.
     */
    public ArrayList<Instalacion> listarInstalaciones(){
        try{
            ArrayList<Instalacion> instalaciones;
            ArrayList<Tarea> tareas;
            LocalDate fecha = null;
            LocalTime hora = null;
            String estado = "";
            int[] idInstalaciones;
            int indiceIds;
            Producto productoInstalado;
            Cliente solicitante;
            Version versionInstalada;
            ResultSet rs;
            AccesoBD aBD;
            MultiTarea mTarea;
            MultiCliente mCliente;
            MultiProducto mProducto;
            MultiVersion mVersion;
            Convertidor convActual;
            idInstalaciones = new int [obtenerCantInstalaciones()];
            indiceIds = 0;
            convActual = new Convertidor();
            instalaciones = new ArrayList<>();
            aBD = Conector.getConector();
            mTarea = new MultiTarea();
            mProducto = new MultiProducto();
            mCliente = new MultiCliente();
            mVersion = new MultiVersion();
            rs = aBD.ejecutarSQL("CALL pa_obtener_id_instalaciones()",true);

            while (rs.next()){
                idInstalaciones[indiceIds] = rs.getInt("id_instalacion");
                indiceIds++;
            }
            rs.close();

            for (int i = 0; i < idInstalaciones.length; i++){
                rs = aBD.ejecutarSQL("CALL pa_obtener_info_instalacion_por_id",true);

                if (rs.next()){
                    fecha = convActual.convertirStringAFecha(rs.getString("fecha"));
                    hora = convActual.convertirStringAHora(rs.getString("hora"));
                    estado = rs.getString("estado");
                }
                System.out.println("f "+fecha+" h "+hora+" e "+estado);
                rs.close();

                if (mTarea.validarTareasExistentes(idInstalaciones[i])){
                    tareas = mTarea.obtenerTareasPorIdInstalacion(idInstalaciones[i]);
                } else {
                    tareas = null;
                }
                System.out.println(tareas+" / "+tareas.get(1).toString());
                productoInstalado = mProducto.obtenerProductoPorIdInstalacion(idInstalaciones[i]);
                System.out.println(productoInstalado.toString());
                solicitante = mCliente.obtenerClientePorIdInstalacion(idInstalaciones[i]);
                System.out.println(solicitante.toString());
                versionInstalada = mVersion.obtenerVersionPorIdInstalacion(idInstalaciones[i]);
                System.out.println(versionInstalada.toString());
                instalaciones.add(new Instalacion(fecha,hora,estado,tareas,solicitante,productoInstalado,versionInstalada));
            }
            return instalaciones;
        } catch (Exception e){

            return null;
        }
    }

    public int obtenerCantInstalaciones() {
        try {
            AccesoBD aBD;
            ResultSet rs;
            int cantInst = -1;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_contar_instalaciones()",true);

            if (rs.next()) {
                cantInst = rs.getInt("COUNT(id_instalacion)");
            }
            return cantInst;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Metodo que modifica el estado de una instalacion
     * @param idInstalacion
     * @param nuevoEstado
     * @return Retorna un string con el mensaje de exito o error.
     */
    public void modificarEstadoInstalacion (int idInstalacion,String nuevoEstado) {
        try {
            AccesoBD aBD;
            aBD = Conector.getConector();
            aBD.ejecutarSQL("CALL pa_modificar_estado_instalacion("+idInstalacion+",'"+nuevoEstado+"')");
        } catch (Exception e){
        }
    }
}
