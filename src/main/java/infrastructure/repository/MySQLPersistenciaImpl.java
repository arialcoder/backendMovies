package infrastructure.repository;

import application.services.IPeliculaPersistencia;
import application.services.IPersistencia;
import domain.models.Pelicula;
import domain.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLPersistenciaImpl implements IPersistencia, IPeliculaPersistencia {

    private Connection conexion;

    public MySQLPersistenciaImpl(){
        this.conexion = DatabaseConnection.getConnection();
    }

    @Override
    public void saveUser(Usuario user) {
        String sql ="INSERT INTO usuarios(username,password,email) values(?,?,?)";
        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1, user.getUsername());
            preparador.setString(2, user.getPassword());
            preparador.setString(3, user.getEmail());

            preparador.executeUpdate();
            preparador.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario finByUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username= ?";
        try {
            PreparedStatement preparador = this.conexion.prepareStatement(sql);
            preparador.setString(1,username);
            ResultSet tablaVirtual = preparador.executeQuery();
            if(tablaVirtual.next()){
                Usuario usuario = new Usuario();

                usuario.setId(tablaVirtual.getInt("id"));
                usuario.setUsername(tablaVirtual.getString("username"));
                usuario.setPassword(tablaVirtual.getString("password"));
                usuario.setEmail(tablaVirtual.getString("email"));

                System.out.println(usuario);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> getAllUsers() {
        String sql = "SELECT * FROM usuarios";
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement preparador = conexion.prepareStatement(sql);
            ResultSet tablaResultado = preparador.executeQuery();

            while(tablaResultado.next()){
                Usuario usuario = new Usuario();

                usuario.setId(tablaResultado.getInt("id"));
                usuario.setUsername(tablaResultado.getString("username"));
                usuario.setPassword(tablaResultado.getString("password"));
                usuario.setEmail(tablaResultado.getString("email"));

                usuarios.add(usuario);
            }
            return usuarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";

        PreparedStatement preparador = null;
        try {
            preparador = this.conexion.prepareStatement(sql);
            preparador.setInt(1,id);
            preparador.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public ArrayList<Pelicula> getAllMovies() {

            String sql = "SELECT * FROM movies";
            ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
            try {
                PreparedStatement preparador = conexion.prepareStatement(sql);
                ResultSet tablaResultado = preparador.executeQuery();

                while(tablaResultado.next()){
                    Pelicula pelicula = new Pelicula();

                    pelicula.setId(tablaResultado.getInt("id"));
                    pelicula.setTitulo(tablaResultado.getString("titulo"));
                    pelicula.setAnio(tablaResultado.getString("anio"));
                    pelicula.setDirector(tablaResultado.getString("director"));
                    pelicula.setGenero(tablaResultado.getString("genero"));
                    peliculas.add(pelicula);
                }

                return peliculas;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public Pelicula findByTitulo(String titulo) {
        String sql = "SELECT * FROM movies WHERE titulo= ?";
            try {
                PreparedStatement preparador = this.conexion.prepareStatement(sql);
                preparador.setString(1,titulo);
                ResultSet tablaVirtual = preparador.executeQuery();
                if(tablaVirtual.next()){
                    Pelicula pelicula = new Pelicula();

                     pelicula.setId(tablaVirtual.getInt("id"));
                    pelicula.setTitulo(tablaVirtual.getString("titulo"));
                    pelicula.setAnio(tablaVirtual.getString("anio"));
                    pelicula.setDirector(tablaVirtual.getString("director"));
                    pelicula.setGenero(tablaVirtual.getString("genero"));

                    System.out.println(pelicula);
                    return pelicula;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
}
