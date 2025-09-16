package entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Long cuil;
    private String logo;

    //Una empresa tiene muchas sucursales (Set)
    private Set<Sucursal> sucursales = new HashSet<>();
}
