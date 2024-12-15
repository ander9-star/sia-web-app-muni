package pe.sia.presentation.dto.actoresDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.actores.Rol;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO extends EstadoPeticion {
    private Integer id;
    private String nombre;
    private Rol rol;
    private List<Rol> listRol;
}
