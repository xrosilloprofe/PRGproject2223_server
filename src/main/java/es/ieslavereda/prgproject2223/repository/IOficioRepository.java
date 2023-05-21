package es.ieslavereda.prgproject2223.repository;

import es.ieslavereda.prgproject2223.model.Oficio;

import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {

    List<Oficio> getOficios() throws SQLException;
    String getImage(int id)  throws SQLException;
    Oficio getOficiobyId(int id) throws SQLException;

}
