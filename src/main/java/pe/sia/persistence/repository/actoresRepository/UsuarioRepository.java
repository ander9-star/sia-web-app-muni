package pe.sia.persistence.repository.actoresRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.sia.persistence.entity.actores.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName); 
}
