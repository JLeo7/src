package src.com.softwareCelestial.cl;
import java.time.LocalDate;

public class CaractError extends Caracteristica {
    private LocalDate fechaReporte;

    public CaractError() {
        super();
    }

    public CaractError(String descripcion, LocalDate fechaReporte) {
        super(descripcion);
        this.fechaReporte = fechaReporte;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    @Override
    public String toString() {
        return "CaractError{" +
                "fechaReporte=" + fechaReporte +
                "} " + super.toString();
    }
}
