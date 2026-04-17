import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.File;


public class InventarioLibro {
    ArrayList <Libro> lLibro;
    ArrayList <Cliente> lCliente;
    private static final String LIBROS_FILE = "data/libros.txt";
    private static final String CLIENTES_FILE = "data/clientes.txt";
    private int sizeL;
    private int sizec;
    private int sizeph;


    public InventarioLibro() {
        this.lLibro = new ArrayList<>();
        this.lCliente=new ArrayList<>();
        this.sizeL=0;
        this.sizec=0;
        this.sizeph=0;
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        cargarLibros();
        cargarClientes();
    }
    public boolean registrarNuevoLibro(Libro temp){
        for(int i=0;i<lLibro.size();i++){
            if(lLibro.get(i).getIdentificador()==temp.getIdentificador()){
                return false;
            } 
    
        }
        lLibro.add(temp);
        sizeL++;
        guardarLibros();
        return true;
    }

    public boolean registrarNuevoCliente(Cliente cliente){
        for(Cliente c: lCliente){
            if(c.getId()==cliente.getId()){
                return false;
            }
        }
        lCliente.add(cliente);
        sizec++;
        guardarClientes();
        return true;
    }

    public void mostrarInfoClientes(){
        for(Cliente c: lCliente){
            String x =c.mostrarInfo();
            System.out.println(x);
        }
    }
    
    public boolean buscarLibro(int codigo){
        for(int i=0;i<lLibro.size();i++){
            if(lLibro.get(i).getIdentificador()==codigo){
                return true;
            }
        }
        return false;
    }
    public Libro buscarLibroInventario(int codigo){
        for (Libro l : lLibro) {
            if(l.getIdentificador() == codigo) {
                return l;
            }   
        }
        return null;
    }

public boolean cambiarEstadoPorPrestamo(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
    t.setEstado("prestado a un cliente");
    guardarLibros();
    return true;
    }return false;
}
public boolean cambiarEstadoPorRetiro(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
    t.setEstado("libro retirado");
    guardarLibros();
    return true;
    }return false;
}

public void mostrarEstado(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
        System.out.println( "informacion libro"  + ":" +  t.getTitulo() + t.getAñoPublicacion() + t.getAutor() + t.getIdentificador() + t.getCategoria() + t.getEditorial() + t.getEstado());
    } System.out.println("no se enceuntra dentro del inventario");

}

    public Cliente buscarCliente(int id){
        for (Cliente c: lCliente){
            if(c.getId()==id){
                return c;
            }
        }
        return null;
    }
    public boolean realizarPrestamoCliente(int idCliente, Libro libro) {
        int contador=0;
        Cliente c = buscarCliente(idCliente);
        if (c == null || libro == null) {
            return false;
        }
        if (c.getLibro() != null) {  //ya tiene un libro
            return false;
        }
        if (!buscarLibro(libro.getIdentificador())) { //el libro no existe en el inventario
            return false;
        }
        boolean ok = c.recibirLibro(libro);
        if (ok) {
            cambiarEstadoPorPrestamo(libro);
            sizeph++;
            contador++;
            libro.setSolicitud(contador);
            // Crear registro de préstamo con fecha límite de 14 días
            LocalDateTime fechaEntrega = LocalDateTime.now();
            LocalDateTime fechaLimite = fechaEntrega.plusDays(-2);
            Prestamo prestamo = new Prestamo(fechaEntrega, fechaLimite);
            c.agregarPrestamo(prestamo);
        }
        return ok;
    }
    
    public boolean registrarDevolucionCliente(int idCliente) {
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            return false;
        }
        Libro l = c.getLibro();
        if (l == null) {// ningún libro que devolver
            return false;
        }
        boolean ok = c.devolverLibro();
        if (ok) {
            l.setEstado("disponible");
            guardarLibros();
            // Calcular multa por retraso si aplica
            if (!c.getHistorialPrestamos().isEmpty()) {
                Prestamo ultimoPrestamo = c.getHistorialPrestamos().get(c.getHistorialPrestamos().size() - 1);
                ultimoPrestamo.calcularMulta(LocalDateTime.now());
                
                if (ultimoPrestamo.tieneRetraso()) {
                    c.agregarMulta(ultimoPrestamo.getMulta());
                    guardarClientes();
                }
            }
        }
        return ok;
    }

    public void listarLibros() {
    for (Libro libro : lLibro) {
        System.out.println("ID: " + libro.identificador);
        System.out.println("Titulo: " + libro.titulo);
        System.out.println("Autor: " + libro.autor);
        System.out.println("Editorial: " + libro.editorial);
        System.out.println("Año: " + libro.añoPublicacion);
        System.out.println("Categoria: " + libro.categoria);
        System.out.println("Estado: " + libro.estado);
        System.out.println("----------------------");
    }
}

