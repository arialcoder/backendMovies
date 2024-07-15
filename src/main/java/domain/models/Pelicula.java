package domain.models;

public class Pelicula {
    private int id;
    private String titulo;
    private String anio;
    private String director;
    private String genero;

    //Constructors
    public Pelicula() {}

    public Pelicula(String titulo, String anio, String director,String genero) {
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", director='" + director + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

//Getters Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
