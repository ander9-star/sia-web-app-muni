package pe.sia.persistence.repository.problemaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.problema.DetalleProblema;

@Repository
public interface DetalleProblemaRepository extends JpaRepository<DetalleProblema, Integer> {

    // para buscar un detalle problema por codigo de problema
    Optional<DetalleProblema> findByCodigoProblema(String codigoProblema);

    // listando por completo la data de la tabla detalle_problema
    @Query(value = "select * from listar_detalle_problema_and_por_id(:id_pg, :id_usuario, :es_admin)", nativeQuery = true)
    List<Object[]> findAllDetalleProblema(@Param("id_pg") Integer idProblemaGeneral, @Param("id_usuario") Integer idUsuario,
                                          @Param("es_admin") Boolean esAdmin);

    // para obtener la cantidad de detalle problema solucionadas actualmente
    @Query(value = "select get_cantidad_problemas_resueltos()", nativeQuery = true)
    Integer getDetalleProblemaSolucionados();

    // funcion para obtener el promedio de las detalle problema resueltas
    @Query(value = "select get_promedio_problemas_resueltas()", nativeQuery = true)
    Integer getPromedioDetalleProblemaResueltos();

    // para obtener el promedio del empleado con mas detalle problema
    @Query(value = "select * from get_cantidad_empleado_mas_problemas();", nativeQuery = true)
    List<Map<String, Object>> getPromedioMaxEmpleadoDetalleProblema();

    // para obtener la cantidad de detalle problema registradas ayer
    @Query(value = "select get_detalle_problema_ayer()", nativeQuery = true)
    Integer getTotalDetalleProblemaAyer();

    // para obtener el promedio de detalle problema con respecto de ayer con el dia actual
    @Query(value = "select * from get_detalle_problema_promedio_dia_ayer_hoy()", nativeQuery = true)
    List<Map<String, Object>> getPromedioDetalleProbemaEntreAyerHoy();

    // procedimiento para obtener la cantidad de problema por mes
    @Query(value = "select * from get_cantidad_dp_por_mes()",nativeQuery = true)
    List<Object[]> getCantidadTotalDetallePromedioPorMes();

    // procedimiento para obtener la cantidad total, porcentaje de solucionado y mantenimiento
    @Query(value = "select * from get_medidas_detalle_problema_mantenimiento()", nativeQuery = true)
    List<Object[]> getMedidasDetalleProblemaMantenimiento();

    // procedimiento para obtener la cantidad de problema por los dias del mes actual y anterior
    @Query(value = "select * from contar_problema_detalle_semanales_mes_actua_anterior()", nativeQuery = true)
    List<Object[]> getCantidadDetalleProblemaPorDiaMesActualAnterior();

    // procedimiento para obtener la cantidad total de problema del mes anterior con el actual
    @Query(value = "select * from contar_problema_detalle_total_mes()", nativeQuery = true)
    List<Object[]> getDetalleProblemaTotalMesActualAnterior();

    // procedimiento para obtener la cantidad total de problema y fallos por prioridad del mes actual y anterior
    @Query(value = "select * from get_prioridad_categoria_detalle_problema()", nativeQuery = true)
    List<Object[]> getTotalDetalleProblemaByPrioridadByCategoria();

    @Query(value = "select * from listar_detalle_problema_por_id_pg(:id_dp)", nativeQuery = true)
    List<Object[]> getDetalleProblemaPorIdPG(@Param("id_dp") Integer idProblemaGeneral);

    // para obtener el nombre del activo ingresando el codigo del problema del la tabla detalle_problema
    @Query(value = "select get_nombre_activo(:codigoP)", nativeQuery = true)
    Optional<String> getNombreActivoPorCodigoProblema(String codigoP);

    // funcion que retorna el empleado y cantidad con mayor detalle problema actuales (una tabla)
    @Query(value = "select * from get_cantidad_empleado_mas_problemas()", nativeQuery = true)
    List<Map<String, Object>> getMaxEmpleadoDetalleProblema();

}
