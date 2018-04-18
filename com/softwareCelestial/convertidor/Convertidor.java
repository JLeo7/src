package com.softwareCelestial.convertidor;
import java.time.LocalDate;
import java.time.LocalTime;

public class Convertidor {

    public Convertidor() {
    }

    public String convertirFechaAString(LocalDate fecha){
        return fecha.getYear()+"-"+fecha.getMonthValue()+"-"+fecha.getDayOfMonth();
    }

    public LocalDate convertirStringAFecha(String stringFecha){
        String partes[];
        partes = stringFecha.split("-");
        return LocalDate.of(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Integer.parseInt(partes[3]));
    }

    public String convertirHoraAString (LocalTime hora){
        return hora.getHour()+":"+hora.getMinute()+":"+hora.getSecond();
    }

    public LocalTime convertirStringAHora (String stringHora) {
        String partes[];
        partes = stringHora.split(":");
        return LocalTime.of(Integer.parseInt(partes[1]),Integer.parseInt(partes[2]),Integer.parseInt(partes[3]));
    }
}
