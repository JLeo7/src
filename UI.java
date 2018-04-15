import com.softwareCelestial.*; //Quitar esto cuando se importe la libreria.
import com.softwareCelestial.cl.*;
import com.softwareCelestial.gestor.GestorCliente;
import com.softwareCelestial.gestor.GestorProducto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class UI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static GestorCliente gestorCliente = new GestorCliente();
    static GestorProducto gestorProducto = new GestorProducto();

    public static void main(String[] args) throws java.io.IOException {
        int option;
        boolean mainExit = false;

        do {
            showMainMenu();
            option = readOption();
            mainExit = execMainAction(option);
        } while (!mainExit);
    }

    static void showMainMenu() {
        out.println();
        out.println("Menu");
        out.println();
        out.println("1.  Clientes");
        out.println("2.  Productos");
        out.println("3.  Instalaciones");
        out.println("0.  Exit.");
        out.println();
    }

    static void showOpt1Menu() {
        out.println();
        out.println("Clientes");
        out.println();
        out.println("1.  Registrar cliente.");
        out.println("2.  Modificar cliente.");
        out.println("3.  Listar clientes.");
        out.println("0.  Regresar");
        out.println();
    }

    static void showOpt2Menu() {
        out.println();
        out.println("Productos");
        out.println();
        out.println("1.  Registrar producto.");
        out.println("2.  Modificar producto.");
        out.println("3.  Listar productos.");
        out.println("4.  Eliminar producto.");
        out.println("0.  Regresar.");
        out.println();
    }

    static void showOpt3Menu() {
        out.println();
        out.println("Option 3");
        out.println();
        out.println("1.  .");
        out.println("2.  .");
        out.println("3.  .");
        out.println("4.  .");
        out.println("0.  Go back.");
        out.println();
    }

    static int readOption() throws java.io.IOException {
        int option;
        out.print("Select your option: ");
        option = Integer.parseInt(in.readLine());
        out.println();
        return option;
    }

    static boolean execMainAction(int poption) throws java.io.IOException {
        boolean mainExit = false;
        boolean previous = false;
        int subMenuOption;

        switch (poption) {
            case 0: // Exits the app.
                mainExit = true;
                break;
            case 1: // Shows the menu for the first option
                do {
                    showOpt1Menu();
                    subMenuOption = readOption();
                    previous = exeOpt1Action(subMenuOption);
                } while (!previous);
                break;
            case 2: // Shows the menu for the second option
                do {
                    showOpt2Menu();
                    subMenuOption = readOption();
                    previous = exeOpt2Action(subMenuOption);
                } while (!previous);
                break;
            case 3: // Shows the menu for the third option
                do {
                    showOpt3Menu();
                    subMenuOption = readOption();
                    previous = exeOpt3Action(subMenuOption);
                } while (!previous);
                break;
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return mainExit;
    }

    static boolean exeOpt1Action(int poption) throws java.io.IOException{
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
                registrarCliente();
                break;
            case 2: //
                break;
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return previous;
    }

    static boolean exeOpt2Action(int poption) throws java.io.IOException{
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
                registrarProducto();
                break;
            case 2: //
                break;
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return previous;
    }

    static boolean exeOpt3Action(int poption) {
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
                registrarInstalacion();
                break;
            case 2: //
//                modificarInstalacion();
                break;
            case 3: //
//                listarInstalaciones();
                break;
            case 4: //
//                agregarTareaAInstalaciones();
                break;
            case 5: //
//                modificarEstadoTareas();
                break;
            case 6: //
//                modificarEstadoInstalaciones();
                break;
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return previous;
    }

    static void registrarInstalacion(){
        try {
           String estado;
        } catch (Exception e){
            out.println(e.getMessage());
        }
    }

    static void registrarCliente()throws java.io.IOException{
        String nombre;
        String razonSocial;
        String cedJuridica;
        String latidud;
        String longitud;
        String direccionExacta;
        String logo;
        int cantTelefonos;
        ArrayList<String> telefonosCliente = new ArrayList<>();
        String idContacto1;
        String nombreContacto1;
        String apellidoContacto1;
        String puestoContacto1;
        String correoContacto1;
        ArrayList<String> telefonosContacto1 = new ArrayList<>();
        String idContacto2;
        String nombreContacto2;
        String apellidoContacto2;
        String puestoContacto2;
        String correoContacto2;
        ArrayList<String> telefonosContacto2 = new ArrayList<>();

        // Info del cliente
        out.println("ingrese el nombre del cliente");
        nombre = in.readLine();
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
        logo = "resources/foto1.jpg";
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosCliente.add(in.readLine());
        }

        // Info del primer contacto dentro del cliente
        out.println("Id");
        idContacto1 = in.readLine();
        out.println("nombre");
        nombreContacto1 = in.readLine();
        out.println("apellido");
        apellidoContacto1 = in.readLine();
        out.println("puesto");
        puestoContacto1 = in.readLine();
        out.println("Digite el correo");
        correoContacto1= in.readLine();
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosContacto1.add(in.readLine());
        }

        // Info del segundo contacto dentro del cliente
        out.println("Id");
        idContacto2 = in.readLine();
        out.println("nombre");
        nombreContacto2 = in.readLine();
        out.println("apellido");
        apellidoContacto2 = in.readLine();
        out.println("puesto");
        puestoContacto2 = in.readLine();
        out.println("Digite el correo");
        correoContacto2 = in.readLine();
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Telefono "+i);
            telefonosContacto2.add(in.readLine());
        }

        gestorCliente.crearCliente(nombre, razonSocial, cedJuridica, latidud, longitud, direccionExacta, logo, telefonosCliente, idContacto1, nombreContacto1, apellidoContacto1, puestoContacto1, correoContacto1, telefonosContacto1, idContacto2, nombreContacto2, apellidoContacto2, puestoContacto2, correoContacto2, telefonosContacto2);

    }

    static void registrarProducto()throws java.io.IOException{
        String nombre;
        String logo;
        String descripcion;
        String idProducto;
        String version;

        out.println("Ingrese el nombre del producto");
        nombre = in.readLine();
        out.println("Ingrese el url del logo");
        logo = in.readLine();
        out.println("Ingrese la descripción del producto");
        descripcion = in.readLine();
        out.println("Ingrese el código de identificación del producto");
        idProducto = in.readLine();
        out.println("Ingrese el número de versión del producto");
        version = in.readLine();

        gestorProducto.crearProducto(nombre, logo, descripcion, idProducto, version);
        out.println("Se ha registrado el producto");

    }
}