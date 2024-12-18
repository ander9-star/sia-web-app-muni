package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.activos.Software;
import java.util.List;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Integer> {

    @Query(value = "select * from get_detalle_problema_por_sotfware()", nativeQuery = true)
    List<Object[]>  getFISoftware();
}
