package pe.sia.presentation.dto.incienciasDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.sia.persistence.entity.problema.Prioridad;
import pe.sia.util.EstadoPeticion;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrioridadDTO extends EstadoPeticion {
    private Integer id;
    private String nombre;
    private String mes;
    private Integer cantidadLeve;
    private Integer cantidadGrave;
    private Integer cantidadCritico;
    private Prioridad prioridad;
    private List<PrioridadDTO> listAllPrioridad;

    public static class Builder extends  EstadoPeticion {
        private String nombre;
        private String mes;
        private Integer cantidadLeve;
        private Integer cantidadGrave;
        private Integer cantidadCritico;

        public Builder statusCode(int statusCode) {
            super.setStatusCode(statusCode);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder mes(String mes) {
            this.mes = mes;
            return this;
        }

        public Builder cantidadLeve(Integer cantidadLeve) {
            this.cantidadLeve = cantidadLeve;
            return this;
        }

        public Builder cantidadGrave(Integer cantidadGrave) {
            this.cantidadGrave = cantidadGrave;
            return this;
        }

        public Builder cantidadCritico(Integer cantidadCritico) {
            this.cantidadCritico = cantidadCritico;
            return this;
        }

        public PrioridadDTO build() {
            PrioridadDTO prioridadDTO = new PrioridadDTO();
            prioridadDTO.setStatusCode(this.getStatusCode());
            prioridadDTO.setNombre(this.nombre);
            prioridadDTO.setMes(this.mes);
            prioridadDTO.setCantidadLeve(this.cantidadLeve);
            prioridadDTO.setCantidadGrave(this.cantidadGrave);
            prioridadDTO.setCantidadCritico(this.cantidadCritico);
            return prioridadDTO;
        }
    }
}
