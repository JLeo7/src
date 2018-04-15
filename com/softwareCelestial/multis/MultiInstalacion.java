package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import com.softwareCelestial.cl.Instalacion;
import com.softwareCelestial.multis.MultiProducto;
import com.softwareCelestial.multis.MultiCliente;
import com.softwareCelestial.multis.MultiVersion;
public class MultiInstalacion {

    public void registrarInstalacion(Instalacion nuevaInstalacion){
        try{
            AccesoBD accesoBD;
            ResultSet rs;
            int idProducto = 0;
            int idCliente = 0;
            int idVersion = 0;
            String fecha;
            String hora;
            fecha = nuevaInstalacion.getFecha().getYear()+"-"+nuevaInstalacion.getFecha().getMonthValue()+"-"+nuevaInstalacion.getFecha().getDayOfMonth();
            accesoBD = Conector.getConector();
            hora = nuevaInstalacion.getHora().getHour()+":"+nuevaInstalacion.getHora().getMinute()+":"+nuevaInstalacion.getHora().getSecond();
            rs = accesoBD.ejecutarSQL("select id_producto from producto where ",true);
//            accesoBD.ejecutarSQL("insert into instalacion (fecha,hora,estado,id_producto,id_cliente,id_version) values ('"+fecha+"'," +
//                    "'"+hora+"','"+nuevaInstalacion.getEstado()+"',"+nuevaInstalacion.getProductoInstalado().getIdProducto()+"," +
//                    ""+obtenerIdPorCedJuridica()+","+idVersion+")");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
