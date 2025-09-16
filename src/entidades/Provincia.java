package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Provincia {
    private Long id;
    private String nombre;

    private Pais pais;

}
