package pe.sia.presentation.dto.actoresDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO extends EstadoPeticion {
    private Integer idEmpleado;
    private String codigo;
    private String oficinaGerencia;
    private String nombreEmpleado;
    private Integer porcentaje;
    private Integer cantidad;
    private String cargo;
    private String oficina;
    private List<EmpleadoDTO> listTop5Empleados;
    private List<EmpleadoDTO> listEmpleados;

    public static class Builder extends EstadoPeticion {
        private String codigo;
        private String oficinaGerencia;
        private String nombreEmpleado;
        private Integer porcentaje;
        private Integer cantidad;
        private String cargo;
        private String oficina;

        public Builder statusCode(Integer statusCode) {
            super.setStatusCode(statusCode);
            return this;
        }

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder nombreEmpleado(String nombreEmpleado) {
            this.nombreEmpleado = nombreEmpleado;
            return this;
        }

        public Builder cargo(String cargo) {
            this.cargo = cargo;
            return this;
        }

        public Builder oficinaGerencia(String oficinaGerencia) {
            this.oficinaGerencia = oficinaGerencia;
            return this;
        }

        public Builder oficina(String oficina) {
            this.oficina = oficina;
            return this;
        }

        public Builder porcentaje(Integer porcentaje) {
            this.porcentaje = porcentaje;
            return this;
        }

        public Builder cantidad(Integer cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public EmpleadoDTO build() {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setStatusCode(this.getStatusCode());
            empleadoDTO.setCodigo(this.codigo);
            empleadoDTO.setNombreEmpleado(this.nombreEmpleado);
            empleadoDTO.setCargo(this.cargo);
            empleadoDTO.setOficina(this.oficina);
            empleadoDTO.setOficinaGerencia(this.oficinaGerencia);
            empleadoDTO.setPorcentaje(this.porcentaje);
            empleadoDTO.setCantidad(this.cantidad);
            return empleadoDTO;
        }
    }

}
