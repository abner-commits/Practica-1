import java.util.Scanner;

/**
 * Programa: Registro y ordenamiento de juegos de mesa
 *
 * Descripción:
 * Permite al usuario registrar hasta 100 juegos de mesa
 * Algoritmo utilizado:
 * Se usó el algoritmo de ordenamiento Burbuja (Bubble Sort),
 * fuente: https://es.wikipedia.org/wiki/Ordenamiento_de_burbuja
 *
 * Uso de compareTo:
 * El método compareTo() compara cadenas lexicográficamente.
 * Devuelve un número negativo si la primera es menor, 0 si son iguales,
 * y positivo si la primera es mayor.
 */

public class JuegosDeMesa {

    Scanner sc = new Scanner(System.in);
    JuegoDeMesa[] juegos = new JuegoDeMesa[100]; // Aqui determinamos el número de juegos de mesa que se pueden registrar
    int contador = 0; // cantidad actual de juegos

    public static void main(String[] args) {
        JuegosDeMesa app = new JuegosDeMesa ();
        app.iniciar();
    }

    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarJuego();
                    break;
                case 2:
                    ordenarPorNombre();
                    break;
                case 3:
                    ordenarPorCalificacion();
                    break;
                case 4:
                    ordenarPorGenero();
                    break;
                case 5:
                    mostrarMejorYPeorCalificado();
                    break;
                case 6:
                    mostrarJuegos();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\nMENÚ DE JUEGOS DE MESA ");
        System.out.println("1. Registrar juego");
        System.out.println("2. Ordenar por nombre");
        System.out.println("3. Ordenar por calificación");
        System.out.println("4. Ordenar por género");
        System.out.println("5. Mostrar mejor y peor calificado");
        System.out.println("6. Mostrar todos los juegos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    public void registrarJuego() { //Registramos el juego por nombre, calificacion y genero
        if (contador < juegos.length) {
            System.out.print("Nombre del juego: ");
            String nombre = sc.nextLine();
            System.out.print("Calificación (0 a 10): ");
            double calificacion = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
            System.out.print("Género: ");
            String genero = sc.nextLine();

            JuegoDeMesa juego = new JuegoDeMesa(nombre, calificacion, genero);
            juegos[contador] = juego;
            contador++;
            System.out.println("Juego registrado con éxito.");
        } else {
            System.out.println("No se pueden registrar más juegos.");
        }
    }

    public void ordenarPorNombre() {  //Para registrar un juego por orden alfabetico
        for (int i = 0; i < contador - 1; i++) {
            for (int j = 0; j < contador - i - 1; j++) {
                if (juegos[j].getNombre().compareToIgnoreCase(juegos[j + 1].getNombre()) > 0) {
                    JuegoDeMesa temp = juegos[j];
                    juegos[j] = juegos[j + 1];
                    juegos[j + 1] = temp;
                }
            }
        }
        System.out.println("Juegos ordenados por nombre (A-Z).");
    }

    public void ordenarPorCalificacion() { //Para registrar un juego por su calificacion
        for (int i = 0; i < contador - 1; i++) {
            for (int j = 0; j < contador - i - 1; j++) {
                if (juegos[j].getCalificacion() > juegos[j + 1].getCalificacion()) {
                    JuegoDeMesa temp = juegos[j];
                    juegos[j] = juegos[j + 1];
                    juegos[j + 1] = temp;
                }
            }
        }
        System.out.println("Juegos ordenados por calificación (menor a mayor).");
    }

    public void ordenarPorGenero() { //Ordenamos los juegos por genero
        for (int i = 0; i < contador - 1; i++) {
            for (int j = 0; j < contador - i - 1; j++) {
                if (juegos[j].getGenero().compareToIgnoreCase(juegos[j + 1].getGenero()) > 0) {
                    JuegoDeMesa temp = juegos[j];
                    juegos[j] = juegos[j + 1];
                    juegos[j + 1] = temp;
                }
            }
        }
        System.out.println("Juegos ordenados por género (A-Z).");
    }

    public void mostrarMejorYPeorCalificado() {
        if (contador == 0) {
            System.out.println("No hay juegos registrados.");
        } else {
            JuegoDeMesa mejor = juegos[0];
            JuegoDeMesa peor = juegos[0];

            for (int i = 1; i < contador; i++) {
                if (juegos[i].getCalificacion() > mejor.getCalificacion()) {
                    mejor = juegos[i];
                }
                if (juegos[i].getCalificacion() < peor.getCalificacion()) {
                    peor = juegos[i];
                }
            }

            System.out.println("\n--- Mejor calificado ---");
            System.out.println(mejor);
            System.out.println("\n--- Peor calificado ---");
            System.out.println(peor);
        }
    }

    public void mostrarJuegos() {
        if (contador == 0) {
            System.out.println("No hay juegos registrados.");
        } else {
            System.out.println("\n--- Lista de juegos ---");
            for (int i = 0; i < contador; i++) {
                System.out.println(juegos[i]);
            }
        }
    }
}

// Clase auxiliar no pública
class JuegoDeMesa {
    private String nombre;
    private double calificacion;
    private String genero;

    public JuegoDeMesa(String nombre, double calificacion, String genero) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public String getGenero() {
        return genero;
    }

    public String toString() {
        return "Nombre: " + nombre + " | Calificación: " + calificacion + " | Género: " + genero;
    }
}
