package pe.sia.presentation.dto.problemaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.problema.ProblemaGeneral;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemaGeneralDTO extends EstadoPeticion {
    private Integer idProblemaGeneral;
    private String codigoProblemaGeneral;
    private String codigoBien;
    private Integer idActivoMaestro;
    private String nombreActivo;
    private String tipoActivo;
    private String nombreEmpleado;
    private String nombreUsuario;
    private Integer idUsuario;
    private String fechaOcurrencia;
    private Integer cantidadDetalleProblema;
    private ProblemaGeneral problemaGeneral;
    private List<ProblemaGeneralDTO> problemaGeneralDTOList;
}
