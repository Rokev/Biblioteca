package Biblioteca;
import java.util.ArrayList;

public class InventarioLibro {
    ArrayList <Libro> lLibro;
    ArrayList <Cliente> lCliente;

  // modificar constructor para agg cliente 
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
    
}
