package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.problema.Mantenimiento;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Integer>{

    @Query(value = "select * from get_detalle_problema_mantenimiento(:id_dp)", nativeQuery = true)
    List<Object[]> getMantenimientoFallo(Integer idFallo);

    // Obteniendo con mas detalles el mantenimiento
    @Query(value = "select * from get_data_mantenimiento()", nativeQuery = true)
    List<Object[]>  getDataMantenimiento();

    // Para obtener aquellas problema/fallos que todavia no tienen mantenimiento
    @Query(value = "select * from get_codigos_problema_sin_mantenimiento()", nativeQuery = true)
    List<Object[]>  getFISinMantenimiento();

    // Para obtener aquellas solo la data importante para la creacion del mantenimiento
    @Query(value = "select * from get_data_short_tipo_mantenimiento()", nativeQuery = true)
    List<Object[]>  getDataShortTipoMantenimiento();

}
