import com.softwareCelestial.gestor.*;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class UI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static GestorCliente gestorCliente = new GestorCliente();
    static GestorProducto gestorProducto = new GestorProducto();
    static GestorVersion gestorVersion = new GestorVersion();
    static GestorInstalacion gInstalacion = new GestorInstalacion();


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
        out.println("Instalaciones");
        out.println();
        out.println("1.  Realizar una instalacion.");
        out.println("2.  Modificar el estado de una instalacion.");
        out.println("3.  Listar las instalaciones.");
        out.println("4.  Agregar tareas a una instalacion.");
        out.println("5.  Modificar el estado de una tarea.");
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
                modificarCliente();
                break;
            case 3:
                listarClientes();
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
                modificarProducto();
                break;
            case 3:
                listarProductos();
                break;
            case 4:
                eliminarProducto();
            default: // Any value that isn't in the options
                out.println("Invalid option.");
                out.println();
                break;
        }
        return previous;
    }

    static boolean exeOpt3Action(int poption) throws java.io.IOException{
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
                registrarInstalacion();
                break;
            case 2: //
//                modificarEstadoInstalacion();
                break;
            case 3: //
//                listarInstalaciones();
                break;
            case 4: //
                agregarTareasAInstalacion();
                break;
            case 5: //
//                modificarEstadoTarea();
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
            String cedJuridica;
            int idProducto;
            out.print("Digite la cedula juridica del cliente que solicita la instalacion: ");
            cedJuridica = in.readLine();
            out.print("Digite el id del producto que desea instalar: ");
            idProducto = Integer.parseInt(in.readLine());
            gInstalacion.registrarInstalacion(cedJuridica,idProducto);
        } catch (Exception e){
            out.println(e.getMessage());
        }
    }

    static void agregarTareasAInstalacion()throws java.io.IOException{
        String codigo;
        String descripcion;
        String tipo;
        String responsable;

        out.print("Digite el codigo de la tarea: ");
        codigo = in.readLine();
        out.print("Digite la descripcion de la tarea: ");
        descripcion = in.readLine();
        out.print("Digite el tipo de tarea: ");
        tipo = in.readLine();
        out.print("Digite el responsable de la tarea: ");
        responsable = in.readLine();

        GestorTarea gTarea;
        gTarea = new GestorTarea();
        gTarea.registrarTarea(codigo,descripcion,tipo,responsable);
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
        boolean continuar;
        boolean validacionEmail;
        boolean validacionEmail2;
        boolean validacionContacto;
        // Info del cliente
        out.println("Ingrese el nombre del cliente");
        nombre = in.readLine();
        out.println("Ingrese la Razon social");
        razonSocial = in.readLine();
        do{
            out.println("Ingrese la CedJuridica");
            cedJuridica = in.readLine();
            continuar = gestorCliente.validarCliente(cedJuridica);
            if(!continuar){
                out.println("La cedula ya se encuentra registrada, por favor intente de nuevo");
            }
        }while(!continuar);
        out.println("Ingrese la latitud");
        latidud = in.readLine();
        out.println("Ingrese la longitud");
        longitud = in.readLine();
        out.println("Ingrese la direccion exacta");
        direccionExacta = in.readLine();
        logo = "resources/foto1.jpg";
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Ingrese el telefono "+i);
            telefonosCliente.add(in.readLine());
        }

        // Info del primer contacto dentro del cliente

        do{
            out.println("Ingrese la identifiación del contacto #1");
            idContacto1 = in.readLine();
            validacionContacto = gestorCliente.validarContacto(idContacto1);
            if(!validacionContacto){
                out.println("El contacto ya se encuentra registrado, intente de nuevo");
            }

        } while(!validacionContacto);
        out.println("Ingrese el nombre del contacto #1");
        nombreContacto1 = in.readLine();
        out.println("Ingrese el apellido del contacto #1");
        apellidoContacto1 = in.readLine();
        out.println("Ingrese el puesto del contacto #1");
        puestoContacto1 = in.readLine();
        do{
            out.println("Digite el correo del contacto #1");
            correoContacto1= in.readLine();
            validacionEmail = EmailValidator.getInstance().isValid(correoContacto1);
            if(!validacionEmail){
                out.println("El correo es inválido, por favor ingrese un correo en el formato correcto");
            }
        } while(!validacionEmail);
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Ingrese el telefono "+i);
            telefonosContacto1.add(in.readLine());
        }

        // Info del segundo contacto dentro del cliente
        do{
            out.println("Ingrese la identifiación del contacto #2");
            idContacto2 = in.readLine();
            validacionContacto = gestorCliente.validarContacto(idContacto2);
            if(!validacionContacto){
                out.println("El contacto ya se encuentra registrado, intente de nuevo");
            }

        } while(!validacionContacto);
        out.println("Ingrese el nombre del contacto #2");
        nombreContacto2 = in.readLine();
        out.println("Ingrese el apellido del contacto #2");
        apellidoContacto2 = in.readLine();
        out.println("Ingrese el puesto del contacto #2");
        puestoContacto2 = in.readLine();
        do{
            out.println("Digite el correo del contacto #2");
            correoContacto2= in.readLine();
            validacionEmail2 = EmailValidator.getInstance().isValid(correoContacto2);
            if(!validacionEmail2){
                out.println("El correo es inválido, por favor ingrese un correo en el formato correcto");
            }
        } while(!validacionEmail2);
        out.println("Cuantos telefonos quiere registrar?");
        cantTelefonos = Integer.parseInt(in.readLine());

        for (int i = 0; i<cantTelefonos;i++){
            out.println("Ingrese el telefono "+i);
            telefonosContacto2.add(in.readLine());
        }

        gestorCliente.crearCliente(nombre, razonSocial, cedJuridica, latidud, longitud, direccionExacta, logo, telefonosCliente, idContacto1, nombreContacto1, apellidoContacto1, puestoContacto1, correoContacto1, telefonosContacto1, idContacto2, nombreContacto2, apellidoContacto2, puestoContacto2, correoContacto2, telefonosContacto2);

    }

    static void modificarCliente() throws  java.io.IOException{
        String idCliente = "";
        out.println("Ingrese la cedula juridica del cliente a modificar");
        idCliente = in.readLine();
        out.println(gestorCliente.imprimirCliente(idCliente));
        out.println();
        String nombre;
        String razonSocial;
        String latitud;
        String longitud;
        String direccionExacta;
        String logo;
        int cantTelefonos;
        int opcionEdicion;
        ArrayList<String> telefonosCliente = new ArrayList<>();
        out.println("Que información desea editar? Digite 1 para información general, 2 para telefonos");
        opcionEdicion = Integer.parseInt(in.readLine());
        switch(opcionEdicion){
            case 1:
                out.println("Ingrese el nombre");
                nombre = in.readLine();
                out.println("Ingrese la razon social");
                razonSocial = in.readLine();
                out.println("Ingrese la latitud");
                latitud = in.readLine();
                out.println("Ingrese la longitud");
                longitud = in.readLine();
                out.println("Ingrese la direccion");
                direccionExacta = in.readLine();
                out.println("Ingrese el URL del logo");
                logo = in.readLine();
                gestorCliente.modificarCliente(idCliente, nombre, razonSocial, latitud, longitud, direccionExacta, logo);
                break;
            case 2 :
                for(String var :gestorCliente.imprimirTelefonos(idCliente)){
                    out.println(var);
                }
                out.println("Cuantos telefonos quiere registrar?");
                out.println();
                cantTelefonos = Integer.parseInt(in.readLine());

                for (int i = 0; i<cantTelefonos;i++){
                    out.println("Ingrese el telefono "+(i+1));
                    telefonosCliente.add(in.readLine());
                }
                gestorCliente.actualizarTelefonos(idCliente, telefonosCliente);
                break;
        }
        out.println();
    }

    static void listarClientes() throws java.io.IOException{
        for(String var:gestorCliente.listarClientes()){
            out.println(var);
        }
    }

    static void registrarProducto()throws java.io.IOException{
        String nombre;
        String logo;
        String descripcion;
        String idProducto;
        String version;
        boolean continuar;
        out.println("Ingrese el nombre del producto");
        nombre = in.readLine();
        out.println("Ingrese el url del logo");
        logo = in.readLine();
        out.println("Ingrese la descripción del producto");
        descripcion = in.readLine();
        do{
            out.println("Ingrese el código de identificación del producto");
            idProducto = in.readLine();
            continuar = gestorProducto.validarProducto(idProducto);
            if(!continuar){
                out.print("El producto ya se encuentra registrado, por favor intente de nuevo.");
            }
        }while(!continuar);
        out.println("Ingrese el número de versión del producto");
        version = in.readLine();

        gestorProducto.crearProducto(nombre, logo, descripcion, idProducto, version);
        out.println("Se ha registrado el producto");


    }

    static void modificarProducto() throws java.io.IOException{
        int opcionModificar;
        String idProducto;
        out.println("Ingrese el id del producto a modificar");
        idProducto = in.readLine();
        out.println();
        out.println(gestorProducto.imprimirProducto(idProducto));
        out.println();
        out.println("Seleccione la informacion que desea modificar:");
        out.println("1. Informacion general");
        out.println("2. Version");
        opcionModificar = Integer.parseInt(in.readLine());
        switch(opcionModificar){
            case 1:
                String nombre;
                String logo;
                String descripcion;
                String version;
                out.println("Ingrese el nombre del producto");
                nombre = in.readLine();
                out.println("Ingrese el url del logo");
                logo = in.readLine();
                out.println("Ingrese la descripción del producto");
                descripcion = in.readLine();

                gestorProducto.modificarProducto(nombre, logo, descripcion, idProducto);
                break;
            case 2:
                out.println("Ingrese el número de versión del producto");
                version = in.readLine();
                gestorVersion.modificarVersion(version, idProducto);
                break;
        }
    }

    static void listarProductos(){
        for(String var:gestorProducto.listarProductos()){
            out.println(var);
        }
    }

    static void eliminarProducto()throws java.io.IOException{
        String idProducto;
        out.println("Ingrese el id del producto a eliminar");
        idProducto = in.readLine();
        gestorProducto.eliminarProducto(idProducto);
    }
}