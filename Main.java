import java.util.Scanner;

import Biblioteca.Cliente;
import Biblioteca.InventarioLibro;

public class Main {
    public static void main(String[] args) {
        InventarioLibro inventario = new InventarioLibro();5
        
        Scanner scanner = new Scanner(System.in);
        int opcion =0;
        while(opcion != 7){
            System.out.println("1. Agregar cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Agregar libro a cliente");
            System.out.println("4. agregar libro  ");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa los datos del nuevo cliente");
                    System.out.print("Id cliente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();
                    Cliente nuevo = new Cliente(id, nombre, telefono, direccion, null);
                    if (inventario.registrarNuevoCliente(nuevo)) {
                        System.out.println("Cliente agregado.");
                    } else {
                        System.out.println("Ya existe un cliente con ese id.");
                    }
                    break;
                case 2:
                    inventario.mostrarInfoClientes();
                    break;
                case 3:
                    // Lógica para agregar libro a cliente
                    break;
                    case 4:
                System.out.println("Ingrese datos del libro a registrar en el inventario:");
                    System.out.print("Identificador: ");
                    int idNuevoLib = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Título: ");
                    String t = scanner.nextLine();
                    System.out.print("Autor: ");
                    String a = scanner.nextLine();
                    System.out.print("Editorial: ");
                    String e = scanner.nextLine();
                    System.out.print("Año publicación: ");
                    int y = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoría: ");
                    String c = scanner.nextLine();
                    Libro lNuevo = new Libro(idNuevoLib, t, a, e, y, c, "disponible");
                    if (inventario.registrarNuevoLibro(lNuevo)) {
                        System.out.println("Libro agregado al inventario.");
                    } else {
                        System.out.println("Ya existe un libro con ese identificador.");
                    }
                    break;
                case 5:
                inventario.listarLibros();
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
        scanner.close();
    }
}