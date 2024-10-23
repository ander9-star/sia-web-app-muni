package pe.sia.presentation.dto.activosDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SoftwareDTO {

    private Long id;

    @NotNull(message = "el nombre no debe de estar vacio")
    private String nombre;

    @NotNull(message = "la versión del software no debe de estar vacia")
    private String version;

    @NotNull(message = "la fecha de instalación no debe de estar vacia")
    private LocalDate fechaInstalacion;

    private LocalDate fechaVencimiento;

    @NotNull(message = "el proveedor no debe de estar vacio")
    private Long proveedorId;

    @NotNull(message = "la orden de compra no debe de estar vacio")
    private String ordenCompra;
}
