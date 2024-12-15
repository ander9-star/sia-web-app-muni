package pe.sia.persistence.repository.problemaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.problema.TipoMantenimiento;

import java.util.List;

@Repository
public interface TipoMantenimientoRepository extends JpaRepository<TipoMantenimiento, Integer> {

    @Query(value = "select * from get_data_short_tipo_mantenimiento()", nativeQuery = true)
    List<Object[]> getShortDataTipoMantenimiento();
}
