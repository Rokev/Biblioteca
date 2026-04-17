import java.util.ArrayList;
public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String dirección;
    private Libro libro;
    private ArrayList<Libro> historialP;
    private int cPrestamos;
    private double multaPendiente;
    private ArrayList<Prestamo> historialPrestamos;


    public Cliente(int id, String nombre, String telefono, String dirección, Libro libro) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dirección = dirección;
        this.libro = libro;
	this.historialP=new ArrayList<>();
    this.cPrestamos=this.cPrestamos();
        this.historialP=new ArrayList<>();
        this.multaPendiente=0;
        this.historialPrestamos=new ArrayList<>();
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getDirección() {
        return dirección;
    }


    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public Libro getLibro() {
        return libro; 
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String mostrarInfo(){
        String tieneLibro = (this.libro!=null) ? "Si tiene libro": "No tiene libro";
        return "Cliente: " + nombre +  ", Id:" + id + ", Teléfono: " + telefono + ", Dirección: " + dirección + ", " + tieneLibro;
    }

    

    public void setHistorialP(ArrayList<Libro> historialP) {
        this.historialP = historialP;
    }


    public int getcPrestamos() {
        return cPrestamos;
    }


    public void setcPrestamos(int cPrestamos) {
        this.cPrestamos = cPrestamos;
    }


    public boolean recibirLibro(Libro libro){
        if(this.libro == null && libro !=null){
            this.libro = libro;
            historialP.add(libro);
            return true;
        }
        return false;
    }

    public boolean devolverLibro(){
        if(this.libro != null){
            this.libro = null;
            return true;
        }
        return false; 
    }

public ArrayList<Libro> getHistorialP(){
        return historialP;
}
 
public int cPrestamos(){
    if(recibirLibro(libro)){
        cPrestamos++;
        return cPrestamos;
    }  return 0;

}





public double getMultaPendiente() {
    return multaPendiente;
}

public void setMultaPendiente(double multaPendiente) {
    this.multaPendiente = multaPendiente;
}

public void agregarMulta(double cantidad) {
    this.multaPendiente += cantidad;
}

public void pagarMulta(double cantidad) {
    if (cantidad <= multaPendiente) {
        this.multaPendiente -= cantidad;
    } else {
        System.out.println("La cantidad a pagar excede la multa pendiente.");
    }
}

public ArrayList<Prestamo> getHistorialPrestamos() {
    return historialPrestamos;
}

public void agregarPrestamo(Prestamo prestamo) {
    this.historialPrestamos.add(prestamo);
}

}
