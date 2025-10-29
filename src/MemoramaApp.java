import java.util.Scanner;
import java.util.Random;

public class MemoramaApp {

    Scanner sc = new Scanner(System.in);
    char[][] tablero = new char[4][4];        // tablero real con los símbolos
    boolean[][] descubierto = new boolean[4][4]; // qué casillas están descubiertas
    int movimientos = 0;

    public static void main(String[] args) {
        MemoramaApp app = new MemoramaApp();
        app.iniciar();
    }


    public void iniciar() {
        inicializarTablero();
        while (!juegoTerminado()) {
            imprimirTablero();
            jugarTurno();
        }
        System.out.println("¡Felicidades! Completaste el memorama.");
        System.out.println("Movimientos realizados: " + movimientos);
    }

    public void inicializarTablero() {
        char[] simbolos = {'A','B','C','D','E','F','G','H'};
        char[] pares = new char[16];
        for (int i = 0; i < 8; i++) {
            pares[i * 2] = simbolos[i];
            pares[i * 2 + 1] = simbolos[i];
        }
        System.out.println("\n ===Bienvenido al juego de Memorama, para comenzar: === ");

        // Mezclar los símbolos aleatoriamente
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            int j = rand.nextInt(16);
            char temp = pares[i];
            pares[i] = pares[j];
            pares[j] = temp;
        }

        // Asignar al tablero 4x4
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tablero[i][j] = pares[k];
                descubierto[i][j] = false;
                k++;
            }
        }
    }

    public void imprimirTablero() {
        System.out.println("\n    0 1 2 3");
        System.out.println("   ---------");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 4; j++) {
                if (descubierto[i][j]) {
                    System.out.print(tablero[i][j] + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public void jugarTurno() {
        int fila1, col1, fila2, col2;

        // Primer símbolo
        System.out.println("Seleccion de la primera casilla:");
        fila1 = leerCoordenada("Elige una opcion horizontal, 0-3: ");
        col1 = leerCoordenada("Escoje una opcion vertical, 0-3: ");
        if (!descubierto[fila1][col1]) {
            descubierto[fila1][col1] = true;
        }
        imprimirTablero();

        // Segundo símbolo
        System.out.println("Selecciona la segunda casilla y adivina:");
        fila2 = leerCoordenada("Elige una opcion horizontal, (0-3): ");
        col2 = leerCoordenada("Escoje una opcion vertical, (0-3): ");
        if (!descubierto[fila2][col2]) {
            descubierto[fila2][col2] = true;
        }
        imprimirTablero();

        // Para incrementar movimientos usamos:
        movimientos++;

        // Verificar coincidencia
        if (tablero[fila1][col1] == tablero[fila2][col2]) {
            System.out.println("¡Par correcto!");
        } else {
            System.out.println("No coincide.");
            // Esperar para que el jugador vea los símbolos
            System.out.println("Presiona Enter para continuar con el juego");
            sc.nextLine();
            // Para volver a cubrir
            descubierto[fila1][col1] = false;
            descubierto[fila2][col2] = false;
        }
    }

    public int leerCoordenada(String mensaje) {
        int valor = -1;
        while (valor < 0 || valor > 3) {
            System.out.print(mensaje);
            valor = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            if (valor < 0 || valor > 3) {
                System.out.println("Coordenada inválida o no registrada. Intenta de nuevo.");
            }
        }
        return valor;
    }

    public boolean juegoTerminado() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!descubierto[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
