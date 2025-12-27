import java.util.Scanner;

/**
 * Aplicación de Gestión de Agenda de Contactos
 * Práctica 2: Operadores y Expresiones
 *
 * @author Ezequiel Llarena Borges
 * @version 2.0
 */
public class AgendaContactosApp {

    private static Scanner scanner = new Scanner(System.in);

    // Variables para almacenar contactos (simulación simple)
    private static String nombre = "";
    private static String telefono = "";
    private static String email = "";
    private static String empresa = "";
    private static String cargo = "";
    private static int edad = 0;
    private static boolean favorito = false;
    private static String categoria = ""; // "Personal" o "Profesional"

    // Variables para estadísticas
    private static int totalContactos = 0;
    private static int contactosPersonales = 0;
    private static int contactosProfesionales = 0;
    private static int contactosFavoritos = 0;
    private static int sumaEdades = 0;

    public static void main(String[] args) {
        mostrarCabecera();

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    introducirContacto();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        System.out.println("\n¡Gracias por usar la Agenda de Contactos!");
    }

    private static void mostrarCabecera() {
        System.out.println("===========================================");
        System.out.println("   AGENDA DE CONTACTOS - Versión 2.0");
        System.out.println("===========================================");
        System.out.println();
    }

    private static void mostrarMenu() {
        System.out.println("\nMENÚ PRINCIPAL:");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.println();
    }

    private static int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static void introducirContacto() {
        System.out.println("\n--- AÑADIR NUEVO CONTACTO ---");

        System.out.print("Nombre completo: ");
        nombre = scanner.nextLine();

        System.out.print("Teléfono: ");
        telefono = scanner.nextLine();

        // Validar teléfono
        if (!validarTelefono(telefono)) {
            System.out.println("⚠ Advertencia: El teléfono debe contener solo dígitos.");
        }

        System.out.print("Email: ");
        email = scanner.nextLine();

        // Validar email
        if (!validarEmail(email)) {
            System.out.println("⚠ Advertencia: El email no tiene formato válido.");
        }

        System.out.print("Empresa: ");
        empresa = scanner.nextLine();

        System.out.print("Cargo: ");
        cargo = scanner.nextLine();

        System.out.print("Edad: ");
        edad = scanner.nextInt();
        scanner.nextLine();

        // Validar edad
        if (!validarEdad(edad)) {
            System.out.println("⚠ Advertencia: Edad fuera de rango válido (1-120).");
        }

        System.out.print("Categoría (1=Personal, 2=Profesional): ");
        int catOpcion = scanner.nextInt();
        scanner.nextLine();
        categoria = (catOpcion == 1) ? "Personal" : "Profesional";

        System.out.print("¿Es favorito? (1=Sí, 0=No): ");
        int favOpcion = scanner.nextInt();
        scanner.nextLine();
        favorito = (favOpcion == 1);

        // Actualizar estadísticas usando operadores de asignación compuesta
        totalContactos++;
        sumaEdades += edad;

        if (categoria.equals("Personal")) {
            contactosPersonales++;
        } else {
            contactosProfesionales++;
        }

        if (favorito) {
            contactosFavoritos++;
        }

        System.out.println("\n✓ ¡Contacto añadido correctamente!");
        mostrarContacto();
    }

    private static void mostrarContacto() {
        System.out.println("\n--- DATOS DEL CONTACTO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Empresa: " + empresa);
        System.out.println("Cargo: " + cargo);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Categoría: " + categoria);
        System.out.println("Favorito: " + (favorito ? "Sí" : "No"));
    }

    private static void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DE LA AGENDA ---");
        System.out.println("Total de contactos: " + totalContactos);

        if (totalContactos > 0) {
            // Calcular porcentajes usando operadores aritméticos
            double porcentajePersonales = (contactosPersonales * 100.0) / totalContactos;
            double porcentajeProfesionales = (contactosProfesionales * 100.0) / totalContactos;
            double porcentajeFavoritos = (contactosFavoritos * 100.0) / totalContactos;
            double edadPromedio = (double) sumaEdades / totalContactos;

            System.out.println("Contactos Personales: " + contactosPersonales +
                             " (" + String.format("%.1f", porcentajePersonales) + "%)");
            System.out.println("Contactos Profesionales: " + contactosProfesionales +
                             " (" + String.format("%.1f", porcentajeProfesionales) + "%)");
            System.out.println("Contactos Favoritos: " + contactosFavoritos +
                             " (" + String.format("%.1f", porcentajeFavoritos) + "%)");
            System.out.println("Edad Promedio: " + String.format("%.1f", edadPromedio) + " años");
        } else {
            System.out.println("No hay contactos registrados todavía.");
        }
    }

    /**
     * Valida que el teléfono contenga solo dígitos
     * Usa operadores lógicos y relacionales
     */
    private static boolean validarTelefono(String tel) {
        // Verificar que no esté vacío y que tenga longitud razonable
        if (tel == null || tel.length() < 9 || tel.length() > 15) {
            return false;
        }

        // Verificar que todos los caracteres sean dígitos
        for (int i = 0; i < tel.length(); i++) {
            char c = tel.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    /**
     * Valida formato básico de email
     * Debe contener @ y .
     */
    private static boolean validarEmail(String em) {
        // Usar operadores lógicos
        boolean tieneArroba = em.contains("@");
        boolean tienePunto = em.contains(".");
        boolean longitudAdecuada = em.length() >= 5;

        // Verificar que @ venga antes que el punto
        int posArroba = em.indexOf("@");
        int posPunto = em.lastIndexOf(".");
        boolean ordenCorrecto = posArroba > 0 && posPunto > posArroba;

        return tieneArroba && tienePunto && longitudAdecuada && ordenCorrecto;
    }

    /**
     * Valida que la edad esté en rango válido (1-120)
     */
    private static boolean validarEdad(int ed) {
        return ed >= 1 && ed <= 120;
    }
}
