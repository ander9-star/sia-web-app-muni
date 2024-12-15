package pe.sia.persistence.repository.ubicaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.ubicaciones.OficinaSubgerencia;

import java.util.List;

@Repository
public interface OficinaSubgerenciaRepository extends JpaRepository<OficinaSubgerencia, Integer> {

    @Query(value = "select * from get_data_oficina_perfil()", nativeQuery = true)
    List<Object[]> findAllDataForProfile();

    @Query(value = "SELECT * FROM get_detalle_problema_por_oficina()", nativeQuery = true)
    List<Object[]> getFIPorOficina();
}
