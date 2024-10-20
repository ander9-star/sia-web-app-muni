package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
