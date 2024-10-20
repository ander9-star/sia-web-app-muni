package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
