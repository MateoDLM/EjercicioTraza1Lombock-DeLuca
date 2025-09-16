package entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Localidad {
    private long id;
    private String nombre;

    private Provincia provincia;
}
