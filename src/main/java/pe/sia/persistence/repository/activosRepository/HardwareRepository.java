package pe.sia.persistence.repository.activosRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sia.persistence.entity.activos.Hardware;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Integer>{

}
