import java.util.Scanner;

import Biblioteca.InventarioLibro;

public class Main {
    public static void main(String[] args) {
        InventarioLibro inventario = new InventarioLibro();
        Scanner scanner = new Scanner(System.in);
        int opcion =0;
        while(opcion != 7){
            System.out.println("1. Agregar cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Agregar libro a cliente");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa los datos del nuevo cliente");
                    break;
                case 2:
                    inventario.mostrarInfoClientes();
                    break;
                case 3:
                    // Lógica para agregar libro a cliente
                    break;
                    case 4:
                    // Lógica 
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
        scanner.close();
    }
}