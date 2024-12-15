package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import pe.sia.persistence.entity.problema.Auditoria;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>{

    @Query(value="select * from get_auditoria_mantenimiento(:id_man)", nativeQuery = true)
    List<Object[]> getAuditoriaMantenimiento(@Param("id_man") Integer idMantenimiento);

    // para actualizar una auditoria
    @Modifying // se usa para indicar que se va a usar una operacion de modificacion, en este caso es UPDATE
    @Transactional // asegura que la operacion se ejecuta dentro de una transacion
    @Query(value="call update_auditoria(:fecha, :cambio, :id_auditoria);", nativeQuery = true)
    void updateAuditoria(@Param("fecha") Instant fecha, @Param("cambio") String cambio, @Param("id_auditoria") Integer idAuditoria);

    // para obtener data completa de la auditoria
    @Query(value = "select * from get_data_auditoria_full()", nativeQuery = true)
    List<Object[]> getDataFullAuditoria();

}
