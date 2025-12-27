import java.util.Scanner;

/**
 * Aplicación de Gestión de Agenda de Contactos
 * Práctica 1: Introducción a Java y Estructura Básica
 *
 * @author Ezequiel Llarena Borges
 * @version 1.0
 */
public class AgendaContactosApp {

    // Scanner global para leer entrada del usuario
    private static Scanner scanner = new Scanner(System.in);

    // Variables para almacenar información de un contacto
    private static String nombre = "";
    private static String telefono = "";
    private static String email = "";
    private static String empresa = "";
    private static String cargo = "";
    private static int edad = 0;
    private static boolean favorito = false;

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   AGENDA DE CONTACTOS - Versión 1.0");
        System.out.println("===========================================");
        System.out.println();

        // Mostrar menú principal
        mostrarMenu();

        // Leer opción del usuario
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Procesar opción (por ahora solo opción 1)
        if (opcion == 1) {
            introducirContacto();
            mostrarContacto();
        } else {
            System.out.println("Opción no implementada todavía.");
        }

        System.out.println("\n¡Gracias por usar la Agenda de Contactos!");
    }

    /**
     * Muestra el menú principal de opciones
     */
    private static void mostrarMenu() {
        System.out.println("MENÚ PRINCIPAL:");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Listar contactos");
        System.out.println("4. Salir");
        System.out.println();
    }

    /**
     * Lee los datos de un contacto desde teclado
     */
    private static void introducirContacto() {
        System.out.println("\n--- AÑADIR NUEVO CONTACTO ---");

        System.out.print("Nombre completo: ");
        nombre = scanner.nextLine();

        System.out.print("Teléfono: ");
        telefono = scanner.nextLine();

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Empresa: ");
        empresa = scanner.nextLine();

        System.out.print("Cargo: ");
        cargo = scanner.nextLine();

        System.out.print("Edad: ");
        edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("¿Es favorito? (true/false): ");
        favorito = scanner.nextBoolean();
        scanner.nextLine(); // Limpiar buffer

        System.out.println("\n¡Contacto añadido correctamente!");
    }

    /**
     * Muestra los datos del contacto almacenado
     */
    private static void mostrarContacto() {
        System.out.println("\n--- DATOS DEL CONTACTO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Empresa: " + empresa);
        System.out.println("Cargo: " + cargo);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Favorito: " + (favorito ? "Sí" : "No"));
    }
}
