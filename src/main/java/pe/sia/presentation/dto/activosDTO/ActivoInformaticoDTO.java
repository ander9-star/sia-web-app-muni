package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivoInformaticoDTO {
    
    private Integer id;
    
    @NotNull(message = "La orden de compra no puede ser nulo")
    private String ordenCompra;

    @NotNull(message = "El tipo de activo no puede ser nulo")
    private String tipoActivo;

    @NotNull(message = "El hardware no puede ser nulo")
    private Integer hardwareId;

    @NotNull(message = "El software no puede ser nulo")
    private Integer softwareId;

    @NotNull(message = "La red no puede ser nula")
    private Integer infraestructuraRedId;
}
