package pe.sia.presentation.dto.activosDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RedDTO {
    
    private Long id;

    @NotNull(message = "El modelo de la red no puede ser nulo")
    private String modelo;

    @NotNull(message = "La dirección IP no puede ser nula")
    @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", 
             message = "La dirección IP debe de ser IPv4")
    private String direccionIP;
    
    @NotNull(message = "El proveedor no puede ser nulo")
    private Integer proveedorId;
    
    @NotNull(message = "El tipo de red no puede ser nulo")
    private Integer tipoRedId;

    @NotNull(message = "El número de orden de compra no puede ser nulo")
    private Long ordenCompra;

}
