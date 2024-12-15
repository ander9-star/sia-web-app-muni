package pe.sia.persistence.repository.actoresRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.actores.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query(value = "SELECT * FROM get_top5_empleados_detalle_problema()", nativeQuery = true)
    List<Object[]> getTop5EmpleadosTncidencias();

    // para obtener todos los empleados y la cantidad total de problema
    @Query(value = "SELECT * FROM get_total_empleados_detalle_problema()", nativeQuery = true)
    List<Object[]> getTotalEmpleadosIncidencias();

    // para obtener la data completa de todos los empleados
    @Query(value = "SELECT * FROM get_data_total_empleados()", nativeQuery = true)
    List<Object[]> getDataTotalEmpleados();

}
