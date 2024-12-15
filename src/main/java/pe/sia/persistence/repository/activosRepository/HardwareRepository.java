package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.activos.Hardware;

import java.util.List;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Integer>{

    @Query(value = "select * from get_detalle_problema_por_hardware()", nativeQuery = true)
    List<Object[]> getFIPorHardware();
}
