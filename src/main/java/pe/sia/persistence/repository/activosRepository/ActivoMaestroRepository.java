package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.activos.ActivoMaestro;

import java.util.List;

@Repository
public interface ActivoMaestroRepository extends JpaRepository<ActivoMaestro, Integer> {


    // procedimiento para obener los top 5 activos con mas incidenciaas/fallos
    @Query(value = "select * from get_top_5_activos_con_problemas()", nativeQuery = true)
    List<Object[]> getTop5ActivosIncidencias();

    // tabla personaliada para obtener solo el id, codigo de bien, nombre y empleado
    @Query(value = "select * from listar_activo_nombre()", nativeQuery = true)
    List<Object[]> getActivoMaestroSinProblemaGeneral();
}
