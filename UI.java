import com.softwareCelestial.*; //Quitar esto cuando se importe la libreria.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
public class UI {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

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
        out.println("1.  Option 1.");
        out.println("2.  Option 2.");
        out.println("3.  Option 3.");
        out.println("0.  Exit.");
        out.println();
    }

    static void showOpt1Menu() {
        out.println();
        out.println("Option 1");
        out.println();
        out.println("1.  .");
        out.println("2.  .");
        out.println("3.  .");
        out.println("4.  .");
        out.println("0.  Go back.");
        out.println();
    }

    static void showOpt2Menu() {
        out.println();
        out.println("Option 2");
        out.println();
        out.println("1.  .");
        out.println("2.  .");
        out.println("3.  .");
        out.println("4.  .");
        out.println("0.  Go back.");
        out.println();
    }

    static void showOpt3Menu() {
        out.println();
        out.println("Instalaciones");
        out.println();
        out.println("1.  Realizar instalacion.");
        out.println("2.  Modificar una instalacion.");
        out.println("3.  Listar instalaciones.");
        out.println("4.  Agregar tareas a una instalacion.");
        out.println("5.  Modificar el estado de las tareas.");
        out.println("6.  Modificar el estado de las instalaciones.");
        out.println("0.  Regresar.");
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

    static boolean exeOpt1Action(int poption) {
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
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

    static boolean exeOpt2Action(int poption) {
        boolean previous = false;

        switch (poption) {
            case 0: // Goes back to the previous menu.
                previous = true;
                break;
            case 1: //
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
                modificarInstalacion();
                break;
            case 3: //
                listarInstalaciones();
                break;
            case 4: //
                agregarTareasAInstalaciones();
                break;
            case 5: //
                modificarEstadoTareas();
                break;
            case 6: //
                ModificarEstadoInstalaciones();
                break;
            default: // Any value that isn't in the options
                out.println("Opcion invalida.");
                out.println();
                break;
        }
        return previous;
    }

    public static void registrarInstalacion(){
        try{
            
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }
}