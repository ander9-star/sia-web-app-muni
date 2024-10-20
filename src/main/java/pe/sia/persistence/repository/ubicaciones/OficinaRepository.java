package pe.sia.persistence.repository.ubicaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.ubicaciones.Oficina;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {

}
