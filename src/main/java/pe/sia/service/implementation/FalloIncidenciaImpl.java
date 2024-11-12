package pe.sia.service.implementation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.sia.persistence.entity.incidencias.FalloIncidencia;
import pe.sia.persistence.repository.incidenciasRepository.FalloIncidenciaRepository;
import pe.sia.presentation.dto.incienciasDTO.IncidenciaFalloDTO;
import pe.sia.service.interfaces.FalloIncidenciaService;
import pe.sia.util.UtilsApp;

@Service
@Slf4j
public class FalloIncidenciaImpl implements FalloIncidenciaService {

    @Autowired
    private FalloIncidenciaRepository falloIncidenciaRepository;

    @Override
    public IncidenciaFalloDTO getIncidenciaFallo() {
        IncidenciaFalloDTO requestDTO = new IncidenciaFalloDTO();
        try {
            List<Object[]> listResults = falloIncidenciaRepository.findAllIncidencias();
            List<IncidenciaFalloDTO> listFalloDTO = new ArrayList<>();

            if(!listResults.isEmpty()) {
                for(Object[] object : listResults) {
                    IncidenciaFalloDTO falloIncidenciaDTO = new IncidenciaFalloDTO();
                    falloIncidenciaDTO.setStatusCode(200);
                    falloIncidenciaDTO.setCodigoProblema((String) object[0]);
                    falloIncidenciaDTO.setDescripcion((String) object[1]);
                    falloIncidenciaDTO.setFechaOcurrencia(UtilsApp.formatearFecha((Instant) object[2])); 
                    falloIncidenciaDTO.setMedioReporte((String) object[3]);
                    falloIncidenciaDTO.setSolucion((String) object[4]);
                    falloIncidenciaDTO.setCodigoBien((String) object[5]);
                    falloIncidenciaDTO.setNombreActivo((String) object[6]);
                    falloIncidenciaDTO.setTipoActivo((String) object[7]);
                    falloIncidenciaDTO.setNombreEmpleado((String) object[8]);
                    falloIncidenciaDTO.setPrioridad((String) object[9]);
                    falloIncidenciaDTO.setCategoria((String) object[10]);
                    listFalloDTO.add(falloIncidenciaDTO);
                }

                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado de incidencias con éxito");
                requestDTO.setListFalloIncidenciaFalloDTO(listFalloDTO);
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las incidencias: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public Integer getTotalIncidenciasPorMes() {
        int mesActual = LocalDateTime.now().getMonthValue();
        return falloIncidenciaRepository.countIncidenciasByMonth(mesActual);
    }

    @Override
    public Map<String, Object> getIncidenciasDosMonthComparacion() {
        List<Map<String, Object>> resultadosTable = falloIncidenciaRepository.getIncidenciasComparacion();

        if(resultadosTable.size() < 2) {
            // si no estan los dos meses
            return Map.of("mesActual",0, "mesAnterior", 0);
        }

        Map<String, Object> mesActual = resultadosTable.get(0);
        Map<String, Object> mesAnterior = resultadosTable.get(1);

        long totalMesActual = (long) mesActual.get("total");
        long totalMesAnterior = (long) mesAnterior.get("total");

        double porcentaje = (double) ((totalMesActual - totalMesAnterior) / totalMesAnterior) * 100;
        boolean incremento = porcentaje >= 0;

        return Map.of(
            "porcentaje", Math.abs(Math.round(porcentaje)),
            "esIncremento", incremento
        );
    }

    @Override
    public Integer getTotalIncidenciasPorDia() {
        int diaActual = LocalDateTime.now().getDayOfMonth();
        return falloIncidenciaRepository.countIncidenciasByDay(diaActual);
    }

    @Override
    public Map<String, Object> getIncidenciasDosDayComparacion() {
        List<Map<String, Object>> resultadoTable = falloIncidenciaRepository.getIncidenciasDiaComparacion();

        if(resultadoTable.size() < 2) {
            return Map.of("diaActual",0, "diaAnterior", 0);
        }

        Map<String, Object> diaActual = resultadoTable.get(0);
        Map<String, Object> diaAnterior = resultadoTable.get(1);

        long totalDiaActual = (long) diaActual.get("total");
        long totalDiaAnterior = (long) diaAnterior.get("total");

        double porcentaje =  ((double)(totalDiaActual - totalDiaAnterior) / totalDiaAnterior) * 100;
        log.info("total dia actual: " + totalDiaActual + " total dia anterior: " + totalDiaAnterior + " procentaje: " + porcentaje);
        boolean incremento = porcentaje >= 0;

        return Map.of(
            "porcentaje", Math.abs(Math.round(porcentaje)), 
            "esIncremento", incremento
        );
    }

    @Override
    public Integer getIncidenciasTotalesSolucionadas() {
        int totalIncidenciasSolucionadas;
        totalIncidenciasSolucionadas = falloIncidenciaRepository.getIncidenciasTotalesSolucionadas();
        return totalIncidenciasSolucionadas;
    }

    @Override
    public Integer getPromedioIncidenciasSolucionadas() {
        int promedioIncidenciasSolucionadas;
        promedioIncidenciasSolucionadas = falloIncidenciaRepository.getPromedioIncidenciasResueltas();
        return promedioIncidenciasSolucionadas;
    }

    @Override
    public Map<String, Object> getEmpleadoCantidadMaxIncidencias() {
        List<Map<String, Object>> resultadoTabla = falloIncidenciaRepository.getEmpleadoCantidaMaximaIncidencias();

        Map<String, Object> empleado = resultadoTabla.get(0);

        return Map.of(
            "nombre", empleado.get("nombre"),
            "cantidad", empleado.get("cantidad")
        );
    }

    @Override
    public Integer getPromedioIncidenciasEmpleado() {
        int promedio;
        promedio = falloIncidenciaRepository.getPromedioEmpleadoMaxIncidencias();
        return promedio;
    }

    @Override
    public Integer getIncidenciasTotalesAyer() {
        int total;
        total = falloIncidenciaRepository.getIncidenciasTotalesAyer();
        return total;
    }

    @Override
    public Integer getPromedioIncidenciasAyerHoy() {
        int promedio;
        promedio = falloIncidenciaRepository.getPromedioIncidenciasAyerHoy();
        return promedio;
    }

    @Override
    public Map<String, Object> getTotalManenimientoHoyAyer() {
        List<Map<String, Object>> resultadoTable = falloIncidenciaRepository.getTotalManenimientoHoyAyer();
        Map<String, Object> map = resultadoTable.get(0);
        return Map.of(
            "totalHoy", map.get("totalHoy"),
            "totalAyer", map.get("totalAyer")
        );
    }

    @Override
    public Map<String, Object> getTotalAuditoriaHoyTotal() {
        List<Map<String, Object>> resultadoTable = falloIncidenciaRepository.getTotalAuditoriaHoyTotal();
        Map<String, Object> map = resultadoTable.get(0);
        return Map.of(
            "totalHoy", map.get("totalHoy"),
            "total", map.get("total")
        );
    }

    @Override
    public IncidenciaFalloDTO getTableResults() {
        IncidenciaFalloDTO requestDTO = new IncidenciaFalloDTO();
        try{
            List<Object[]> resultTable = falloIncidenciaRepository.getTableResults();
            List<IncidenciaFalloDTO> listIncidenciaDTO = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] objects : resultTable) {
                    IncidenciaFalloDTO incidenciaFalloDTO = new IncidenciaFalloDTO();
                    incidenciaFalloDTO.setStatusCode(200);
                    incidenciaFalloDTO.setActivoMaestroId((Integer) objects[0]);
                    incidenciaFalloDTO.setCodigoBien((String) objects[1]);
                    incidenciaFalloDTO.setNombreActivo((String) objects[2]);
                    incidenciaFalloDTO.setNombreEmpleado((String) objects[3]);
                    listIncidenciaDTO.add(incidenciaFalloDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado con éxito la tabla personalizada");
                requestDTO.setListFalloIncidenciaFalloDTO(listIncidenciaDTO);
            }
            return requestDTO;
        }catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las incidencias: " + e.getMessage());
            return requestDTO;
        }
    }

}
