package pe.sia.service.interfaces;

import java.util.List;
import pe.sia.presentation.dto.activosDTO.SoftwareDTO;

public interface SoftwareService {

    SoftwareDTO createSoftware(SoftwareDTO softwareDTO);
    SoftwareDTO updateSoftware(Long idSoftware, SoftwareDTO softwareDTO);
    void deleteSoftware(Long idSoftware);
    SoftwareDTO getSoftwareById(Long idSoftware);
    List<SoftwareDTO> getAllSoftwares();
    
}
