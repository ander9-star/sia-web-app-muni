package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.sia.persistence.entity.activos.Proveedor;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    @Query(value = "select * from get_detalle_problema_por_proveedor()", nativeQuery = true)
    List<Object[]> getFIPorProveedor();

}
