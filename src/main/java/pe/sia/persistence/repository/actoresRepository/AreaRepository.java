package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long>{

}
