package es.ieslavereda.prgproject2223.repository;

import es.ieslavereda.prgproject2223.model.Oficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioRepository implements IOficioRepository{

    @Autowired
    DataSource dataSource;

    @Override
    public List<Oficio> getOficios()  throws SQLException {
        List<Oficio> oficios = new ArrayList<>();
        String query = "{call obtener_oficios(?)}";

        try(Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setNull(1,0);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                oficios.add(Oficio.builder().id(rs.getInt(1))
                        .descripcion(rs.getString(2))
                        .imageurl(rs.getString(4))
                        .build());
            }
        }
        return oficios;
    }

    @Override
    public String getImage(int id)  throws SQLException {
        String image;
        String query = "{call obtener_image_oficio(?,?)}";

        try(Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.registerOutParameter(1, Types.BLOB);
            cs.setInt(2,id);
            cs.execute();
            byte[] imageBytes = cs.getBytes(1);
            image = new String(imageBytes,"ISO_8859_1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public Oficio getOficiobyId(int id) throws SQLException {
        Oficio oficio = null;
        String query = "{call obtener_oficios(?)}";

        try(Connection connection = dataSource.getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setInt(1,id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
                oficio = Oficio.builder().id(rs.getInt(1))
                        .descripcion(rs.getString(2))
                        .imageurl(rs.getString(4))
                        .build();
            }
        return oficio;
    }

}
