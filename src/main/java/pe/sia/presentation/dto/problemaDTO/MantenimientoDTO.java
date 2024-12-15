package pe.sia.presentation.dto.problemaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.problema.Mantenimiento;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoDTO extends EstadoPeticion {
    private Integer idMantenimiento;
    private Integer idDetalleProblema;
    private String codigoProblema;
    private String fechaProgramada;
    private String tipoMantenimiento;
    private String personal;
    private String nombreActivo;
    private String descripcion;
    private String fechaRegistro;
    private String fechaRealizada;
    private Integer tipoMantenimientoId;
    private Integer personalId;
    private String notas;
    private Integer cantidadAuditoria;
    private Mantenimiento mantenimiento;
    private List<MantenimientoDTO> listaMantenimiento;
}