public void mostrarHistorial(int id){
        Cliente c=buscarCliente(id);
	if(c==null){
           System.out.println("no existe el cliente");
		    return;
	}

	if(c.getHistorialP().isEmpty()){
	   System.out.println("el cliente no tiene prestamos registrados");
			return;
	}

        System.out.println("historial de prestamos del cliente"+c.getNombre());
	 for(Libro l:c.getHistorialP()){
	    System.out.println(l.getTitulo() + l.getIdentificador());
	 }
} 
 public ArrayList<Libro> buscarLibrosPorTitulo(String texto){
        ArrayList<Libro> resultados = new ArrayList<>();
        String busqueda=texto.toLowerCase();
        for(Libro l : lLibro){
           if(l.getTitulo().toLowerCase().contains(busqueda)){
              resultados.add(l);
           }
        }
        return resultados;
   }

   public ArrayList<Libro> buscarLibrosPorAutor(String texto){
        ArrayList<Libro> resultados = new ArrayList<>();
        String busqueda = texto.toLowerCase();
        for(Libro l : lLibro){
            if(l.getAutor().toLowerCase().contains(busqueda)){
                resultados.add(l);
            }
        }
        return resultados;
    }

    public ArrayList<Libro> buscarLibrosPorCategoria(String texto){
        ArrayList<Libro> resultados = new ArrayList<>();
        String busqueda = texto.toLowerCase();
        for(Libro l : lLibro){
            if(l.getCategoria().toLowerCase().contains(busqueda)){
                resultados.add(l);
            }
        }
        return resultados;
   }
   
    
    public String obtenerClienteConMasPrestamos() {
        if (lCliente == null || lCliente.isEmpty()) {
            return null;  // o lanzar una excepción si se prefiere
        }

        Cliente clienteMax = lCliente.get(0);
        for (Cliente c : lCliente) {
            if (c.getcPrestamos() > clienteMax.getcPrestamos()) {
                clienteMax = c;
            }
        }
        return clienteMax.getNombre();
    }
    public String obtenerLibroConMenosSolicitudes() {
        if (lLibro == null || lLibro.isEmpty()) {
            return null;  // o lanzar excepción
        }

        Libro libroMin = lLibro.get(0);
        for (Libro lib : lLibro) {
            if (lib.getSolicitud() < libroMin.getSolicitud()) {
                libroMin = lib;
            }
        }
        return libroMin.getTitulo();

    }

    public int contarClientesConPrestamoActivo() {
    int contador = 0;
    for (Cliente cliente : lCliente) {
        if (cliente.getLibro() != null) {
            contador++;
        }
    }
    return contador;
}

