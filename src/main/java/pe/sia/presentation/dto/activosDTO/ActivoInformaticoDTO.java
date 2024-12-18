package pe.sia.presentation.dto.activosDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
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
