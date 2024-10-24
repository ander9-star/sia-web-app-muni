package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.incidencias.TipoMantenimiento;

@Repository
public interface TipoMantenimientoRepository extends JpaRepository<TipoMantenimiento, Integer> {

}
