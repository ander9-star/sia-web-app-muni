package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HardwareDTO {

    private Long id;

    @NotNull(message = "el nombre del hardware no puede ser nulo")
    private String nombre;

    @NotNull(message =  "la marca no debe de ser nula")
    private String marca;
    
    @NotNull(message = "el modelo no debe de ser nulo")
    private String modelo;

    @NotNull(message = "el proveedor no debe de ser nulo")
    private Integer proveedorId;

    @NotNull(message = "el número de orden de compra no debe de ser nulo")
    private Long ordenCompra;

}
