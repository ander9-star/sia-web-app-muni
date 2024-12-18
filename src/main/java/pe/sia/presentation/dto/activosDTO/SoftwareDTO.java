package pe.sia.presentation.dto.activosDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pe.sia.util.EstadoPeticion;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDTO extends EstadoPeticion {

    private Integer id;

    @NotNull(message = "el nombre no debe de estar vacio")
    private String nombre;

    @NotNull(message = "la versión del software no debe de estar vacia")
    private String version;

    @NotNull(message = "la fecha de instalación no debe de estar vacia")
    private LocalDate fechaInstalacion;

    private LocalDate fechaVencimientoLicencia;

    @NotNull(message = "el proveedor no debe de estar vacio")
    private Integer proveedorId;

    private String nombreProveedor;
    private String nombreEmpleado;
    private Integer cantidadIncidencias;
    private BigDecimal porcentajeIncidencias;
    private List<SoftwareDTO> softwareDTOList;
}
