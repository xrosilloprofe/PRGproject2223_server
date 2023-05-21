package es.ieslavereda.prgproject2223.service;

import es.ieslavereda.prgproject2223.model.Oficio;
import es.ieslavereda.prgproject2223.repository.OficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OficioService {

    @Autowired
    private OficioRepository repository;

    public List<Oficio> getOficios() throws SQLException {
        return repository.getOficios();
    }
    public Oficio getOficiobyId(int id) throws SQLException{
        return repository.getOficiobyId(id);
    }

    public String getImage(int id) throws SQLException{
        return repository.getImage(id);
    }

}
