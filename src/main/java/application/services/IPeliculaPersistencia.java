package application.services;

import domain.models.Pelicula;


import java.util.ArrayList;

public interface IPeliculaPersistencia {

   // void guardarPeliculas(ArrayList<Pelicula> peliculas);

    ArrayList<Pelicula> getAllMovies();
    Pelicula findByTitulo(String titulo);

    //actualizar registro
   // void actualizarTitulo(String tituloNuevo, String anio);

    // Elimina Pelicua segun el ID
   // void borrarPelicula(int id);

}