public void mostrarModulo(){
    System.out.println("total libros:" + this.sizeL);
    System.out.println("total clientes:" + this.sizec);
    System.out.println("total prestamos activos:" + this.contarClientesConPrestamoActivo());
    System.out.println("total prestamos historicos:" + this.sizeph);
    System.out.println("cliente con mayor numero de prestamos:" + this.obtenerClienteConMasPrestamos());
    System.out.println("libro menos solicitado:" + this.obtenerLibroConMenosSolicitudes());
}

    public void mostrarMultasCliente(int idCliente) {
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.println("\n========== MULTAS DEL CLIENTE ==========");
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("ID: " + c.getId());
        System.out.println("Multa Pendiente Total: $" + String.format("%.2f", c.getMultaPendiente()));
        
        if (c.getHistorialPrestamos().isEmpty()) {
            System.out.println("\nEl cliente no tiene registros de préstamos.");
        } else {
            System.out.println("\n--- Historial de Préstamos y Multas ---");
            int contador = 1;
            for (Prestamo p : c.getHistorialPrestamos()) {
                System.out.println("\nPréstamo #" + contador);
                System.out.println("Fecha de Entrega: " + p.getFechaEntrega());
                System.out.println("Fecha Límite: " + p.getFechaLimiteDevolucion());
                
                if (p.getFechaDevolucion() != null) {
                    System.out.println("Fecha de Devolución: " + p.getFechaDevolucion());
                    if (p.tieneRetraso()) {
                        System.out.println("Días de Retraso: " + p.getDiasRetraso());
                        System.out.println("Multa: $" + String.format("%.2f", p.getMulta()));
                    } else {
                        System.out.println("Estado: Devuelto a Tiempo");
                    }
                } else {
                    System.out.println("Estado: Préstamo Activo (libro no devuelto)");
                }
                contador++;
            }
        }
        System.out.println("\n========================================");
    }

    public boolean pagarMultaCliente(int idCliente, double monto) {
        Cliente c = buscarCliente(idCliente);
        if (c == null) {
            System.out.println("Cliente no encontrado");
            return false;
        }

        if (c.getMultaPendiente() == 0) {
            System.out.println("El cliente no tiene multas pendientes.");
            return false;
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a 0");
            return false;
        }

        if (monto > c.getMultaPendiente()) {
            System.out.println("El monto supera la multa pendiente de $" + String.format("%.2f", c.getMultaPendiente()));
            return false;
        }

        c.pagarMulta(monto);
        guardarClientes();
        System.out.println("Pago registrado. Multa pendiente: " + String.format("%.2f", c.getMultaPendiente()));
        return true;
    }

    private void guardarLibros() {
        java.util.List<String> lines = new java.util.ArrayList<>();
        for (Libro l : lLibro) {
            lines.add(l.getIdentificador() + "," + l.getTitulo() + "," + l.getAutor() + "," + l.getEditorial() + "," + l.getAñoPublicacion() + "," + l.getCategoria() + "," + l.getEstado());
        }
        FileUtil.writeFile(LIBROS_FILE, lines);
    }

    private void cargarLibros() {
        java.util.List<String> lines = FileUtil.readFile(LIBROS_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    String titulo = parts[1];
                    String autor = parts[2];
                    String editorial = parts[3];
                    int año = Integer.parseInt(parts[4]);
                    String categoria = parts[5];
                    String estado = parts[6];
                    Libro l = new Libro(id, titulo, autor, editorial, año, categoria, estado);
                    lLibro.add(l);
                } catch (NumberFormatException e) {
                    // Ignorar líneas mal formateadas
                }
            }
        }
    }

    private void guardarClientes() {
        java.util.List<String> lines = new java.util.ArrayList<>();
        for (Cliente c : lCliente) {
            lines.add(c.getId() + "," + c.getNombre() + "," + c.getTelefono() + "," + c.getDirección() + "," + c.getMultaPendiente());
        }
        FileUtil.writeFile(CLIENTES_FILE, lines);
    }

    private void cargarClientes() {
        java.util.List<String> lines = FileUtil.readFile(CLIENTES_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                try {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String telefono = parts[2];
                    String direccion = parts[3];
                    double multa = Double.parseDouble(parts[4]);
                    Cliente c = new Cliente(id, nombre, telefono, direccion, null);
                    c.setMultaPendiente(multa);
                    lCliente.add(c);
                } catch (NumberFormatException e) {
                    // Ignorar
                }
            }
        }
    }
}
