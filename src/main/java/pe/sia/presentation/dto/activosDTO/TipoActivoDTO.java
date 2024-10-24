package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoActivoDTO {

    private Integer id;

    @NotNull(message = "el nombre no debe de ser nulo")
    private String nombre;

}
