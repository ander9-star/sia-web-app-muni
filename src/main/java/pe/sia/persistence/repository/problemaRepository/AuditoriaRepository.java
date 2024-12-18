package pe.sia.persistence.repository.problemaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.problema.Auditoria;
import java.util.List;
import java.util.Map;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>{

    @Query(value="select * from get_auditoria_mantenimiento(:id_man)", nativeQuery = true)
    List<Object[]> getAuditoriaMantenimiento(@Param("id_man") Integer idMantenimiento);

    // para obtener data completa de la auditoria
    @Query(value = "select * from get_data_auditoria_full(:id_usuario, :es_admin)", nativeQuery = true)
    List<Object[]> getDataFullAuditoria(@Param("id_usuario") Integer idUsuario, @Param("es_admin") Boolean esAdmin);

    // para obtener la cantidad de audiotria de hoy y el total
    @Query(value = "select * from get_cantidad_auditoria_hoy_total()", nativeQuery = true)
    List<Map<String, Object>> getTotalAuditoriaHoyTotal();

}
