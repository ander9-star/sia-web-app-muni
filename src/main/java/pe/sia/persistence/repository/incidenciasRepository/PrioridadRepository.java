package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.incidencias.Prioridad;

import java.util.List;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Integer>{

    @Query(value = "select * from listar_prioridad()", nativeQuery = true)
    List<Object[]> listAllPrioridad();

}
