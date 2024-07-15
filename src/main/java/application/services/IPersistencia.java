package application.services;

import domain.models.Usuario;

import java.util.ArrayList;

public interface IPersistencia {

    // aca van los metodos abstractos del CRUD

    void saveUser(Usuario user );
    Usuario finByUsername(String username);
    ArrayList<Usuario> getAllUsers();
    void deleteUser(int id);


}
