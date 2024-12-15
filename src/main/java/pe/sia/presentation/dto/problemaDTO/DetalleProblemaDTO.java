package pe.sia.presentation.dto.problemaDTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.sia.persistence.entity.problema.DetalleProblema;
import pe.sia.util.EstadoPeticion;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleProblemaDTO extends EstadoPeticion {
    private Integer idDetalleProblema;
	private Integer idProblemaGeneral;
	private String codigoProblema;
	private String codigoProblemaGeneral;
	private String descripcion;
	private String fechaRegistro;
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
	private String nombreUsuario;
	private DetalleProblema detalleProblema;
    private List<DetalleProblemaDTO> detalleProblemaDTOList;
	private Integer mes;
	private Integer cantidadMes;
	private Integer cantidadTotalFallo;
	private Integer porcentajeSolucionado;
	private Integer cantidadSolucionado;
	private Integer porcentajeMantenimiento;
	private Integer cantidadMantenimiento;
	private DetalleProblemaDTO detalleProblemaDTO;
	private String diaSemana;
	private Integer cantidadMesAnterior;
	private Integer cantidadMesActual;
	private List<PrioridadDTO> listPrioridadDTO;

	// usando el patron builder
	public static class Builder extends EstadoPeticion {
		// declarar las propiedades en privado
		private Integer mes;
		private Integer cantidadMes;
		private Integer cantidadTotalFallo;
		private Integer porcentajeSolucionado;
		private Integer cantidadSolucionado;
		private Integer porcentajeMantenimiento;
		private Integer cantidadMantenimiento;
		private String diaSemana;
		private Integer cantidadMesAnterior;
		private Integer cantidadMesActual;

		// para cada propiedad crear un metodo que siempre retorne Builder
		public Builder mes(Integer mes) {
			this.mes = mes;
			return this;
		}

		public Builder statusCode(Integer statusCode) {
			super.setStatusCode(statusCode);
			return this;
		}

		public Builder cantidadMes(Integer cantidadMes) {
			this.cantidadMes = cantidadMes;
			return this;
		}

		public Builder cantidadTotalFallo(Integer cantidadTotalFallo) {
			this.cantidadTotalFallo = cantidadTotalFallo;
			return this;
		}

		public Builder cantidadSolucionado(Integer cantidadSolucionado) {
			this.cantidadSolucionado = cantidadSolucionado;
			return this;
		}

		public Builder porcentajeSolucionado(Integer porcentajeSolucionado) {
			this.porcentajeSolucionado = porcentajeSolucionado;
			return this;
		}

		public Builder porcentajeMantenimiento(Integer porcentajeMantenimiento) {
			this.porcentajeMantenimiento = porcentajeMantenimiento;
			return this;
		}

		public Builder cantidadMantenimiento(Integer cantidadMantenimiento) {
			this.cantidadMantenimiento = cantidadMantenimiento;
			return this;
		}

		public Builder diaSemana(String diaSemana) {
			this.diaSemana = diaSemana;
			return this;
		}

		public Builder cantidadMesAnterior(Integer cantidadMesAnterior) {
			this.cantidadMesAnterior = cantidadMesAnterior;
			return this;
		}

		public Builder cantidadMesActual(Integer cantidadMesActual) {
			this.cantidadMesActual = cantidadMesActual;
			return this;
		}

		// por ultimo construir la incidencia
		public DetalleProblemaDTO build() {
			DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO();
			detalleProblemaDTO.setStatusCode(this.getStatusCode());
			detalleProblemaDTO.setMes(this.mes);
			detalleProblemaDTO.setCantidadMes(this.cantidadMes);
			detalleProblemaDTO.setCantidadTotalFallo(this.cantidadTotalFallo);
			detalleProblemaDTO.setCantidadSolucionado(this.cantidadSolucionado);
			detalleProblemaDTO.setPorcentajeSolucionado(this.porcentajeSolucionado);
			detalleProblemaDTO.setPorcentajeMantenimiento(this.porcentajeMantenimiento);
			detalleProblemaDTO.setCantidadMantenimiento(this.cantidadMantenimiento);
			detalleProblemaDTO.setDiaSemana(this.diaSemana);
			detalleProblemaDTO.setCantidadMesAnterior(this.cantidadMesAnterior);
			detalleProblemaDTO.setCantidadMesActual(this.cantidadMesActual);
			return detalleProblemaDTO;
		}
	}
}
