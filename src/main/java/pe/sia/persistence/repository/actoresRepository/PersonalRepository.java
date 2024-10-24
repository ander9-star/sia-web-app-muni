package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Personal;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer>{
    
}
