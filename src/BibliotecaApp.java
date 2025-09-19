import java.util.Scanner;

public class BibliotecaApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        mostrarTitulo();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> registrarLibro();
                case 2 -> buscarPorISBN();
                case 3 -> buscarPorCriterio();
                case 4 -> listarLibros();
                case 0 -> System.out.println(TextoColor.MORADO + "Saliendo del programa... Linda tarde" + TextoColor.ANSI_RESET);
                default -> System.out.println(TextoColor.ROJO + "Opción inválida. Vuelve a escoger" + TextoColor.ANSI_RESET);
            }
        } while (opcion != 0);
    }

    // 

    private static void mostrarTitulo() {
        System.out.println(TextoColor.CYAN + "==== Bienvenido a la Biblioteca de Babel ====" + TextoColor.ANSI_RESET);
    }

    private static void mostrarMenu() {
        System.out.println(TextoColor.AZUL + "\n--- MENÚ ---" + TextoColor.ANSI_RESET);
        System.out.println(TextoColor.AMARILLO + "Presiona 1 para. Registrar un libro");
        System.out.println("Presiona 2 para buscar un libro por ISBN");
        System.out.println("Presiona 3 para buscar un libros por autor, título o género");
        System.out.println("Presiona 4 para listar todos los libros existentes");
        System.out.println("Presiona 0 para salir" + TextoColor.ANSI_RESET);
    }

    private static void registrarLibro() {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Género principal: ");
        String genero = scanner.nextLine();

        // Para crear un libro con al menos un género
        Libro libro = new Libro(isbn, autor, titulo, genero);

        // Usamos para agregar más géneros
        System.out.print("¿Deseas agregar más géneros? (s/n): ");
        String resp = scanner.nextLine();
        while (resp.equalsIgnoreCase("s")) {
            System.out.print("Otro género: ");
            String otroGenero = scanner.nextLine();
            libro.agregaGenero(otroGenero);

            System.out.print("¿Agregar otro? (s/n): ");
            resp = scanner.nextLine();
        }

        boolean agregado = biblioteca.agregaLibro(libro);
        if (agregado) {
            System.out.println(TextoColor.VERDE + "¡¡¡Libro registrado con éxito!!!" + TextoColor.ANSI_RESET);
        } else {
            System.out.println(TextoColor.ROJO + "Ya existe un libro con ese ISBN, por favoer registralo con otro ISBN" + TextoColor.ANSI_RESET);
        }
    }

    private static void buscarPorISBN() {
        System.out.print("ISBN a buscar: ");
        String isbn = scanner.nextLine();
        Libro libro = biblioteca.obtenLibroPorISBN(isbn);

        if (libro != null) {
            System.out.println(TextoColor.VERDE + "Libro encontrado:" + TextoColor.ANSI_RESET);
            System.out.println(libro);
        } else {
            System.out.println(TextoColor.ROJO + "No se encontró ningún libro con ese ISBN." + TextoColor.ANSI_RESET);
        }
    }

    private static void buscarPorCriterio() {
        System.out.println("Criterios de búsqueda:");
        System.out.println("1. Autor");
        System.out.println("2. Título");
        System.out.println("3. Género");
        int criterioOpcion = leerEntero("Elige un criterio: ");

        String criterio = switch (criterioOpcion) {
            case 1 -> Biblioteca.AUTOR;
            case 2 -> Biblioteca.TITULO;
            case 3 -> Biblioteca.GENERO;
            default -> null;
        };

        if (criterio == null) {
            System.out.println(TextoColor.ROJO + "Criterio inválido." + TextoColor.ANSI_RESET);
            return;
        }

        System.out.print("Valor a buscar: ");
        String valor = scanner.nextLine();

        String resultado = biblioteca.filtraPor(criterio, valor, "\n");
        if (resultado != null) {
            System.out.println(TextoColor.VERDE + "Resultados encontrados:" + TextoColor.ANSI_RESET);
            System.out.println(resultado);
        } else {
            System.out.println(TextoColor.ROJO + "No se encontraron coincidencias." + TextoColor.ANSI_RESET);
        }
    }

    private static void listarLibros() {
        String resultado = biblioteca.obtenTodosLosLibros("\n");
        if (resultado != null) {
            System.out.println(TextoColor.VERDE + "Lista de libros registrados:" + TextoColor.ANSI_RESET);
            System.out.println(resultado);
        } else {
            System.out.println(TextoColor.ROJO + "La biblioteca está vacía, por favor registra un libro" + TextoColor.ANSI_RESET);
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println(TextoColor.ROJO + "Seleccion incorrecta, ingresa un número válido." + TextoColor.ANSI_RESET);
            scanner.next();
            System.out.print(mensaje);
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); 
        return valor;
   }
}
       
