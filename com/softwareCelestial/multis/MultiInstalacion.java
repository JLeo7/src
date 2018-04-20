package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.softwareCelestial.convertidor.Convertidor;
import com.softwareCelestial.cl.*;
import com.softwareCelestial.multis.MultiProducto;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiVersion;
import com.softwareCelestial.multis.MultiTarea;
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
            ArrayList<Tarea> tareas;
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
            idCliente = mCliente.obtenerIdClientePorCedula(nuevaInstalacion.getSolicitante().getCedJuridica());
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
            Producto productoInstalado;
            Cliente solicitante;
            Version versionInstalada;
            ResultSet rs;
            AccesoBD aBD;
            MultiTarea mTarea;
            MultiCliente mCliente;
            MultiProducto mProducto;
            MultiVersion mVersion;
            Instalacion instalacionObtenida;
            Convertidor convActual;
            convActual = new Convertidor();
            instalaciones = new ArrayList<>();
            aBD = Conector.getConector();
            mTarea = new MultiTarea();
            mProducto = new MultiProducto();
            mCliente = new MultiCliente();
            mVersion = new MultiVersion();
            rs = aBD.ejecutarSQL("CALL pa_obtener_instalaciones()",true);

            while (rs.next()) {
                tareas = mTarea.obtenerTareasPorIdInstalacion(rs.getInt("id_instalacion"));
                productoInstalado = mProducto.obtenerProductoPorIdInstalacion(rs.getInt("id_instalacion"));
                solicitante = mCliente.obtenerClientePorIdInstalacion(rs.getInt("id_instalacion"));
                instalacionObtenida = new Instalacion(convActual.convertirStringAFecha(rs.getString("fecha")),convActual.convertirStringAHora(rs.getString("hora")),rs.getString("estado"),tareas,mCliente.obtenerClientePorIdInstalacion(rs.getInt("id_instalacion")),productoInstalado,mVersion.obtenerVersionPorId(rs.getInt("id_version")));
                instalaciones.add(instalacionObtenida);
            }
            return instalaciones;
        } catch (Exception e){

            return null;
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
