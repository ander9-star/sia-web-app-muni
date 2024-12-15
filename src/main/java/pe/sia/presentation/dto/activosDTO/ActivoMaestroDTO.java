package pe.sia.presentation.dto.activosDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pe.sia.persistence.entity.actores.Empleado;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivoMaestroDTO extends EstadoPeticion {

    private Integer id;

    @NotNull(message = "El codigo de bien no de debe de ser nulo")
    private String codigoBien;

    @NotNull(message = "El activo informatico no de debe de ser nulo")
    private Integer activoInformaticoId;

    @NotNull(message = "El empleado no de debe de ser nulo")
    private Empleado empleadoId;

    private String nombreActivo;
    private Integer activoMaestroId;
    private String nombreEmpleado;
    private String tipoActivo;
    private String proveedor;
    private Integer cantidadFI;
    private Integer porcentajeFI;
    private List<ActivoMaestroDTO> activoMaestroDTOList;

    public static class Builder extends EstadoPeticion {
        private String codigoBien;
        private String nombreActivo;
        private String tipoActivo;
        private String proveedor;
        private Integer cantidadFI;
        private Integer porcentajeFI;

        public Builder statusCode(Integer statusCode) {
            super.setStatusCode(statusCode);
            return this;
        }

        public Builder codigoBien(String codigoBien) {
            this.codigoBien = codigoBien;
            return this;
        }

        public Builder nombreActivo(String nombreActivo) {
            this.nombreActivo = nombreActivo;
            return this;
        }

        public Builder tipoActivo(String tipoActivo) {
            this.tipoActivo = tipoActivo;
            return this;
        }

        public Builder proveedor(String proveedor) {
            this.proveedor = proveedor;
            return this;
        }

        public Builder cantidadFI(Integer cantidadFI) {
            this.cantidadFI = cantidadFI;
            return this;
        }

        public Builder porcentajeFI(Integer porcentajeFI) {
            this.porcentajeFI = porcentajeFI;
            return this;
        }

        public ActivoMaestroDTO build() {
            ActivoMaestroDTO activoMaestroDTO = new ActivoMaestroDTO();
            activoMaestroDTO.setStatusCode(this.getStatusCode());
            activoMaestroDTO.setCodigoBien(this.codigoBien);
            activoMaestroDTO.setNombreActivo(this.nombreActivo);
            activoMaestroDTO.setTipoActivo(this.tipoActivo);
            activoMaestroDTO.setProveedor(this.proveedor);
            activoMaestroDTO.setCantidadFI(this.cantidadFI);
            activoMaestroDTO.setPorcentajeFI(this.porcentajeFI);
            return activoMaestroDTO;
        }

    }

}
