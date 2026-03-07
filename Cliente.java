package Biblioteca;
public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String dirección;
    private Libro libro;


    public Cliente(int id, String nombre, String telefono, String dirección,Libro libro) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dirección = dirección;
        this.libro = libro;
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
        return "Cliente: " + nombre + ", Teléfono: " + telefono + ", Dirección: " + dirección + ", " + tieneLibro;
    }

    public boolean recibirLibro(Libro libro){
        if(this.libro == null && libro !=null){
            this.libro = libro;
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
}