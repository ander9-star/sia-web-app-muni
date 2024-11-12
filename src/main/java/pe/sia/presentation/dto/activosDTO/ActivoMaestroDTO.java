package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.sia.persistence.entity.actores.Empleado;

@Data
public class ActivoMaestroDTO {

    
    private Integer id;

    @NotNull(message = "El codigo de bien no de debe de ser nulo")
    private Long codigoBien;

    @NotNull(message = "El activo informatico no de debe de ser nulo")
    private Integer activoInformaticoId;

    @NotNull(message = "El empleado no de debe de ser nulo")
    private Empleado empleadoId;

}
