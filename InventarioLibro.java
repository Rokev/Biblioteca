package Biblioteca;
import java.util.ArrayList;

public class InventarioLibro {
    ArrayList <Libro> lLibro;
  //  ArrayList <Cliente> lCliente;

  // modificar constructor para agg cliente 
    public InventarioLibro() {
        this.lLibro = new ArrayList();
       // this.lCliente=new ArrayList<>();

    }
    public registrarNuevoLibro(Libro temp){
        for(int i=0;i<lLibro.size();i++){
    if(lLibro.get(i).getIdentificador()==temp.getIdentificador()){
        return false;
    }
        } lLibro.add(temp);
    }



  


}
