package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ProveedorDTO {

    private Integer id;

    @NotNull(message = "el nombre no debe de ser nulo")
    private String nombre;

    private String contacto;

    @Pattern(regexp = "^[0-9\\-]+$", message = "El teléfono es incorrecto")
    private String telefono;
}
