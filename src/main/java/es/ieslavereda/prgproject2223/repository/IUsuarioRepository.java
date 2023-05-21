package es.ieslavereda.prgproject2223.repository;

import es.ieslavereda.prgproject2223.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {
    Usuario getUsuario(int id) throws SQLException;
    Usuario addUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
    boolean deleteUsuario(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;

}
