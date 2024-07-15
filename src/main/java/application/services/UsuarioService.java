package application.services;

import domain.models.Usuario;
import infrastructure.repository.MySQLPersistenciaImpl;

import java.util.ArrayList;

public class UsuarioService implements IPersistencia {
    private IPersistencia persistencia= new MySQLPersistenciaImpl();

    @Override
    public ArrayList<Usuario> getAllUsers() {
        return persistencia.getAllUsers();
    }

    @Override
    public void deleteUser(int id) {
        persistencia.deleteUser(id);
    }

    @Override
    public Usuario finByUsername(String username) {
        return persistencia.finByUsername(username);
    }

    @Override
    public void saveUser(Usuario user) {
        persistencia.saveUser(user);
    }
}
