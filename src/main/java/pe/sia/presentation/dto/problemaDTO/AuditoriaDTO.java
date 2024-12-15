package pe.sia.presentation.dto.problemaDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.problema.Auditoria;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditoriaDTO extends EstadoPeticion {
    private Integer idAuditoria;
    private String codigoProblema;
    private String tipoMantenimiento;
    private String fechaRealizada;
    private String personal;
    private Integer idMantenimiento;
    private String fechaCambio;
    private String cambioRealizado;
    private Auditoria auditoria;
    private List<AuditoriaDTO> auditoriaDTOList;
}
