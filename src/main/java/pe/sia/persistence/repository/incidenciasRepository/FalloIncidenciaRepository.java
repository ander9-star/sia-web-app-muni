package pe.sia.persistence.repository.incidenciasRepository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.incidencias.FalloIncidencia;

@Repository
public interface FalloIncidenciaRepository extends JpaRepository<FalloIncidencia, Integer>{

    @Query(value = "select * from listar_incidencias_fallos()", nativeQuery = true)
    List<Object[]> findAllIncidencias();

    @Query(value="select incidencias_por_mes(:mes)", nativeQuery = true)
    Integer countIncidenciasByMonth(@Param("mes") Integer mes); 

    // se va a recibir una tabla
    @Query(value="select * from incidencias_ultimo_dos_mes()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasComparacion();

    @Query(value="select incidencias_por_dia(:dia)", nativeQuery = true)
    Integer countIncidenciasByDay(@Param("dia") Integer dia); 

    // se va a recibir una tabla
    @Query(value="select * from incidencias_ultimo_dos_dias()", nativeQuery = true)
    List<Map<String, Object>> getIncidenciasDiaComparacion();

    // para obtener la cantidad de incidencias solucionadas actualmente
    @Query(value = "select get_cantidad_incidencias_resueltas()", nativeQuery = true)
    Integer getIncidenciasTotalesSolucionadas();

    // funcion para obtener el promedio de las incidencias resueltas
    @Query(value = "select get_promedio_incidencias_resueltas()", nativeQuery = true)
    Integer getPromedioIncidenciasResueltas();

    // funcion que retorna el empleado y cantidad con mayor incidencias actuales (una tabla)
    @Query(value = "select * from get_cantidad_empleado_mas_incidencias()", nativeQuery = true)
    List<Map<String, Object>> getEmpleadoCantidaMaximaIncidencias();

    // para obtener el promedio del empleado con mas incidencias
    @Query(value = "select get_promedio_empleado_incidencias()", nativeQuery = true)
    Integer getPromedioEmpleadoMaxIncidencias();

    // para obtener la cantidad de incidencias registradas ayer
    @Query(value = "select get_incidencias_ayer()", nativeQuery = true)
    Integer getIncidenciasTotalesAyer();

    // para obtener el promedio de incidencias con respecto de ayer con el dia actual
    @Query(value = "select * from get_incidencias_promedio_dia_ayer_hoy()", nativeQuery = true)
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

}
