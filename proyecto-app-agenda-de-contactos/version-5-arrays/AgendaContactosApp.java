import java.util.Scanner;

/**
 * Aplicación de Gestión de Agenda de Contactos
 * Práctica 5: Arrays y Estructuras de Datos
 *
 * @author Tu Nombre
 * @version 5.0
 */
public class AgendaContactosApp {

    private static Scanner scanner = new Scanner(System.in);
    private static GestorContactos gestor = new GestorContactos();

    public static void main(String[] args) {
        mostrarCabecera();
        gestor.precargarDatos();

        boolean continuar = true;

        do {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarContactoNuevo();
                    break;
                case 2:
                    buscarContactos();
                    break;
                case 3:
                    gestor.listarTodos();
                    break;
                case 4:
                    gestor.listarFavoritos();
                    break;
                case 5:
                    listarPorCategoria();
                    break;
                case 6:
                    ordenarContactos();
                    break;
                case 7:
                    gestionarHistorial();
                    break;
                case 8:
                    mostrarEstadisticas();
                    break;
                case 9:
                    realizarBackup();
                    break;
                case 10:
                    continuar = false;
                    System.out.println("\n👋 ¡Gracias por usar la Agenda!");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }

        } while (continuar);
    }

    private static void mostrarCabecera() {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║     AGENDA DE CONTACTOS - Versión 5.0        ║");
        System.out.println("║        Con Arrays y Estructuras de Datos     ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n┌──────────── MENÚ PRINCIPAL ────────────┐");
        System.out.println("│  1. Añadir contacto                    │");
        System.out.println("│  2. Buscar contactos                   │");
        System.out.println("│  3. Listar todos                       │");
        System.out.println("│  4. Listar favoritos                   │");
        System.out.println("│  5. Listar por categoría               │");
        System.out.println("│  6. Ordenar contactos                  │");
        System.out.println("│  7. Gestionar historial                │");
        System.out.println("│  8. Estadísticas                       │");
        System.out.println("│  9. Realizar backup                    │");
        System.out.println("│ 10. Salir                              │");
        System.out.println("└────────────────────────────────────────┘");
    }

    private static int leerOpcion() {
        System.out.print("Opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Debe ser un número. Opción: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static void agregarContactoNuevo() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      AÑADIR NUEVO CONTACTO         ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        String telefono;
        do {
            System.out.print("Teléfono (9-15 dígitos): ");
            telefono = scanner.nextLine();
        } while (!validarTelefono(telefono));

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
        } while (!validarEmail(email));

        System.out.print("Empresa: ");
        String empresa = scanner.nextLine();

        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        int edad;
        do {
            System.out.print("Edad (1-120): ");
            while (!scanner.hasNextInt()) {
                System.out.print("❌ Debe ser un número. Edad: ");
                scanner.next();
            }
            edad = scanner.nextInt();
            scanner.nextLine();
        } while (edad < 1 || edad > 120);

        System.out.println("\nCategoría:");
        System.out.println("1. Personal");
        System.out.println("2. Profesional");
        System.out.println("3. Servicios");
        System.out.println("4. Emergencias");
        System.out.print("Opción: ");
        int catOpcion = scanner.nextInt();
        scanner.nextLine();

        String categoria = "Personal";
        switch (catOpcion) {
            case 2: categoria = "Profesional"; break;
            case 3: categoria = "Servicios"; break;
            case 4: categoria = "Emergencias"; break;
        }

        System.out.print("¿Marcar como favorito? (S/N): ");
        boolean favorito = scanner.nextLine().toUpperCase().equals("S");

        gestor.agregarContacto(nombre, telefono, email, empresa, cargo, edad, favorito, categoria);
    }

    private static void buscarContactos() {
        System.out.println("\n--- BUSCAR CONTACTOS ---");
        System.out.println("1. Por nombre");
        System.out.println("2. Por teléfono");
        System.out.println("3. Por email");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre a buscar: ");
                String nombre = scanner.nextLine();
                int[] indices = gestor.buscarPorNombre(nombre);
                gestor.mostrarResultadosBusqueda(indices);
                break;
            case 2:
                System.out.print("Teléfono a buscar: ");
                String telefono = scanner.nextLine();
                int indice = gestor.buscarPorTelefono(telefono);
                if (indice != -1) {
                    System.out.println("\n✅ CONTACTO ENCONTRADO:");
                    gestor.mostrarContacto(indice);
                } else {
                    System.out.println("\n❌ No se encontró el contacto.");
                }
                break;
            case 3:
                System.out.print("Email a buscar: ");
                String email = scanner.nextLine();
                indice = gestor.buscarPorEmail(email);
                if (indice != -1) {
                    System.out.println("\n✅ CONTACTO ENCONTRADO:");
                    gestor.mostrarContacto(indice);
                } else {
                    System.out.println("\n❌ No se encontró el contacto.");
                }
                break;
        }
    }

    private static void listarPorCategoria() {
        System.out.println("\n--- FILTRAR POR CATEGORÍA ---");
        System.out.println("1. Personal");
        System.out.println("2. Profesional");
        System.out.println("3. Servicios");
        System.out.println("4. Emergencias");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String categoria = "Personal";
        switch (opcion) {
            case 2: categoria = "Profesional"; break;
            case 3: categoria = "Servicios"; break;
            case 4: categoria = "Emergencias"; break;
        }

        gestor.listarPorCategoria(categoria);
    }

    private static void ordenarContactos() {
        System.out.println("\n--- ORDENAR CONTACTOS ---");
        System.out.println("1. Por nombre (A-Z)");
        System.out.println("2. Por edad (menor a mayor)");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            gestor.ordenarPorNombre();
            System.out.println("✅ Contactos ordenados por nombre");
        } else if (opcion == 2) {
            gestor.ordenarPorEdad();
            System.out.println("✅ Contactos ordenados por edad");
        }

        gestor.listarTodos();
    }

    private static void gestionarHistorial() {
        System.out.println("\n--- HISTORIAL DE INTERACCIONES ---");
        System.out.println("1. Añadir interacción");
        System.out.println("2. Ver historial completo");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.print("Índice del contacto (0-" + (gestor.getTotalContactos()-1) + "): ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Fecha (DD/MM/YYYY): ");
            String fecha = scanner.nextLine();

            System.out.println("Tipo: 1.Llamada 2.Email 3.Reunión");
            int tipo = scanner.nextInt();
            scanner.nextLine();
            String tipoStr = tipo == 1 ? "Llamada" : tipo == 2 ? "Email" : "Reunión";

            System.out.print("Notas: ");
            String notas = scanner.nextLine();

            gestor.agregarInteraccion(indice, fecha, tipoStr, notas);
        } else if (opcion == 2) {
            gestor.mostrarHistorialCompleto();
        }
    }

    private static void mostrarEstadisticas() {
        gestor.mostrarEstadisticas();
    }

    private static void realizarBackup() {
        String[] backup = gestor.obtenerBackup();
        System.out.println("\n💾 BACKUP REALIZADO");
        System.out.println("Total de contactos en backup: " + backup.length);
        System.out.println("✅ Copia de seguridad completada");
    }

    private static boolean validarTelefono(String tel) {
        if (tel.length() < 9 || tel.length() > 15) return false;
        for (int i = 0; i < tel.length(); i++) {
            if (tel.charAt(i) < '0' || tel.charAt(i) > '9') return false;
        }
        return true;
    }

    private static boolean validarEmail(String em) {
        int posArroba = em.indexOf("@");
        int posPunto = em.lastIndexOf(".");
        return posArroba > 0 && posPunto > posArroba;
    }
}

