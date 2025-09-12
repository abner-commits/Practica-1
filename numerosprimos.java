import java.util.Scanner; 
public class numerosprimos {
  public static void main(String[] args) {
   Scanner scanner = new Scanner(System.in); 
   int eleccion;
   do {
     System.out.print("Bienvenido, escoge un número");
     eleccion = scanner.nextInt();
     if (eleccion == 0 || eleccion == 1) {
     System.out.println("error, escoge otro número distinto de 0 y 1"); 
   }
  } while (eleccion == 0 || eleccion == 1); 
  System.out.println("Vamos a hacer la suma de la evaluación de 2x+1 hasta: " + eleccion); 
  SumaN suma = new SumaN(1, eleccion);
  int resultado = suma.evalua(2);
  
  System.out.println("La suma total es: " + resultado);
  //Ahora veremos si el resultado obtenido es primo o no
  Primo verificador = new Primo();
  if (verificador.esPrimo(resultado)) {
    System.out.println("El resultado es un número primo");
  } else {
     System.out.println("El resultado no es primo");
  }

  scanner.close();
 }
}