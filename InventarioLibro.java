package Biblioteca;
import java.util.ArrayList;

public class InventarioLibro {
    ArrayList <Libro> lLibro;
    ArrayList <Cliente> lCliente;


    public InventarioLibro() {
        this.lLibro = new ArrayList<>();
        this.lCliente=new ArrayList<>();

    }
    public boolean registrarNuevoLibro(Libro temp){
        for(int i=0;i<lLibro.size();i++){
            if(lLibro.get(i).getIdentificador()==temp.getIdentificador()){
                return false;
            } 
    
        }
        lLibro.add(temp);
        return true;
    }

    public boolean registrarNuevoCliente(Cliente cliente){
        for(Cliente c: lCliente){
            if(c.getId()==cliente.getId()){
                return false;
            }
        }
        lCliente.add(cliente);
        return true;
    }

    public void mostrarInfoClientes(){
        for(Cliente c: lCliente){
            c.mostrarInfo();
            System.out.println("");
        }
    }
    
    public boolean buscarLibro(int codigo){
        for(int i=0;i<lLibro.size();i++){
    if(lLibro.get(i).getIdentificador()!=codigo){
        return false;
    } 
}  return true; 
}

public boolean cambiarEstadoPorPrestamo(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
    t.setEstado("prestado a un cliente");
    return true;
    }return false;
}
public boolean cambiarEstadoPorRetiro(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
    t.setEstado("libro retirado");
    return true;
    }return false;
}

public void mostrarEstado(Libro t){
    boolean temp=this.buscarLibro(t.getIdentificador());
    if(temp){
        System.out.println(t.getEstado(), "informacion libro" , ":", t.getTitulo() , t.getAñoPublicacion(), t.getAutor(),t.identificador,t.getCategoria(),t.getEditorial(),t.get);
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
}
