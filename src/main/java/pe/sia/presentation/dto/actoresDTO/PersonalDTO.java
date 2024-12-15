package pe.sia.presentation.dto.actoresDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.actores.Personal;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class PersonalDTO extends EstadoPeticion {

    private Integer idPersonal;
    private String personal;
    private String nombre;
    private String apellidos;
    private String rol;
    private Integer rolId;
    private Integer cantidadMantenimiento;
    private Integer cantidadAuditoria;
    private String estado;
    private Personal personalTI;
    private List<PersonalDTO> personalDTOList;
}
