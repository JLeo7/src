package com.softwareCelestial.multis;
import CapaAccesoBD.AccesoBD;
import CapaAccesoBD.Conector;
import com.softwareCelestial.cl.Tarea;
import com.softwareCelestial.cl.Version;
import com.softwareCelestial.convertidor.Convertidor;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class MultiVersion {

    public MultiVersion(){

    }

    public void registrarVersionInicial(String numeroVersion, LocalDate fechaCreacion){
        String fecha = fechaCreacion.getYear()+"-"+fechaCreacion.getMonthValue()+"-"+fechaCreacion.getDayOfMonth();
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("INSERT INTO tversion(numero, fecha_creacion) VALUES ('"+numeroVersion+"', '"+fecha+"')");
        } catch(Exception e) {
                    }
    }

    public void modificarVersion(String numeroVersion, String idProducto, LocalDate fechaCreacion){

        String fecha = fechaCreacion.getYear()+"-"+fechaCreacion.getMonthValue()+"-"+fechaCreacion.getDayOfMonth();
        try{
            AccesoBD BD = Conector.getConector();
            BD.ejecutarSQL("UPDATE tversion AS v INNER JOIN producto AS p ON p.id_version = v.id_version SET v.numero = '"+numeroVersion+"', v.fecha_creacion = '"+fecha+"' WHERE (p.id_producto = '"+idProducto+"') AND (p.id_version = v.id_version)");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Version obtenerVersionPorIdInstalacion(int idInstalacion) {
        try {
            AccesoBD aBD;
            Version versionEncontrada;
            ResultSet rs;
            Convertidor convActual;
            convActual = new Convertidor();
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_version_por_id_instalacion("+idInstalacion+")",true);

            if (rs.next()) {
                return versionEncontrada = new Version(rs.getString("numero"),convActual.convertirStringAFecha(rs.getString("fecha_creacion")));
            } else {
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    public Version obtenerVersionPorId(int idVersion) {
        try {
            AccesoBD aBD;
            Version versionEncontrada;
            ResultSet rs;
            ArrayList<Tarea> tareas;
            Convertidor convActual;
            convActual = new Convertidor();
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("CALL pa_obtener_version_por_id("+idVersion+")",true);

            while (rs.next()) {
                return versionEncontrada = new Version(rs.getString("numero"),convActual.convertirStringAFecha(rs.getString("fecha_creacion")));
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }

    public int obtenerIdVersionPorNumero(String numeroVersion){
        try{
            AccesoBD aBD;
            int idVersion = -1;
            ResultSet rs;
            aBD = Conector.getConector();
            rs = aBD.ejecutarSQL("select tv.id_version from tversion as tv\n" +
                    "where tv.numero = '"+numeroVersion+"'",true);
            if (rs.next()){
                idVersion = rs.getInt("id_version");
            }
            return idVersion;
        } catch (Exception e) {
            return -1;
        }
    }
}
