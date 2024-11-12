package pe.sia.presentation.dto.incienciasDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.sia.util.EstadoPeticion;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidenciaFalloDTO extends EstadoPeticion {
    private Integer id;
	private String codigoProblema;
	private String descripcion;
	private String fechaOcurrencia;
	private String medioReporte;
	private String solucion;
	private Integer activoMaestroId;
	private String codigoBien;
	private String nombreActivo;
	private String tipoActivo;
	private String nombreEmpleado;
	private Integer categoriaId;
	private String categoria;
	private String prioridad;
	private Integer prioridadId;
	private Integer usuarioId;
	private Boolean solucionado;
    private List<IncidenciaFalloDTO> listFalloIncidenciaFalloDTO;
}
