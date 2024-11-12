package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.activos.ActivoMaestro;

@Repository
public interface ActivoMaestroRepository extends JpaRepository<ActivoMaestro, Integer> {

}
