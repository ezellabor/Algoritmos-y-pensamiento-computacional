import java.util.Scanner;

/**
 * Aplicación de Gestión de Agenda de Contactos
 * Práctica 4: Estructuras de Control - Bucles
 *
 * @author Ezequiel Llarena Borges
 * @version 4.0
 */
public class AgendaContactosApp {

    private static Scanner scanner = new Scanner(System.in);

    // Simulación con 3 contactos (temporal hasta Práctica 5)
    private static final int MAX_CONTACTOS = 3;
    private static String[] nombres = new String[MAX_CONTACTOS];
    private static String[] telefonos = new String[MAX_CONTACTOS];
    private static String[] emails = new String[MAX_CONTACTOS];
    private static String[] empresas = new String[MAX_CONTACTOS];
    private static String[] cargos = new String[MAX_CONTACTOS];
    private static int[] edades = new int[MAX_CONTACTOS];
    private static boolean[] favoritos = new boolean[MAX_CONTACTOS];
    private static String[] categorias = new String[MAX_CONTACTOS];

    private static int totalContactos = 0;

    public static void main(String[] args) {
        mostrarCabecera();
        precargarDatosPrueba();

        boolean continuar = true;

        // Usar do-while para mantener el menú activo
        do {
            mostrarMenuPrincipal();
            int opcion = leerOpcionConValidacion();

            switch (opcion) {
                case 1:
                    if (totalContactos < MAX_CONTACTOS) {
                        introducirContacto();
                    } else {
                        System.out.println("❌ Agenda llena (máximo " + MAX_CONTACTOS + " contactos)");
                    }
                    break;
                case 2:
                    buscarContacto();
                    break;
                case 3:
                    listarContactos();
                    break;
                case 4:
                    listarFavoritos();
                    break;
                case 5:
                    listarPorCategoria();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("\n👋 ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }

        } while (continuar);  // Bucle do-while
    }

    private static void mostrarCabecera() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    AGENDA DE CONTACTOS - Versión 4.0        ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n┌─────────── MENÚ PRINCIPAL ───────────┐");
        System.out.println("│ 1. Añadir contacto                   │");
        System.out.println("│ 2. Buscar contacto                   │");
        System.out.println("│ 3. Listar todos los contactos        │");
        System.out.println("│ 4. Listar solo favoritos             │");
        System.out.println("│ 5. Listar por categoría              │");
        System.out.println("│ 6. Estadísticas                      │");
        System.out.println("│ 7. Salir                             │");
        System.out.println("└──────────────────────────────────────┘");
    }

    /**
     * Lee una opción con validación usando bucle while
     * Repite hasta que la entrada sea válida
     */
    private static int leerOpcionConValidacion() {
        int opcion = -1;
        boolean entradaValida = false;
        int intentos = 0;
        final int MAX_INTENTOS = 3;

        // Bucle while para validación
        while (!entradaValida && intentos < MAX_INTENTOS) {
            System.out.print("Opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
                entradaValida = true;
            } else {
                System.out.println("❌ Error: Debe introducir un número");
                scanner.nextLine(); // Limpiar buffer
                intentos++;

                if (intentos < MAX_INTENTOS) {
                    System.out.println("Intento " + (intentos + 1) + " de " + MAX_INTENTOS);
                } else {
                    System.out.println("Demasiados intentos erróneos.");
                }
            }
        }

        return opcion;
    }

    private static void introducirContacto() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      AÑADIR NUEVO CONTACTO         ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.print("Nombre completo: ");
        nombres[totalContactos] = scanner.nextLine();

        // Validar teléfono con bucle
        telefonos[totalContactos] = leerTelefonoValido();

        // Validar email con bucle
        emails[totalContactos] = leerEmailValido();

        System.out.print("Empresa: ");
        empresas[totalContactos] = scanner.nextLine();

        System.out.print("Cargo: ");
        cargos[totalContactos] = scanner.nextLine();

        // Validar edad con bucle
        edades[totalContactos] = leerEdadValida();

        System.out.println("\n--- CATEGORIZACIÓN ---");
        System.out.println("1. Personal");
        System.out.println("2. Profesional");
        System.out.println("3. Servicios");
        System.out.println("4. Emergencias");
        System.out.print("Categoría: ");
        int cat = scanner.nextInt();
        scanner.nextLine();

        switch (cat) {
            case 1: categorias[totalContactos] = "Personal"; break;
            case 2: categorias[totalContactos] = "Profesional"; break;
            case 3: categorias[totalContactos] = "Servicios"; break;
            case 4: categorias[totalContactos] = "Emergencias"; break;
            default: categorias[totalContactos] = "Personal";
        }

        System.out.print("¿Favorito? (S/N): ");
        favoritos[totalContactos] = scanner.nextLine().toUpperCase().equals("S");

        totalContactos++;
        System.out.println("\n✅ Contacto añadido correctamente!");
    }

