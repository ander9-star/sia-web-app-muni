package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.activos.InfraestructuraRed;

import java.util.List;

@Repository
public interface InfraestructuraRedRepository extends JpaRepository<InfraestructuraRed, Integer>{

    @Query(value = "select * from get_detalle_problema_por_red()", nativeQuery = true)
    List<Object[]> getFIRed();
}
