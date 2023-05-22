package es.ieslavereda.prgproject2223.controller;

import es.ieslavereda.prgproject2223.model.Usuario;
import es.ieslavereda.prgproject2223.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(service.getUsuario(id),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/usuarios/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try{
            return new ResponseEntity<>(service.addUsuario(usuario),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        try{
            return new ResponseEntity<>(service.updateUsuario(usuario),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id) {
        try{
//            if (service.deleteUsuario(id))
//                return new ResponseEntity<>("Borrado",HttpStatus.OK);
//            else
//                return new ResponseEntity<>("No borrado",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(service.deleteUsuario(id),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/")
    public ResponseEntity<?> getAllUsuarios() {
        try{
            return new ResponseEntity<>(service.getAllUsuarios(),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
