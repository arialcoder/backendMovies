package application.services;

import domain.models.Pelicula;

import infrastructure.repository.MySQLPersistenciaImpl;

import java.sql.Connection;
import java.util.ArrayList;

public class PeliculaService implements IPeliculaPersistencia {

    private  IPeliculaPersistencia persistencia= new MySQLPersistenciaImpl();
    private Connection connection;


    @Override
    public ArrayList<Pelicula> getAllMovies() {
        return persistencia.getAllMovies();
    }
    @Override
    public Pelicula findByTitulo(String titulo) {
        return persistencia.findByTitulo(titulo);
    }
}
