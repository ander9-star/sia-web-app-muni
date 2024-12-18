package pe.sia.persistence.repository.actoresRepository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.actores.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName);

    @Query(value = "SELECT * FROM listar_usuarios()", nativeQuery = true)
    List<Object[]> findAllUser();

    @Query(value = "select get_user_problema_general(:id_usuario)", nativeQuery = true)
    Optional<Boolean> findUserIncidencia(@Param("id_usuario") Integer idUsuario);

}
