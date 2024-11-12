package pe.sia.service.interfaces;

import java.util.Map;

import pe.sia.presentation.dto.incienciasDTO.IncidenciaFalloDTO;

public interface FalloIncidenciaService {

    // obtener todas las incidencias normalizadas
    IncidenciaFalloDTO getIncidenciaFallo();

    Integer getTotalIncidenciasPorMes(); 

    // se va recibir una tabla
    Map<String, Object> getIncidenciasDosMonthComparacion();

    Integer getTotalIncidenciasPorDia(); 
    
    // se va recibir una tabla
    Map<String, Object> getIncidenciasDosDayComparacion();

    // para obtener todas las incidencias solucionadas
    Integer getIncidenciasTotalesSolucionadas();

    // para obtener el promedio de las incidencias solucionadas
    Integer getPromedioIncidenciasSolucionadas();

    // empleado con mas incidencias reportadas actualmente
    Map<String, Object> getEmpleadoCantidadMaxIncidencias();

    // promedio de empleado con mas incidencias acutales
    Integer getPromedioIncidenciasEmpleado();

    // obtener la cantidad total de incidencias registradas ayer
    Integer getIncidenciasTotalesAyer();

    // obtener el promedio de incidencias registradas con respecto ayer con hoy dia
    Integer getPromedioIncidenciasAyerHoy();

    // para obtener el total de mantenimiento de hoy dia y ayer
    Map<String, Object> getTotalManenimientoHoyAyer();

    // para obtener la cantidad de auditorias del dia de hoy y el total
    Map<String, Object> getTotalAuditoriaHoyTotal();

    // para obtener la tabla personalizada
    IncidenciaFalloDTO getTableResults();
}
