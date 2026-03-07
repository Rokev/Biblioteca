import java.util.Scanner;

import Biblioteca.Cliente;
import Biblioteca.InventarioLibro;

public class Main {
    public static void main(String[] args) {
        InventarioLibro inventario = new InventarioLibro();
        
        Scanner scanner = new Scanner(System.in);
        int opcion =0;
        while(opcion != 7){
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Realizar préstamo de libro a cliente");
            System.out.println("4. Agregar libro al inventario");
            System.out.println("5. Listar libros");
            System.out.println("6. Realizar devolución de libro por cliente");
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
                    System.out.print("Id del cliente que recibirá el libro: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese datos del libro a prestar:");
                    System.out.print("Identificador: ");
                    int idLib = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Editorial: ");
                    String editorial = scanner.nextLine();
                    System.out.print("Año de publicación: ");
                    int año = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = scanner.nextLine();

                    Libro libro = new Libro(idLib, titulo, autor, editorial, año, categoria, "disponible");
                    inventario.registrarNuevoLibro(libro); // si no está en el inventario
                    boolean prestado = inventario.realizarPrestamoCliente(idCliente, libro);
                    System.out.println(prestado ? "Préstamo registrado." :
                            "No se pudo realizar el préstamo (cliente, libro o estado inválido).");
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
                case 6:
                    System.out.print("Id del cliente que devolverá el libro: ");
                    int idClienteDev = scanner.nextInt();
                    scanner.nextLine();
                    boolean devuelto = inventario.registrarDevolucionCliente(idClienteDev);
                    System.out.println(devuelto ? "Devolución registrada." :
                            "No se pudo registrar la devolución (cliente o estado inválido).");
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
        scanner.close();
    }
}