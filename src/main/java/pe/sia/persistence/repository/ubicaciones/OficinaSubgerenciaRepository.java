package pe.sia.persistence.repository.ubicaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.ubicaciones.OficinaSubgerencia;

@Repository
public interface OficinaSubgerenciaRepository extends JpaRepository<OficinaSubgerencia, Integer> {

}
