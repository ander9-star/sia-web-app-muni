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
public class ProveedorDTO extends EstadoPeticion {

    private Integer id;

    @NotNull(message = "el nombre no debe de ser nulo")
    private String nombre;

    private String contacto;

    @Pattern(regexp = "^[0-9\\-]+$", message = "El teléfono es incorrecto")
    private String telefono;

    private Integer cantidadIncidencias;
    private BigDecimal porcentajeIncidencia;
    private List<ProveedorDTO> proveedorDTOList;
}
