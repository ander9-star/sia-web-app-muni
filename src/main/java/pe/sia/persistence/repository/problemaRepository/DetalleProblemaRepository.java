package pe.sia.persistence.repository.incidenciasRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.problema.DetalleProblema;

@Repository
public interface ProblemaGeneralRepository extends JpaRepository<DetalleProblema, Integer> {

    // para obtener el nombre del activo
    @Query(value = "select get_nombre_activo(:codigoP)", nativeQuery = true)
    Optional<String> getNombreActivo(String codigoP);

    // para buscar una incidencia por codigo de problema
    Optional<DetalleProblema> findByCodigoProblema(String codigoProblema);

    @Query(value = "select * from listar_detalle_problema()", nativeQuery = true)
    List<Object[]> findAllDetalleProblema();

    @Query(value="select get_problemas_generales_al_mes_actual()", nativeQuery = true)
    Integer countIncidenciasByMonth();

    // se va a recibir una tabla
    @Query(value="select * from get_problemas_generales_para_mes_actual_anterior()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasComparacion();

    @Query(value="select get_problemas_generado_por_dia()", nativeQuery = true)
    Integer countIncidenciasByDay();

    // se va a recibir una tabla
    @Query(value="select * from get_problema_generado_dia_actual_anterior()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasDiaComparacion();

    // para obtener la cantidad de problema solucionadas actualmente
    @Query(value = "select get_cantidad_problemas_resueltos()", nativeQuery = true)
    Integer getIncidenciasTotalesSolucionadas();

    // funcion para obtener el promedio de las problema resueltas
    @Query(value = "select get_promedio_problemas_resueltas()", nativeQuery = true)
    Integer getPromedioIncidenciasResueltas();

    // funcion que retorna el empleado y cantidad con mayor problema actuales (una tabla)
    @Query(value = "select * from get_cantidad_empleado_mas_problemas()", nativeQuery = true)
    List<Map<String, Object>> getEmpleadoCantidaMaximaIncidencias();

    // para obtener el promedio del empleado con mas problema
    @Query(value = "select get_promedio_empleado_problemas()", nativeQuery = true)
    Integer getPromedioEmpleadoMaxIncidencias();

    // para obtener la cantidad de problema registradas ayer
    @Query(value = "select get_detalle_problema_ayer()", nativeQuery = true)
    Integer getIncidenciasTotalesAyer();

    // para obtener el promedio de problema con respecto de ayer con el dia actual
    @Query(value = "select get_detalle_problema_promedio_dia_ayer_hoy()", nativeQuery = true)
    Integer getPromedioIncidenciasAyerHoy(); 

    // para obtener la cantidad de mantenimiento de hoy y ayer
    @Query(value = "select * from get_cantidad_mantenimiento_hoy_ayer()", nativeQuery = true)
    List<Map<String, Object>> getTotalManenimientoHoyAyer();

    // para obtener la cantidad de audiotria de hoy y el total
    @Query(value = "select * from get_cantidad_auditoria_hoy_total()", nativeQuery = true)
    List<Map<String, Object>> getTotalAuditoriaHoyTotal();

    // tabla personaliada para obtener solo el id, codigo de bien, nombre y empleado
    @Query(value = "select * from listar_activo_nombre()", nativeQuery = true)
    List<Object[]> getTableResults();

    // procedimiento para obtener la cantidad de problema por mes
    @Query(value = "select * from get_cantidad_dp_por_mes()",nativeQuery = true)
    List<Object[]> getCantidadTotalIncidencaPorMes();

    // procedimiento para obtener la cantidad total, porcentaje de solucionado y mantenimiento
    @Query(value = "select * from get_total_problemas_por_mes()", nativeQuery = true)
    List<Object[]> getTotalIncidencias();

    // procedimiento para obtener la cantidad de problema por los dias del mes actual y anterior
    @Query(value = "select * from contar_problema_detalle_semanales_mes_actua_anterior()", nativeQuery = true)
    List<Object[]> getTotalIncidenciasDiasMes();

    // procedimiento para obtener la cantidad total de problema del mes anterior con el actual
    @Query(value = "select * from contar_problema_detalle_total_mes()", nativeQuery = true)
    List<Object[]> getIncidenciasTotalMesActualAnterior();

    // procedimiento para obtener la cantidad total de problema y fallos por prioridad del mes actual y anterior
    @Query(value = "select * from get_prioridad_categoria_detalle_problema()", nativeQuery = true)
    List<Object[]> getTotalIncidenciasFalloByPrioridad();

}
