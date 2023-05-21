package es.ieslavereda.prgproject2223.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private int idOficio;
}
