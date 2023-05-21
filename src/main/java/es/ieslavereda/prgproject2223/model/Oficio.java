package es.ieslavereda.prgproject2223.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Oficio {
    private int id;
    private String descripcion;
    private String image;
    private String imageurl;
}
