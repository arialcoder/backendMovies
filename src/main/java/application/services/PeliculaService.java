package application.services;

import domain.models.Pelicula;

import infrastructure.repository.MySQLPersistenciaImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class PeliculaService implements IPeliculaPersistencia {

    private  IPeliculaPersistencia persistencia= new MySQLPersistenciaImpl();
    private Connection connection;


    @Override
    public void guardarPelicula(Pelicula pelicula) {
        persistencia.guardarPelicula(pelicula);
    }

    @Override
    public ArrayList<Pelicula> getAllMovies() {
        return persistencia.getAllMovies();
    }
    @Override
    public Pelicula findByTitulo(String titulo) {
        return persistencia.findByTitulo(titulo);
    }

    @Override
    public void borrarPelicula(int id) {
        persistencia.borrarPelicula(id);
    }
}
