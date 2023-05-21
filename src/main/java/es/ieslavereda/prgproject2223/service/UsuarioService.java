package es.ieslavereda.prgproject2223.service;

import es.ieslavereda.prgproject2223.model.Usuario;
import es.ieslavereda.prgproject2223.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario getUsuario(int id) throws SQLException {
        return repository.getUsuario(id);
    }

    public Usuario addUsuario(Usuario usuario) throws SQLException {
        return repository.addUsuario(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) throws SQLException{
     return repository.updateUsuario(usuario);
    }
    public boolean deleteUsuario(int id) throws SQLException {
        return repository.deleteUsuario(id);
    }
    public List<Usuario> getAllUsuarios() throws SQLException {
        return repository.getAllUsuarios();
    }

}
