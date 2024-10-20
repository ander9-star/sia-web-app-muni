package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoRedDTO {

    private Long id;

    @NotNull(message = "el nombre no debe de estar vacio")
    private String nombre;

}
