package entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "localidad")  // Excluir localidad para evitar recursión infinita
@Builder

public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;

    private Localidad localidad;
}
