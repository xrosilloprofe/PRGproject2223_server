package es.ieslavereda.prgproject2223.controller;

import es.ieslavereda.prgproject2223.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OficioController {
    @Autowired
    private OficioService service;

    @GetMapping("/oficios/{id}")
    public ResponseEntity<?> getOficiobyId(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(service.getOficiobyId(id), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(service.getImage(id), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/oficios")
    public ResponseEntity<?> getOficios() {
        try{
            return new ResponseEntity<>(service.getOficios(),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
