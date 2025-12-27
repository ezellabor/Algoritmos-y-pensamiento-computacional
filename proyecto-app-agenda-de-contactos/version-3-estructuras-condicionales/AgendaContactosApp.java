import java.util.Scanner;

/**
 * Aplicación de Gestión de Agenda de Contactos
 * Práctica 3: Estructuras de Control - Condicionales
 *
 * @author Ezequiel Llarena Borges
 * @version 3.0
 */
public class AgendaContactosApp {

    private static Scanner scanner = new Scanner(System.in);

    // Datos del último contacto (simulación temporal)
    private static String nombre = "";
    private static String telefono = "";
    private static String email = "";
    private static String empresa = "";
    private static String cargo = "";
    private static int edad = 0;
    private static boolean favorito = false;
    private static String categoria = "";

    // Estadísticas
    private static int totalContactos = 0;
    private static int contactosPersonales = 0;
    private static int contactosProfesionales = 0;
    private static int contactosServicios = 0;
    private static int contactosEmergencias = 0;
    private static int contactosFavoritos = 0;
    private static int sumaEdades = 0;

    // Contadores por edad
    private static int menoresEdad = 0;
    private static int adultosJovenes = 0;
    private static int adultos = 0;
    private static int seniors = 0;

    public static void main(String[] args) {
        mostrarCabecera();

        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            // Usar switch para el menú principal
            switch (opcion) {
                case 1:
                    introducirContacto();
                    break;
                case 2:
                    buscarContacto();
                    break;
                case 3:
                    listarContactos();
                    break;
                case 4:
                    editarContacto();
                    break;
                case 5:
                    eliminarContacto();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 7:
                    configuracion();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("\n¡Hasta pronto!");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarCabecera() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    AGENDA DE CONTACTOS - Versión 3.0        ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n┌─────────── MENÚ PRINCIPAL ───────────┐");
        System.out.println("│ 1. Añadir contacto                   │");
        System.out.println("│ 2. Buscar contacto                   │");
        System.out.println("│ 3. Listar contactos                  │");
        System.out.println("│ 4. Editar contacto                   │");
        System.out.println("│ 5. Eliminar contacto                 │");
        System.out.println("│ 6. Estadísticas                      │");
        System.out.println("│ 7. Configuración                     │");
        System.out.println("│ 8. Salir                             │");
        System.out.println("└──────────────────────────────────────┘");
    }

    private static int leerOpcion() {
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private static void introducirContacto() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      AÑADIR NUEVO CONTACTO         ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.print("Nombre completo: ");
        nombre = scanner.nextLine();

        System.out.print("Teléfono: ");
        telefono = scanner.nextLine();

        if (!validarTelefono(telefono)) {
            System.out.println("⚠ Advertencia: El teléfono debe contener solo dígitos.");
        }

        System.out.print("Email: ");
        email = scanner.nextLine();

        if (!validarEmail(email)) {
            System.out.println("⚠ Advertencia: El email no tiene formato válido.");
        }

        System.out.print("Empresa (dejar vacío si no aplica): ");
        empresa = scanner.nextLine();

        System.out.print("Cargo (dejar vacío si no aplica): ");
        cargo = scanner.nextLine();

        System.out.print("Edad: ");
        edad = scanner.nextInt();
        scanner.nextLine();

        if (!validarEdad(edad)) {
            System.out.println("⚠ Advertencia: Edad fuera de rango válido.");
        }

        // Categorizar contacto usando if-else-if
        categoria = categorizarContacto();

        // Preguntar si es favorito
        System.out.print("¿Marcar como favorito? (S/N): ");
        String respuesta = scanner.nextLine().toUpperCase();
        favorito = respuesta.equals("S") || respuesta.equals("SI");

        // Actualizar estadísticas
        actualizarEstadisticas();

        // Mostrar prioridad calculada
        String prioridad = calcularPrioridad();

        System.out.println("\n✅ ¡Contacto añadido correctamente!");
        System.out.println("Categoría: " + categoria);
        System.out.println("Prioridad: " + prioridad);

        mostrarContactoActual();
    }

    /**
     * Categoriza el contacto según los datos introducidos
     * Usa if-else-if para determinar la categoría
     */
    private static String categorizarContacto() {
        System.out.println("\n--- CATEGORIZACIÓN ---");
        System.out.println("1. Personal (Familia/Amigos)");
        System.out.println("2. Profesional (Trabajo/Negocios)");
        System.out.println("3. Servicios (Médico/Mecánico/etc.)");
        System.out.println("4. Emergencias");
        System.out.print("Seleccione categoría: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        String cat;

        // Usar if-else-if para categorización
        if (opcion == 1) {
            cat = "Personal";
            contactosPersonales++;
        } else if (opcion == 2) {
            cat = "Profesional";
            contactosProfesionales++;
        } else if (opcion == 3) {
            cat = "Servicios";
            contactosServicios++;
        } else if (opcion == 4) {
            cat = "Emergencias";
            contactosEmergencias++;
        } else {
            System.out.println("⚠ Opción no válida, se asignará 'Personal'");
            cat = "Personal";
            contactosPersonales++;
        }

        return cat;
    }

    /**
     * Calcula la prioridad del contacto
     * Alta: Favoritos + Emergencias
     * Media: Profesionales
     * Baja: Resto
     */
    private static String calcularPrioridad() {
        String prioridad;

        if (favorito || categoria.equals("Emergencias")) {
            prioridad = "ALTA";
        } else if (categoria.equals("Profesional")) {
            prioridad = "MEDIA";
        } else {
            prioridad = "BAJA";
        }

        return prioridad;
    }

    /**
     * Clasifica por edad usando if-else-if
     */
    private static String clasificarPorEdad(int ed) {
        String clasificacion;

        if (ed < 18) {
            clasificacion = "Menor de edad";
            menoresEdad++;
        } else if (ed >= 18 && ed <= 30) {
            clasificacion = "Adulto joven";
            adultosJovenes++;
        } else if (ed >= 31 && ed <= 60) {
            clasificacion = "Adulto";
            adultos++;
        } else {
            clasificacion = "Senior";
            seniors++;
        }

        return clasificacion;
    }

    private static void actualizarEstadisticas() {
        totalContactos++;
        sumaEdades += edad;

        if (favorito) {
            contactosFavoritos++;
        }

        // Clasificar por edad
        clasificarPorEdad(edad);
    }

    private static void mostrarContactoActual() {
        System.out.println("\n┌─────────────────────────────────────┐");

        // Usar operador ternario para el ícono de favorito
        String iconoFavorito = favorito ? "⭐" : "  ";
        System.out.println("│ " + iconoFavorito + " " + nombre);

        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 📞 " + telefono);
        System.out.println("│ 📧 " + email);

        // Usar operador ternario para mostrar empresa/cargo si existen
        String infoEmpresa = (!empresa.isEmpty()) ? empresa : "N/A";
        String infoCargo = (!cargo.isEmpty()) ? cargo : "N/A";
        System.out.println("│ 🏢 " + infoEmpresa + " - " + infoCargo);

        System.out.println("│ 🎂 " + edad + " años - " + clasificarPorEdad(edad));
        System.out.println("│ 🏷️  " + categoria);
        System.out.println("│ ⚡ Prioridad: " + calcularPrioridad());
        System.out.println("└─────────────────────────────────────┘");
    }

    private static void buscarContacto() {
        System.out.println("\n[Funcionalidad de búsqueda - Pendiente Práctica 4]");
    }

    private static void listarContactos() {
        System.out.println("\n[Funcionalidad de listado - Pendiente Práctica 4]");
    }

    private static void editarContacto() {
        System.out.println("\n[Funcionalidad de edición - Pendiente]");
    }

    private static void eliminarContacto() {
        System.out.println("\n[Funcionalidad de eliminación - Pendiente]");
    }

    private static void mostrarEstadisticas() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       ESTADÍSTICAS GENERALES         ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.println("Total de contactos: " + totalContactos);

        if (totalContactos > 0) {
            double edadPromedio = (double) sumaEdades / totalContactos;

            System.out.println("\n--- Por Categoría ---");
            System.out.println("Personal: " + contactosPersonales);
            System.out.println("Profesional: " + contactosProfesionales);
            System.out.println("Servicios: " + contactosServicios);
            System.out.println("Emergencias: " + contactosEmergencias);

            System.out.println("\n--- Por Edad ---");
            System.out.println("Menores de edad: " + menoresEdad);
            System.out.println("Adultos jóvenes (18-30): " + adultosJovenes);
            System.out.println("Adultos (31-60): " + adultos);
            System.out.println("Seniors (+60): " + seniors);
            System.out.println("Edad promedio: " + String.format("%.1f", edadPromedio) + " años");

            System.out.println("\n--- Otros ---");
            System.out.println("Favoritos: " + contactosFavoritos);
        } else {
            System.out.println("\nNo hay contactos registrados.");
        }
    }

    private static void configuracion() {
        System.out.println("\n[Configuración - Pendiente]");
    }

    private static boolean validarTelefono(String tel) {
        if (tel == null || tel.length() < 9 || tel.length() > 15) {
            return false;
        }

        for (int i = 0; i < tel.length(); i++) {
            if (tel.charAt(i) < '0' || tel.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

    private static boolean validarEmail(String em) {
        boolean tieneArroba = em.contains("@");
        boolean tienePunto = em.contains(".");
        int posArroba = em.indexOf("@");
        int posPunto = em.lastIndexOf(".");

        return tieneArroba && tienePunto && posArroba > 0 && posPunto > posArroba;
    }

    private static boolean validarEdad(int ed) {
        return ed >= 1 && ed <= 120;
    }
}
