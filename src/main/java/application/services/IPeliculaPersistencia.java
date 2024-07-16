package application.services;

import domain.models.Pelicula;


import java.util.ArrayList;

public interface IPeliculaPersistencia {

    void guardarPelicula(Pelicula pelicula);

    ArrayList<Pelicula> getAllMovies();
    Pelicula findByTitulo(String titulo);

    //actualizar registro
   // void actualizarTitulo(String tituloNuevo, String anio);

    // Elimina Pelicua segun el ID
    void borrarPelicula(int id);

}
