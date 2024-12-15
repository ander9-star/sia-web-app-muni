package pe.sia.service.interfaces;

import java.util.Map;
import pe.sia.presentation.dto.problemaDTO.DetalleProblemaDTO;

public interface DetalleProblemaService {

    // obtener la data de detalle problema normalizadas
    DetalleProblemaDTO findAllDetalleProblema(Integer idProblemaGeneral);

    // para insertar una nuevo detalle problema
    DetalleProblemaDTO insertarDetalleProblema(DetalleProblemaDTO detalleProblema);

    // para actualizar un detalle problema
    DetalleProblemaDTO actualizarDetalleProblema(Integer id, DetalleProblemaDTO detalleProblemaUpdate);

    // para eliminar un detalle problema
    DetalleProblemaDTO eliminarDetalleProblema(Integer id);

    // para buscar una detalle problema por su codigo de problema
    DetalleProblemaDTO buscarDetalleProblema(String codigoProblema);

    // para obtener la cantidad total de detalle problema solucionados
    Integer getDetalleProblemaSolucionados();

    // para obtener el promedio de los detalle problema solucionados
    Integer getPromedioDetalleProblemaResueltos();

    // para obtener el empleado con mas detalle problemas reportados actualmente
    Map<String, Object> getMaxEmpleadoDetalleProblema();

    // para obtener el promedio de empleado con mas detalle problemas acutales
    Integer getPromedioMaxEmpleadoDetalleProblema();

    // para obtener la cantidad total de detalle problema registrados ayer
    Integer getTotalDetalleProblemaAyer();

    // para obtener el promedio de detalle problema registradas con respecto ayer con hoy dia
    Integer getPromedioDetalleProbemaEntreAyerHoy();

    // para obtener la cantidad de detalle problema por mes
    DetalleProblemaDTO getCantidadTotalDetallePromedioPorMes();

    // para obtener la cantidad, porcentaje de solucionado y en mantenimiento de los detalle problema
    DetalleProblemaDTO getMedidasDetalleProblemaMantenimiento();

    // para obtener la cantidad de detalle problema de todos los dias del mes actual con el anterior
    DetalleProblemaDTO getCantidadDetalleProblemaPorDiaMesActualAnterior();

    // para obtener la cantidad total de detalle problema del mes actual y anterior
    DetalleProblemaDTO getDetalleProblemaTotalMesActualAnterior();

    // procedimiento para obtener la cantidad total de detalle problema y fallos por prioridad del mes actual y anterior
    DetalleProblemaDTO getTotalDetalleProblemaByPrioridadByCategoria();

    // procedimiento para obtener los detalles problema por el id de problema general
    DetalleProblemaDTO getDetalleProblemaPorIdProblemaGeneral(Integer idProblemaGeneral);
}
