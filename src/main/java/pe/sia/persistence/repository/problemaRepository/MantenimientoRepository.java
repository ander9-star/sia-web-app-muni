package pe.sia.persistence.repository.problemaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.problema.Mantenimiento;
import java.util.List;
import java.util.Map;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer>{

    @Query(value = "select * from get_detalle_problema_mantenimiento(:id_dp)", nativeQuery = true)
    List<Object[]> getMantenimientoDetalleProblema(@Param("id_dp") Integer idDp);

    // Obteniendo con mas detalles el mantenimiento
    @Query(value = "select * from get_data_mantenimiento(:id_usuario, :es_admin)", nativeQuery = true)
    List<Object[]>  getDataMantenimiento(@Param("id_usuario") Integer idUsuario, @Param("es_admin") Boolean esAdmin);

    // Para obtener aquellas problema/fallos que todavia no tienen mantenimiento
    @Query(value = "select * from get_codigos_problema_sin_mantenimiento()", nativeQuery = true)
    List<Object[]>  getFISinMantenimiento();

    // para obtener la cantidad de mantenimiento de hoy y ayer
    @Query(value = "select * from get_cantidad_mantenimiento_hoy_ayer()", nativeQuery = true)
    List<Map<String, Object>> getTotalManenimientoHoyAyer();

    @Query(value = "select * from get_total_vencida_mantenimiento();", nativeQuery = true)
    List<Map<String, Object>> getTotalVencidaMantenimiento();

}
