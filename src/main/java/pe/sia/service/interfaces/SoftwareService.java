package pe.sia.service.interfaces;

import java.util.List;
import pe.sia.presentation.dto.activosDTO.SoftwareDTO;

public interface SoftwareService {
    SoftwareDTO createSoftware(SoftwareDTO softwareDTO);
    SoftwareDTO updateSoftware(Integer idSoftware, SoftwareDTO softwareDTO);
    void deleteSoftware(Integer idSoftware);
    SoftwareDTO getSoftwareById(Integer idSoftware);
    List<SoftwareDTO> getAllSoftwares();
    SoftwareDTO getFISoftware();
}