    /**
     * Lee un teléfono válido usando bucle do-while
     * Repite hasta que sea válido
     */
    private static String leerTelefonoValido() {
        String telefono;
        boolean valido;

        do {
            System.out.print("Teléfono (solo dígitos, 9-15 caracteres): ");
            telefono = scanner.nextLine();
            valido = validarTelefono(telefono);

            if (!valido) {
                System.out.println("❌ Teléfono inválido. Intente nuevamente.");
            }
        } while (!valido);

        return telefono;
    }

    /**
     * Lee un email válido usando bucle do-while
     */
    private static String leerEmailValido() {
        String email;
        boolean valido;

        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            valido = validarEmail(email);

            if (!valido) {
                System.out.println("❌ Email inválido. Debe contener @ y .");
            }
        } while (!valido);

        return email;
    }

    /**
     * Lee una edad válida usando bucle do-while
     */
    private static int leerEdadValida() {
        int edad;
        boolean valido;

        do {
            System.out.print("Edad (1-120): ");
            edad = scanner.nextInt();
            scanner.nextLine();
            valido = validarEdad(edad);

            if (!valido) {
                System.out.println("❌ Edad inválida. Debe estar entre 1 y 120.");
            }
        } while (!valido);

        return edad;
    }

    /**
     * Busca contactos por nombre usando bucle for
     */
    private static void buscarContacto() {
        if (totalContactos == 0) {
            System.out.println("\n❌ No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n--- BUSCAR CONTACTO ---");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por teléfono");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            buscarPorNombre();
        } else if (opcion == 2) {
            buscarPorTelefono();
        }
    }

    /**
     * Busca por nombre (búsqueda parcial) usando for
     */
    private static void buscarPorNombre() {
        System.out.print("Nombre a buscar: ");
        String busqueda = scanner.nextLine().toLowerCase();

        boolean encontrado = false;

        // Usar bucle for para búsqueda
        for (int i = 0; i < totalContactos; i++) {
            if (nombres[i].toLowerCase().contains(busqueda)) {
                if (!encontrado) {
                    System.out.println("\n📋 RESULTADOS:");
                    encontrado = true;
                }
                mostrarContacto(i);
            }
        }

        if (!encontrado) {
            System.out.println("\n❌ No se encontraron contactos con ese nombre.");
        }
    }

    /**
     * Busca por teléfono usando while con break
     */
    private static void buscarPorTelefono() {
        System.out.print("Teléfono a buscar: ");
        String busqueda = scanner.nextLine();

        int i = 0;
        boolean encontrado = false;

        // Usar while con break para búsqueda exacta
        while (i < totalContactos) {
            if (telefonos[i].equals(busqueda)) {
                System.out.println("\n✅ CONTACTO ENCONTRADO:");
                mostrarContacto(i);
                encontrado = true;
                break;  // Salir del bucle cuando se encuentra
            }
            i++;
        }

        if (!encontrado) {
            System.out.println("\n❌ No se encontró contacto con ese teléfono.");
        }
    }

    /**
     * Lista todos los contactos usando for
     */
    private static void listarContactos() {
        if (totalContactos == 0) {
            System.out.println("\n❌ No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║       LISTADO DE CONTACTOS            ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("Total: " + totalContactos + " contacto(s)\n");

        // Usar for para recorrer todos los contactos
        for (int i = 0; i < totalContactos; i++) {
            System.out.println("--- Contacto " + (i + 1) + " ---");
            mostrarContacto(i);
            System.out.println();
        }
    }

    /**
     * Lista solo los favoritos usando for con continue
     */
    private static void listarFavoritos() {
        if (totalContactos == 0) {
            System.out.println("\n❌ No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n⭐ CONTACTOS FAVORITOS:");

        int contadorFavoritos = 0;

        // Usar for con continue para saltar los no favoritos
        for (int i = 0; i < totalContactos; i++) {
            if (!favoritos[i]) {
                continue;  // Saltar este contacto si no es favorito
            }

            contadorFavoritos++;
            System.out.println("\n--- Favorito " + contadorFavoritos + " ---");
            mostrarContacto(i);
        }

        if (contadorFavoritos == 0) {
            System.out.println("No hay contactos marcados como favoritos.");
        } else {
            System.out.println("\nTotal favoritos: " + contadorFavoritos);
        }
    }

    /**
     * Lista contactos por categoría
     */
    private static void listarPorCategoria() {
        if (totalContactos == 0) {
            System.out.println("\n❌ No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n--- FILTRAR POR CATEGORÍA ---");
        System.out.println("1. Personal");
        System.out.println("2. Profesional");
        System.out.println("3. Servicios");
        System.out.println("4. Emergencias");
        System.out.print("Categoría: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        String categoriaFiltro = "";
        switch (opcion) {
            case 1: categoriaFiltro = "Personal"; break;
            case 2: categoriaFiltro = "Profesional"; break;
            case 3: categoriaFiltro = "Servicios"; break;
            case 4: categoriaFiltro = "Emergencias"; break;
        }

        System.out.println("\n📂 CATEGORÍA: " + categoriaFiltro);

        int contador = 0;
        for (int i = 0; i < totalContactos; i++) {
            if (categorias[i].equals(categoriaFiltro)) {
                contador++;
                System.out.println("\n--- Contacto " + contador + " ---");
                mostrarContacto(i);
            }
        }

        if (contador == 0) {
            System.out.println("No hay contactos en esta categoría.");
        } else {
            System.out.println("\nTotal: " + contador + " contacto(s)");
        }
    }

    /**
     * Muestra un contacto específico
     */
    private static void mostrarContacto(int indice) {
        String icono = favoritos[indice] ? "⭐" : "  ";

        System.out.println(icono + " " + nombres[indice]);
        System.out.println("📞 " + telefonos[indice]);
        System.out.println("📧 " + emails[indice]);
        System.out.println("🏢 " + empresas[indice] + " - " + cargos[indice]);
        System.out.println("🎂 " + edades[indice] + " años");
        System.out.println("🏷️  " + categorias[indice]);
    }

    private static void mostrarEstadisticas() {
        if (totalContactos == 0) {
            System.out.println("\n❌ No hay contactos en la agenda.");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       ESTADÍSTICAS GENERALES         ║");
        System.out.println("╚══════════════════════════════════════╝");

        // Contadores
        int personales = 0, profesionales = 0, servicios = 0, emergencias = 0;
        int contadorFavoritos = 0;
        int sumaEdades = 0;

        // Usar for para calcular estadísticas
        for (int i = 0; i < totalContactos; i++) {
            sumaEdades += edades[i];

            if (favoritos[i]) {
                contadorFavoritos++;
            }

            switch (categorias[i]) {
                case "Personal": personales++; break;
                case "Profesional": profesionales++; break;
                case "Servicios": servicios++; break;
                case "Emergencias": emergencias++; break;
            }
        }

        double edadPromedio = (double) sumaEdades / totalContactos;

        System.out.println("Total contactos: " + totalContactos);
        System.out.println("\n--- Por Categoría ---");
        System.out.println("Personal: " + personales);
        System.out.println("Profesional: " + profesionales);
        System.out.println("Servicios: " + servicios);
        System.out.println("Emergencias: " + emergencias);
        System.out.println("\nFavoritos: " + contadorFavoritos);
        System.out.println("Edad promedio: " + String.format("%.1f", edadPromedio) + " años");
    }

    /**
     * Precarga datos de prueba
     */
    private static void precargarDatosPrueba() {
        // Dejar espacio para que el alumno añada sus contactos
        System.out.println("ℹ️  Agenda vacía. Añade tus contactos.");
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
        int posArroba = em.indexOf("@");
        int posPunto = em.lastIndexOf(".");
        return posArroba > 0 && posPunto > posArroba;
    }

    private static boolean validarEdad(int ed) {
        return ed >= 1 && ed <= 120;
    }
}
