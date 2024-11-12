package pe.sia.presentation.dto.activosDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SoftwareDTO {

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
}
