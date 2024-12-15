package pe.sia.presentation.dto.activosDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import pe.sia.util.EstadoPeticion;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class InfraestructuraRedDTO extends EstadoPeticion {
    
    private Integer id;
    
    @NotNull(message = "El tipo de red no puede ser nulo")
    private Integer tipoRedId;

    @NotNull(message = "La dirección IP no puede ser nula")
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", 
             message = "La dirección IP debe de ser IPv4")
    private String direccionIP;
    
    @NotNull(message = "El proveedor no puede ser nulo")
    private Integer proveedorId;

    private String tipRed;
    private String nombreProveedor;
    private String nombreEmpleado;
    private String oficinaGerencia;
    private Integer piso;
    private Integer cantidadIncidencias;
    private BigDecimal porcentajeIncidencias;
    private List<InfraestructuraRedDTO> infraestructuraRedDTOList;

}
