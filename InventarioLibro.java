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
    
}lLibro.add(temp);
    return true;

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
        System.out.println(t.getEstado());
    } System.out.println("no se enceuntra dentro del inventario");

}

}
