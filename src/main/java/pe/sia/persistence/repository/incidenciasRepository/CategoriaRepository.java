package pe.sia.persistence.repository.incidenciasRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.incidencias.Categoria;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

    @Query(value = "select * from listar_categoria()", nativeQuery = true)
    List<Object[]> listAllCategoria();

}
