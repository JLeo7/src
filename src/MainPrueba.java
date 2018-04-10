package src;

import com.softwareCelestial.cl.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainPrueba {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Instalacion> instalaciones = new ArrayList<>();

    public static void main(String[] args) throws java.io.IOException {
        int option;
        boolean exit = false;

        do {
            showMenu();
            option = readOption();
            exit = execAction(option);
        } while (!exit);
    }

    static void showMenu(){
        out.println();
        out.println("Menu");
        out.println();
        out.println("1.  Registrar cliente.");
        out.println("2.  Registrar productos.");
        out.println("3.  Registrar instalaciones.");
        out.println("4.  Agregar tareas.");
        out.println("5.  Modificar estado tarea.");
        out.println("6.  Modificar estado instalaciones.");
        out.println("0.  Exit.");
        out.println();
    }

    static int readOption() throws java.io.IOException {
        int option;
        out.print("Select your option: ");
        option= Integer.parseInt(in.readLine());
        out.println();
        return option;
    }

    static boolean execAction(int poption)throws java.io.IOException{
        boolean exit = false;

        switch (poption) {
            case 0: // Exits the app.
                exit = true;
                break;
            case 1: //
                registrarCliente();
                break;
            case 2: //
                registrarProducto();
                break;
            case 3: //
//                registrarInstalaciones();
                break;
            case 4: //
//                agregarTareas();
                break;
            case 5: //
//                modificarEstadoTarea();
                break;
            case 6: //
//                modificarEstadoInstalacion();
                break;
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return exit;
    }

    static void registrarCliente()throws java.io.IOException{
        String razonSocial;
        String cedJuridica;
        String latidud;
        String longitud;
        String direccionExacta;
        Image logo;
        int cantTelefonos;
        ArrayList<String> telefonosCliente = new ArrayList<>();
        Contacto contactoLider;
        Contacto contactoTecnico;
        String id;
        String nombre;
        String apellido;
        String puesto;
        String correo;
        ArrayList<String> telefonosContacto = new ArrayList<>();

        // Info del cliente
        out.println("Razon social");
        razonSocial = in.readLine();
        out.println("CedJuridica");
        cedJuridica = in.readLine();
        out.println("Latitud");
        latidud = in.readLine();
        out.println("Longitud");
        longitud = in.readLine();
        out.println("direccion exacta");
        direccionExacta = in.readLine();
        logo = ImageIO.read(new File("resources/selectBox.png"));
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());
        Cliente clienteNuevo;

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosCliente.add(in.readLine());
        }

        // Info del primer contacto dentro del cliente
        out.println("Id");
        id = in.readLine();
        out.println("nombre");
        nombre = in.readLine();
        out.println("apellido");
        apellido = in.readLine();
        out.println("puesto");
        puesto = in.readLine();
        out.println("Digite el correo");
        correo = in.readLine();
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosContacto.add(in.readLine());
        }
        contactoLider = new Contacto(id,nombre,apellido,puesto,correo,telefonosContacto);

        // Info del segundo contacto dentro del cliente
        out.println("Id");
        id = in.readLine();
        out.println("nombre");
        nombre = in.readLine();
        out.println("apellido");
        apellido = in.readLine();
        out.println("puesto");
        puesto = in.readLine();
        out.println("Digite el correo");
        correo = in.readLine();
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosContacto.add(in.readLine());
        }
        contactoTecnico = new Contacto(id,nombre,apellido,puesto,correo,telefonosContacto);

        clienteNuevo = new Cliente(razonSocial,cedJuridica,latidud,longitud,direccionExacta,logo,telefonosCliente,contactoLider,contactoTecnico);
        clientes.add(clienteNuevo);

    }

    static void registrarProducto()throws java.io.IOException{
        String nombre;
        Image logo;
        String descripcionProd;
        String idProducto;
        Version versionActual;
        String numero;
        LocalDate fechaCreacion;
        ArrayList<Caracteristica> caracteristicas;
        String descripcionCar;
        LocalDate fechaReporte;
        int cantErrores;
        CaractError caracteristicaError;
        int cantMejoras;
        CaractMejora caracteristicaMejora;
        Producto productoNuevo;
        out.println("nombre producto");
        nombre = in.readLine();
        logo = ImageIO.read(new File("resources/crearSinImagen.png"));
        out.println("descripcion producto");
        descripcionProd = in.readLine();
        out.println("idProducto");
        idProducto = in.readLine();
        out.println("numero de la version");
        numero = in.readLine();
        fechaCreacion = LocalDate.now();
        caracteristicas = new ArrayList<>();
        out.println("Cuantos errores quiere registrar?");
        cantErrores = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantErrores;i++){
            out.println("descripcion error");
            descripcionCar = in.readLine();
            fechaReporte = LocalDate.now();
            caracteristicaError = new CaractError(descripcionCar,fechaReporte);
            caracteristicas.add(caracteristicaError);
        }

        out.println("Cuantas mejoras quiere registrar?");
        cantMejoras = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantMejoras;i++){
            out.println("descripcion mejora");
            descripcionCar = in.readLine();
            caracteristicaMejora = new CaractMejora(descripcionCar);
            caracteristicas.add(caracteristicaMejora);
        }

        versionActual = new Version(numero,fechaCreacion,caracteristicas);
        productoNuevo = new Producto(nombre,logo,descripcionProd,idProducto,versionActual);

        productos.add(productoNuevo);
    }
}
