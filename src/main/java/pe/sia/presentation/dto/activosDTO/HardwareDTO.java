package pe.sia.presentation.dto.activosDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
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
public class HardwareDTO extends EstadoPeticion {

    private Integer id;

    @NotNull(message = "el nombre del hardware no puede ser nulo")
    private String nombre;

    @NotNull(message =  "la marca no debe de ser nula")
    private String marca;
    
    @NotNull(message = "el modelo no debe de ser nulo")
    private String modelo;

    @NotNull(message = "el proveedor no debe de ser nulo")
    private Integer proveedorId;

    private String nombreProveedor;
    private String nombreEmpleado;
    private Integer cantidadIncidencias;
    private BigDecimal porcentajeIncidencias;
    private List<HardwareDTO> hardwareDTOList;
}
