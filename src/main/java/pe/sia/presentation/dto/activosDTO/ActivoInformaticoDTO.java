package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivoInformaticoDTO {
    
    private Integer id;
    
    @NotNull(message = "El tipo de activo no puede ser nulo")
    private Integer tipoActivoId;

    @NotNull(message = "El empleado no puede ser nulo")
    private Integer empleadoId;

    @NotNull(message = "El hardware no puede ser nulo")
    private Long hardwareId;

    @NotNull(message = "El software no puede ser nulo")
    private Long softwareId;

    @NotNull(message = "La red no puede ser nula")
    private Long redId;
}
