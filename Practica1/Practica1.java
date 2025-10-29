import java.util.Random;
import java.util.Scanner;

public class Practica1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); //Con este comando la computadora elige aleatoriamente

        int victoriasJugador = 0;
        int victoriasComputadora = 0;

        System.out.println("Bienvenido, jugaremos piedra, papel o tijeras");

        for (int ronda = 1; ronda <= 3; ronda++) {
            System.out.println("\nRonda " + ronda + ":");
            System.out.println("Elige una opción");
            System.out.println("1 - Tijeras");
            System.out.println("2 - Papel");
            System.out.println("3 - Piedra");

            int eleccion = scanner.nextInt();

            int eleccionComputadora = random.nextInt(3) + 1; //Acotamos el número que la computadora puede elegir

            System.out.println("Elegiste: " + eleccion);
            System.out.println("La computadora eligió: " + eleccionComputadora);

            if (eleccion == eleccionComputadora) {
                System.out.println("Empate");
            } else if (
                (eleccion == 1 && eleccionComputadora == 2) || //Escogimos tijeras y la computadora papel, tijeras gana a papel//Hacemos las comparaciones respectivas
                (eleccion == 2 && eleccionComputadora == 3) || //Escogimos papel y la computadora pierda, gana papel
                (eleccion == 3 && eleccionComputadora == 1)    //Escogimos piedra y la computadora tijeras, gana piedra 
            ) {
                System.out.println("Ganaste esta ronda");
                victoriasJugador++;
            } else {
                System.out.println("Perdiste esta ronda");
                victoriasComputadora++;
            }
        }

        // Mostrar resultado final
        System.out.println("\nResultado final:");
        System.out.println("Victorias del jugador: " + victoriasJugador);
        System.out.println("Victorias de la computadora: " + victoriasComputadora); //Mostramos quién gana la partida

        if (victoriasJugador > victoriasComputadora) {
            System.out.println("¡Ganaste el juego!");
        } else if (victoriasComputadora > victoriasJugador) {
            System.out.println("La computadora ganó el juego.");
        }
        scanner.close();
    }
}
