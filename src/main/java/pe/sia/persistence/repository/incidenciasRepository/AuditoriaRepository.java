package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.incidencias.Auditoria;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>{

}
