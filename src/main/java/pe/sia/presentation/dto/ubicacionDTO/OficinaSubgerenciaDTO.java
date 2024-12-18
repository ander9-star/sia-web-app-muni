package pe.sia.presentation.dto.ubicacionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.ubicaciones.OficinaGerencia;
import pe.sia.util.EstadoPeticion;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class OficinaSubgerenciaDTO extends EstadoPeticion {
    private Integer id;
    private String nombreOficina;
    private String oficinaGeren;
    private String oficinaMayor;
    private String ubicacion;
    private Integer cantidadIncidencias;
    private Integer piso;
    private OficinaGerencia oficinaGerencia;
    private List<OficinaSubgerenciaDTO> listOficinas;
}
