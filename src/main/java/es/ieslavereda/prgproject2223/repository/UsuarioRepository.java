package es.ieslavereda.prgproject2223.repository;

import es.ieslavereda.prgproject2223.model.MyDataSource;
import es.ieslavereda.prgproject2223.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements IUsuarioRepository {


    @Override
    public Usuario getUsuario(int id) throws SQLException {
        Usuario usuario=null;
        String query = "Select * from Usuario where idUsuario = ? ";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
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
        Usuario usuari=null;
        String query = "{call crear_usuario(?,?,?,?,?)}";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.registerOutParameter(1,Types.INTEGER);
            cs.setInt(2,usuario.getIdUsuario());
            cs.setString(3,usuario.getNombre());
            cs.setString(4, usuario.getApellidos());
            cs.setInt(5,usuario.getIdOficio());
            cs.execute();
            usuari = Usuario.builder().idUsuario(cs.getInt(1))
                    .nombre(usuario.getNombre())
                    .apellidos(usuario.getApellidos())
                    .idOficio(usuario.getIdOficio())
                    .build();
        }
        return usuari;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String query = "{?  = call actualizar_usuario (?,?,?,?)}";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setInt(2,usuario.getIdUsuario());
            cs.setString(3,usuario.getNombre());
            cs.setString(4,usuario.getApellidos());
            cs.setInt(5,usuario.getIdOficio());
            cs.execute();
            int numActualizados = cs.getInt(1);
        }
        return usuario;
    }

    @Override
    public boolean deleteUsuario(int id) throws SQLException {
        boolean borrado=false;
        int numBorrados = 0;
        String query = "{?  = call eliminar_usuario(?)}";

        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setInt(2,id);
            cs.execute();
            numBorrados = cs.getInt(1);
            if (numBorrados==1)
                borrado = true;
        }
        return borrado;
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