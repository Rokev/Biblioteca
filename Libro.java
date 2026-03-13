public class Libro {

    int identificador;
    String titulo;
    String autor;
    String editorial;
    int añoPublicacion;
    String categoria;
    String estado;
    
    public Libro(int identificador, String titulo, String autor, String editorial, int añoPublicacion, String categoria,
            String estado) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

