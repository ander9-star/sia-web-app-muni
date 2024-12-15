package pe.sia.service.interfaces;

import java.util.Map;

import pe.sia.presentation.dto.problemaDTO.DetalleProblemaDTO;

public interface ProblemaDetalleService {

    // obtener todas las problema normalizadas
    DetalleProblemaDTO getIncidenciaFallo();

    // para insertar una incidencia
    DetalleProblemaDTO insertarIncidencia(DetalleProblemaDTO falloIncidencia);

    // para insertar una incidencia
    DetalleProblemaDTO actualizarIncidencia(Integer id, DetalleProblemaDTO falloIncidencia);

    // para eliminar una incidencia
    DetalleProblemaDTO eliminarIncidencia(Integer id);

    // para buscar una incidencia
    DetalleProblemaDTO buscarIncidencia(String codigoProblema);

    Integer getTotalIncidenciasPorMes(); 

    // se va recibir una tabla
    Map<String, Object> getIncidenciasDosMonthComparacion();

    Integer getTotalIncidenciasPorDia(); 
    
    // se va recibir una tabla
    Map<String, Object> getIncidenciasDosDayComparacion();

    // para obtener todas las problema solucionadas
    Integer getIncidenciasTotalesSolucionadas();

    // para obtener el promedio de las problema solucionadas
    Integer getPromedioIncidenciasSolucionadas();

    // empleado con mas problema reportadas actualmente
    Map<String, Object> getEmpleadoCantidadMaxIncidencias();

    // promedio de empleado con mas problema acutales
    Integer getPromedioIncidenciasEmpleado();

    // obtener la cantidad total de problema registradas ayer
    Integer getIncidenciasTotalesAyer();

    // obtener el promedio de problema registradas con respecto ayer con hoy dia
    Integer getPromedioIncidenciasAyerHoy();

    // para obtener el total de mantenimiento de hoy dia y ayer
    Map<String, Object> getTotalManenimientoHoyAyer();

    // para obtener la cantidad de auditorias del dia de hoy y el total
    Map<String, Object> getTotalAuditoriaHoyTotal();

    // para obtener la tabla personalizada
    DetalleProblemaDTO getTableResults();

    // para obtener la cantidad de problema por mes
    DetalleProblemaDTO getCantidadTotalIncidencaPorMes();

    // para obtener la cantidad, porcentaje de solucionado y en mantenimiento de las problema
    DetalleProblemaDTO getTotalIncidencias();

    // para obtener la cantidad de problema de todos los dias del mes actual con el anterior
    DetalleProblemaDTO getTotalIncidenciasDiasMes();

    // para obtener la cantidad total de problema del mes actual y anterior
    DetalleProblemaDTO getIncidenciasTotalMesActualAnterior();

    // procedimiento para obtener la cantidad total de problema y fallos por prioridad del mes actual y anterior
    DetalleProblemaDTO getTotalIncidenciasFalloByPrioridad();
}
