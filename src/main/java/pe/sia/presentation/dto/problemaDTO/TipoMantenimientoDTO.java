package pe.sia.presentation.dto.problemaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.problema.TipoMantenimiento;
import pe.sia.util.EstadoPeticion;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoMantenimientoDTO extends EstadoPeticion {
    private Integer id;
    private String nombre;
    private TipoMantenimiento tipoMantenimiento;
    private String descripcion;
    private List<TipoMantenimientoDTO> tipoMantenimientoDTOList;
}
