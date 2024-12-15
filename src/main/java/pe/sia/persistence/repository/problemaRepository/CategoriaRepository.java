package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.problema.Categoria;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

    @Query(value = "select * from listar_categoria()", nativeQuery = true)
    List<Object[]> listAllCategoria();

}
