package es.ieslavereda.prgproject2223.repository;

import es.ieslavereda.prgproject2223.model.MyDataSource;
import es.ieslavereda.prgproject2223.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public Usuario getUsuario(int id) throws SQLException {
        Usuario usuario=null;
        String query = "Select * from Usuario where idUsuario = ? ";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario = Usuario.builder().idUsuario(rs.getInt(1)).nombre(rs.getString(2))
                        .apellidos(rs.getString(3))
                        .idOficio(rs.getInt(4))
                        .build();
            }
        }
        return usuario;
    }

    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        Usuario usuari;
        String query = "{call crear_usuario(?,?,?,?,?)}";

        try(Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.registerOutParameter(1,Types.INTEGER);
            cs.setInt(2,usuario.getIdUsuario());
            cs.setString(3,usuario.getNombre());
            cs.setString(4, usuario.getApellidos());
            cs.setInt(5,usuario.getIdOficio());
            cs.execute();

            usuari = getUsuario(cs.getInt(1));
        }
        return usuari;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String query = "{?  = call actualizar_usuario (?,?,?,?)}";

        try(Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setInt(2,usuario.getIdUsuario());
            cs.setString(3,usuario.getNombre());
            cs.setString(4,usuario.getApellidos());
            cs.setInt(5,usuario.getIdOficio());
            cs.execute();
            cs.getInt(1);
        }
        return getUsuario(usuario.getIdUsuario());
    }

    @Override
    public Usuario deleteUsuario(int id) throws SQLException {
        Usuario usuario = getUsuario(id);
        String query = "{?  = call eliminar_usuario(?)}";

        if (usuario!=null) {
            try (Connection connection = MyDataSource.getMyDataSource().getConnection();
                 CallableStatement cs = connection.prepareCall(query)) {
                cs.setInt(2, id);
                cs.execute();
                cs.getInt(1);
            }
        }

        return usuario;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{call obtener_usuarios()}";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){

            ResultSet rs = cs.executeQuery();
            while(rs.next()){
               usuarios.add(Usuario.builder().idUsuario(rs.getInt(1))
                       .nombre(rs.getString(2))
                       .apellidos(rs.getString(3))
                       .idOficio(rs.getInt(4))
                       .build());
            }
        }
        return usuarios;
    }
}
