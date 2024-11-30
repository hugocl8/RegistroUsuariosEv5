import java.util.ArrayList;
import java.util.Scanner;

class Persona {
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String fechaNacimiento;

    public Persona(String nombreUsuario, String contrasena, String nombreCompleto, String fechaNacimiento) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public static boolean validarContrasena(String contrasena) {
        if (contrasena.length() <= 10) {
            System.out.println("Contraseña debil: debe tener más de 10 caracteres.");
            return false;
        }

        long mayusculas = contrasena.chars().filter(Character::isUpperCase).count();
        if (mayusculas <= 2) {
            System.out.println("Contraseña debil: debe tener al menos 3 caracteres numéricos o símbolos.");
            return false;
        }

        return true;
    }
    public static boolean esMayorDeEdad (String fechaNacimiento){
        int anioNacimiento = Integer.parseInt(fechaNacimiento.split("-")[2]);
        int anioActual = java.time.Year.now().getValue();
        return (anioActual - anioNacimiento) >= 18;
    }
}

public class RegistroUsuarios {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Persona> listaUsuarios = new ArrayList<>();

        while (listaUsuarios.size() < 10) {
            System.out.println("Ingrese un nuevo nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            String contrasena;
            do {
                System.out.println("Ingrese contraseña: ");
                contrasena = scanner.nextLine();
            } while (!Persona.validarContrasena(contrasena));

            System.out.println("Ingrese su nombre completo: ");
            String nombreCompleto = scanner.nextLine();

            System.out.println("Ingrese la fecha de nacimiento (DD-MM-AAAA): ");
            String fechaNacimiento = scanner.nextLine();

            boolean esMayor = Persona.esMayorDeEdad(fechaNacimiento);
            listaUsuarios.add(new Persona(nombreUsuario, contrasena, nombreCompleto, fechaNacimiento));

            System.out.println("\nBienvenido " + nombreUsuario + "\n");
            if (esMayor) {
                System.out.println("Puede pasar a la zona para mayores de 18 años.");
            } else {
                System.out.println("Acceso restringido para menores de edad.");
            }
            System.out.println("\nLista total de usuarios registrados: ");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                System.out.println((i + 1) + "- " + listaUsuarios.get(i).getNombreUsuario());
            }
            System.out.println("\n¿Desea registrar otro usuario?");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                break;
            }
        }
        System.out.println("Registro finalizado");
        scanner.close();
    }
}
